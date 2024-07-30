package com.ruoyi.xljk.controller;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.http.HttpServletResponse;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(tags = "测评记录导出")
@RequestMapping("/xljk/exportToExcel")
public class ExportToExcelController {

    @ApiOperation("导出")
    @GetMapping("/export")
    public void exportToExcel(@RequestParam("deptId") int deptId, HttpServletResponse response) {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        try {
            String fileName = URLEncoder.encode("测试状态.xlsx", StandardCharsets.UTF_8.toString());
            response.setHeader("Content-Disposition", "attachment; filename*=UTF-8''" + fileName);

            try (Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://8.130.87.53:3306/xljk?useUnicode=true&characterEncoding=utf8&zeroDateTimeBehavior=convertToNull&useSSL=true&serverTimezone=GMT%2B8",
                    "root", "zkrtFCZ812");
                 XSSFWorkbook workbook = new XSSFWorkbook()) {

                // SQL 查询
                String query = "SELECT " +
                        "b.name, " +
                        "a.test_name, " +
                        "a.test_time, " +
                        "'已测试' AS test_status " +
                        "FROM " +
                        "answer_localhost a " +
                        "LEFT JOIN " +
                        "sys_user b ON a.openid = b.open_id " +
                        "WHERE " +
                        "b.identity = '学生' AND b.dept_id = ? " +
                        "UNION " +
                        "SELECT " +
                        "b.name, " +
                        "NULL AS test_name, " +
                        "NULL AS test_time, " +
                        "'未测试' AS test_status " +
                        "FROM " +
                        "sys_user b " +
                        "LEFT JOIN " +
                        "answer_localhost a ON b.open_id = a.openid " +
                        "WHERE " +
                        "b.identity = '学生' AND b.dept_id = ? AND a.openid IS NULL " +
                        "ORDER BY " +
                        "name;";

                // 执行查询
                PreparedStatement preparedStatement = connection.prepareStatement(query);
                preparedStatement.setInt(1, deptId);
                preparedStatement.setInt(2, deptId);
                ResultSet resultSet = preparedStatement.executeQuery();

                // 创建 Excel 文件
                XSSFSheet sheet = workbook.createSheet("Query Results");

                // 创建表头
                Row headerRow = sheet.createRow(0);
                String[] columns = {"姓名", "测试名称", "测试时间", "测试状态"};
                for (int i = 0; i < columns.length; i++) {
                    Cell cell = headerRow.createCell(i);
                    cell.setCellValue(columns[i]);
                }

                // 填充数据
                int rowNum = 1;
                while (resultSet.next()) {
                    Row row = sheet.createRow(rowNum++);
                    row.createCell(0).setCellValue(resultSet.getString("name"));
                    row.createCell(1).setCellValue(resultSet.getString("test_name"));
                    row.createCell(2).setCellValue(resultSet.getString("test_time"));
                    row.createCell(3).setCellValue(resultSet.getString("test_status"));
                }

                // 将生成的 Excel 文件写入响应
                workbook.write(response.getOutputStream());
                response.flushBuffer();

            } catch (SQLException | IOException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
