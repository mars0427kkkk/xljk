package com.ruoyi.xljk.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.xljk.domain.SysFileInfo;
import com.ruoyi.xljk.domain.correctionalMode;
import com.ruoyi.xljk.domain.nameVo;
import com.ruoyi.xljk.domain.positiveNature;
import com.ruoyi.xljk.service.ISysFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.xljk.domain.QuestionAnswer;
import com.ruoyi.xljk.service.IQuestionAnswerService;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author ruoyi
 * @date 2024-03-20
 */
@Api(tags = "测评题目管理")
@RestController
@RequestMapping("/xljk/answer")
public class QuestionAnswerController extends BaseController
{
    @Autowired
    private IQuestionAnswerService questionAnswerService;
    @Autowired
    private ISysFileInfoService sysFileInfoService;

    @ApiOperation("教养模式")
    @GetMapping("/home")
    public AjaxResult homelist(correctionalMode correctionalMode){

        HashMap<String, Long> HashMap = new HashMap<>();
        List<String> list = new ArrayList<>();

        Long authoritativeDemocracy = correctionalMode.getAuthoritativeDemocracy();
        Long strongControl = correctionalMode.getStrongControl();
        Long drowningIndulgence = correctionalMode.getDrowningIndulgence();
        Long ingnoringIndifference = correctionalMode.getIngnoringIndifference();


        HashMap.put("权威民主",authoritativeDemocracy);
        HashMap.put("强势控制",strongControl);
        HashMap.put("溺爱放纵",drowningIndulgence);
        HashMap.put("忽视淡漠",ingnoringIndifference);

        long maxValue = Long.MIN_VALUE;
        String maxObjects = "";

        for (Map.Entry<String, Long> entry : HashMap.entrySet()) {
            long value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                maxObjects = entry.getKey();
                list.clear();
                list.add(maxObjects);

            } else if (value == maxValue) {

                list.add(entry.getKey());
            }
        }

        return success(list);
    }


    @ApiOperation("积极天性")
    @GetMapping("/posi")
    public AjaxResult posilist(positiveNature positiveNature){

        HashMap<String, Long> HashMap = new HashMap<>();
        List<String> list = new ArrayList<>();

        Long authoritativeDemocracy = positiveNature.getA();
        Long strongControl = positiveNature.getB();
        Long drowningIndulgence = positiveNature.getC();
        Long ingnoringIndifference = positiveNature.getD();


        HashMap.put("向日葵型",authoritativeDemocracy);
        HashMap.put("玫瑰型",strongControl);
        HashMap.put("仙人掌型",drowningIndulgence);
        HashMap.put("含羞草型",ingnoringIndifference);

        long maxValue = Long.MIN_VALUE;
        String maxObjects = "";

        for (Map.Entry<String, Long> entry : HashMap.entrySet()) {
            long value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                maxObjects = entry.getKey();
                list.clear();
                list.add(maxObjects);

            } else if (value == maxValue) {

                list.add(entry.getKey());
            }
        }

        return success(list);
    }
   @ApiOperation("名字、题目数查询")
    @GetMapping("/name")
    public AjaxResult namelist()
    {

        ArrayList<nameVo> list2 = new ArrayList<>();
        SysFileInfo sysFileInfo = new SysFileInfo();


        List<SysFileInfo> list1 = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        List<QuestionAnswer> list = questionAnswerService.selectQuestionAnswerNameList();
        for (QuestionAnswer s: list) {
            nameVo nameVo = new nameVo();
            nameVo.setQuestionId(s.getQuestionId());
            nameVo.setQuestionType(s.getQuestionType());
            nameVo.setFilePath(s.getFilePath());
            list2.add(nameVo);
        }

        return success(list2);
    }
    /**
     * 查询【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:list')")
    @ApiOperation("题目查询")
    @GetMapping("/list")
    public TableDataInfo list(QuestionAnswer questionAnswer)
    {
//        startPage();

        List<QuestionAnswer> list = questionAnswerService.selectQuestionAnswerList(questionAnswer);

        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:export')")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    public void export(HttpServletResponse response, QuestionAnswer questionAnswer)
    {
        List<QuestionAnswer> list = questionAnswerService.selectQuestionAnswerList(questionAnswer);
        ExcelUtil<QuestionAnswer> util = new ExcelUtil<QuestionAnswer>(QuestionAnswer.class);
        util.exportExcel(response, list, "【请填写功能名称】数据");
    }

    /**
     * 获取【请填写功能名称】详细信息
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:query')")
    @ApiOperation("根据id查询题目")
    @GetMapping(value = "/{id}")
    public AjaxResult getInfo(@PathVariable("id") Long id)
    {
        return success(questionAnswerService.selectQuestionAnswerById(id));
    }

    /**
     * 新增【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:add')")
    @ApiOperation("新增题目")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping
    public AjaxResult add(@RequestBody QuestionAnswer questionAnswer)
    {
        return toAjax(questionAnswerService.insertQuestionAnswer(questionAnswer));
    }

    /**
     * 修改【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:edit')")
    @ApiOperation("修改题目")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PutMapping
    public AjaxResult edit(@RequestBody QuestionAnswer questionAnswer)
    {
        return toAjax(questionAnswerService.updateQuestionAnswer(questionAnswer));
    }

    /**
     * 删除【请填写功能名称】
     */
//    @PreAuthorize("@ss.hasPermi('system:answer:remove')")
    @ApiOperation("删除题目")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
	@DeleteMapping("/{ids}")
    public AjaxResult remove(@PathVariable Long[] ids)
    {
        return toAjax(questionAnswerService.deleteQuestionAnswerByIds(ids));
    }
}
