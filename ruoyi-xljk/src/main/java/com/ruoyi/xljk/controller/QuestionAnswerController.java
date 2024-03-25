package com.ruoyi.xljk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.xljk.domain.Vo.stuposiVo;
import com.ruoyi.xljk.domain.correctionalMode;
import com.ruoyi.xljk.domain.Vo.homeVo;
import com.ruoyi.xljk.domain.Vo.nameVo;
import com.ruoyi.xljk.domain.positiveNature;
import com.ruoyi.xljk.service.ISysFileInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        List<homeVo> list = new ArrayList<>();

        Long authoritativeDemocracy = correctionalMode.getAuthoritativeDemocracy();
        Long strongControl = correctionalMode.getStrongControl();
        Long drowningIndulgence = correctionalMode.getDrowningIndulgence();
        Long ingnoringIndifference = correctionalMode.getIngnoringIndifference();


        HashMap.put("权威民主:权威民主型的家庭被认为是智慧型父母和理想家庭，他们能够尊重孩子的需要，及时给予孩子反馈。",authoritativeDemocracy);
        HashMap.put("强势控制:强势控制型的父母对孩子通常要求很高，但是对孩子的需求却不敏感，常常不能关注到孩子的需求",strongControl);
        HashMap.put("溺爱放纵:溺爱放纵型父母对孩子通常要求很低，但是对孩子的需求却是高度反应。",drowningIndulgence);
        HashMap.put("忽视淡漠:忽视淡漠型家庭的父母对孩子通常要求很低甚至没要求，对孩子的需求也看不见。",ingnoringIndifference);

        long maxValue = Long.MIN_VALUE;
        String maxObjects = "";

        for (Map.Entry<String, Long> entry : HashMap.entrySet()) {
            long value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                maxObjects = entry.getKey();
                String[] split = maxObjects.split(":");
                String s = split[0];
                String s1 = split[1];
                homeVo homeVo = new homeVo();
                homeVo.setName(s);
                homeVo.setType(s1);
                list.clear();
                list.add(homeVo);

            } else if (value == maxValue) {

                maxObjects = entry.getKey();
                String[] split = maxObjects.split(":");
                String s = split[0];
                String s1 = split[1];
                homeVo homeVo = new homeVo();
                homeVo.setName(s);
                homeVo.setType(s1);
                list.add(homeVo);
            }
        }

        return success(list);
    }

    @ApiOperation("积极心理")
    @PostMapping ("/stuposi")
    public AjaxResult stuposilist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        ArrayList<String> list = new ArrayList<>();
        ArrayList<stuposiVo> list1 = new ArrayList<>();
        stuposiVo stuposiVo = new stuposiVo();
        list.add("1");
        list.add("13");
        list.add("25");
        list.add("37");
        list.add("49");
        int i = 0;
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
             i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("乐观希望");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("4");
        list.add("16");
        list.add("28");
        list.add("40");
        list.add("52");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("自信悦纳");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("7");
        list.add("19");
        list.add("31");
        list.add("43");
        list.add("55");
        list.add("62");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("自控力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("4");
        list.add("16");
        list.add("28");
        list.add("40");
        list.add("52");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("自信悦纳");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("10");
        list.add("22");
        list.add("34");
        list.add("46");
        list.add("58");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("专注力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("2");
        list.add("14");
        list.add("26");
        list.add("38");
        list.add("50");
        list.add("63");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("乐群合作");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("5");
        list.add("17");
        list.add("29");
        list.add("41");
        list.add("53");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("同理心");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("8");
        list.add("20");
        list.add("32");
        list.add("44");
        list.add("56");
        list.add("64");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("感恩利他");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("11");
        list.add("23");
        list.add("35");
        list.add("47");
        list.add("59");
        list.add("65");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("领袖力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("3");
        list.add("15");
        list.add("27");
        list.add("39");
        list.add("51");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("抗逆力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("6");
        list.add("18");
        list.add("30");
        list.add("42");
        list.add("54");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("感悟力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(25L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("9");
        list.add("21");
        list.add("33");
        list.add("45");
        list.add("57");
        list.add("66");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("好奇心");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        list.clear();
        stuposiVo = null;
        i = 0;
        list.add("12");
        list.add("24");
        list.add("36");
        list.add("48");
        list.add("60");
        list.add("67");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }
        stuposiVo.setName("创造力");
        stuposiVo.setType((long) i);
        stuposiVo.setNum(30L);
        list1.add(stuposiVo);
        return success(list1);
    }
    private Map<String, String> convertListToMap(List<homeVo> stuposi) {
        Map<String, String> resultMap = new HashMap<>();
        for (homeVo vo : stuposi) {
            resultMap.put(vo.getName(), vo.getType());
        }
        return resultMap;
    }
    @ApiOperation("积极天性")
    @GetMapping("/posi")
    public AjaxResult posilist(positiveNature positiveNature){

        HashMap<String, Long> HashMap = new HashMap<>();
        List<homeVo> list = new ArrayList<>();

        Long authoritativeDemocracy = positiveNature.getA();
        Long strongControl = positiveNature.getB();
        Long drowningIndulgence = positiveNature.getC();
        Long ingnoringIndifference = positiveNature.getD();


        HashMap.put("向日葵型:向日葵型孩子他们总能自然而然地从心底换发着活力和热情，通常他们是人群中那个快乐和乐趣的存在",authoritativeDemocracy);
        HashMap.put("玫瑰型:玫瑰型的孩子给大家最直接的印象是“完美人设”。无论是在行为处事、外在形象还是内心情绪上，他们渴望永远做到完美、追求卓越。\n",strongControl);
        HashMap.put("仙人掌型:仙人掌型的孩子不甘于平凡，永远精力充沛。他们是人类中的冒险家，会为了目标勇往直前。",drowningIndulgence);
        HashMap.put("含羞草型:羞草类型的孩子给人的最大印象是安静害羞，又有着敏感细腻的觉察力，不需刻意就会捕捉到人与人之间的微妙的信息。",ingnoringIndifference);

        long maxValue = Long.MIN_VALUE;
        String maxObjects = "";

        for (Map.Entry<String, Long> entry : HashMap.entrySet()) {
            long value = entry.getValue();
            if (value > maxValue) {
                maxValue = value;
                maxObjects = entry.getKey();
                String[] split = maxObjects.split(":");
                String s = split[0];
                String s1 = split[1];
                homeVo homeVo = new homeVo();
                homeVo.setName(s);
                homeVo.setType(s1);
                list.clear();
                list.add(homeVo);

            } else if (value == maxValue) {

                maxObjects = entry.getKey();
                String[] split = maxObjects.split(":");
                String s = split[0];
                String s1 = split[1];
                homeVo homeVo = new homeVo();
                homeVo.setName(s);
                homeVo.setType(s1);
                list.add(homeVo);
            }
        }

        return success(list);
    }
   @ApiOperation("名字、题目数查询")
    @GetMapping("/name")
    public AjaxResult namelist(String type)
    {

        ArrayList<nameVo> list2 = new ArrayList<>();



        List<QuestionAnswer> list = questionAnswerService.selectQuestionAnswerNameList(type);
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
