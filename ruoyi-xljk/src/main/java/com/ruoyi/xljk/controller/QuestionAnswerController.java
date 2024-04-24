package com.ruoyi.xljk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.xljk.domain.AnswerLocalhost;
import com.ruoyi.xljk.domain.Vo.stuposiVo;
import com.ruoyi.xljk.domain.correctionalMode;
import com.ruoyi.xljk.domain.Vo.homeVo;
import com.ruoyi.xljk.domain.Vo.nameVo;
import com.ruoyi.xljk.domain.positiveNature;
import com.ruoyi.xljk.service.IAnswerLocalhostService;
import com.ruoyi.xljk.service.IJjxlService;
import com.ruoyi.xljk.service.ISysFileInfoService;
import com.sun.net.httpserver.Authenticator;
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
    @Autowired
    private IAnswerLocalhostService answerLocalhostService;

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

    @ApiOperation("幸福感")
    @PostMapping ("/happy")
    public AjaxResult happylist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        //检查选项是否一致
        boolean allValuesEqual = areAllValuesEqual(resultMap);

        if (allValuesEqual) {
            return success("不行");
        }
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String vo : resultMap.values()) {

            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(vo)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(vo, occurrencesMap.get(vo) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(vo, 1);
            }

        }
        String keyToRemove = "openid";
        String s1 = resultMap.get(keyToRemove);
        occurrencesMap.remove(s1);
        StringBuilder stringBuilder = new StringBuilder();


        // 遍历occurrencesMap，并构建字符串
        for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }

        // 删除最后一个逗号和空格
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }



        // 打印结果
        String occurrencesString = stringBuilder.toString();

        AnswerLocalhost answerLocalhost = new AnswerLocalhost();
        List<String> list = new ArrayList<>();
        List<stuposiVo> list1 = new ArrayList<>();
        stuposiVo stuposiVo = new stuposiVo();
        stuposiVo stuposiVo1 = new stuposiVo();
        stuposiVo stuposiVo2 = new stuposiVo();
        stuposiVo stuposiVo3 = new stuposiVo();
        stuposiVo stuposiVo4 = new stuposiVo();
        stuposiVo stuposiVo5 = new stuposiVo();
        stuposiVo stuposiVo6 = new stuposiVo();
        stuposiVo stuposiVo7 = new stuposiVo();
        stuposiVo stuposiVo8 = new stuposiVo();
        stuposiVo stuposiVo9 = new stuposiVo();
        stuposiVo stuposiVo10 = new stuposiVo();
        stuposiVo stuposiVo11 = new stuposiVo();
        stuposiVo stuposiVo12 = new stuposiVo();
        stuposiVo stuposiVo13 = new stuposiVo();
        stuposiVo stuposiVo14 = new stuposiVo();
        stuposiVo stuposiVo15 = new stuposiVo();
        list.add("1");
        list.add("6");
        list.add("11");
        list.add("16");
        list.add("21");
       Double i = 0.0;
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionhappySAnswer(name, type);
        }
        stuposiVo.setName("积极情绪");
        stuposiVo.setType( i);
        stuposiVo.setNum(25L);
            stuposiVo.setAnswer("积极情绪(positive emotions)。包括快乐、满足、希望等，有助于应对挑战和提高创新能力，，；这些情绪能够推动人们去追求良好的结果，进一步调动人们的积极性。");

        list1.add(stuposiVo);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("2");
        list.add("7");
        list.add("12");
        list.add("17");
        list.add("22");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionhappySAnswer(name, type);
        }
        stuposiVo1.setName("投入");
        stuposiVo1.setType(i);
        stuposiVo1.setNum(25L);

        stuposiVo1.setAnswer("正向的投入(engagement)。指全身心投入到某项活动中时体验到的状态，也被称为“心流”，让人们忘记自我，只关注手头的任务；也是指不仅仅把工作当成饭碗，而要当成实现自我价值的平台。");

        list1.add(stuposiVo1);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("3");
        list.add("8");
        list.add("13");
        list.add("18");
        list.add("23");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionhappySAnswer(name, type);
        }
        stuposiVo2.setName("关系");
        stuposiVo2.setType( i);
        stuposiVo2.setNum(25L);

        stuposiVo2.setAnswer("和谐的关系(relationships)。人是社会性动物，需要与他人建立和谐的人际关系以获取支持和归属感；用良好的心态去面对工作中的每一个人也有助于提升组织氛围。");

        list1.add(stuposiVo2);
        list.clear();
        //stuposiVo = null;
        i =0.0;
        list.add("4");
        list.add("9");
        list.add("14");
        list.add("19");
        list.add("24");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionhappySAnswer(name, type);
        }
        stuposiVo3.setName("意义");
        stuposiVo3.setType( i);
        stuposiVo3.setNum(25L);

        stuposiVo3.setAnswer("意义(meaning)。指的是人们认为自己的工作和生活有目标、有价值；找到工作的意义所在能够为工作带来动力和目标感。");

        list1.add(stuposiVo3);
        list.clear();
        //stuposiVo = null;
        i =0.0;
        list.add("5");
        list.add("10");
        list.add("15");
        list.add("20");
        list.add("25");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionhappySAnswer(name, type);
        }
        stuposiVo4.setName("成就");
        stuposiVo4.setType( i);
        stuposiVo4.setNum(25L);

        stuposiVo4.setAnswer("成就(accomplishment)。这是人们在追求目标过程中取得的重要成果或成功经历所带来的满足感与自豪感等方面体验。");

        list1.add(stuposiVo4);
        String string = list1.toString();
        String s = resultMap.get("openid");
        answerLocalhost.setOpenid(s);
        answerLocalhost.setTestName("主观幸福感");
        answerLocalhost.setAnswer(string);
        answerLocalhost.setAnswerNum(occurrencesString);

        answerLocalhostService.insertAnswerLocalhost(answerLocalhost);
        AnswerLocalhost answerLocalhost1 = answerLocalhostService.selectAnswerLocalhostById(answerLocalhost.getId());
        stuposiVo15.setTestTime(answerLocalhost1.getTestTime());
        stuposiVo15.setAnswer(answerLocalhost1.getTestName());
        list1.add(stuposiVo15);
        return success(list1);
    }
    @ApiOperation("积极心理")
    @PostMapping ("/stuposis")
    public AjaxResult stuposislist(@RequestBody List<homeVo> stuposi){
        //映射map
        Map<String, String> resultMap = convertListToMap(stuposi);
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String vo : resultMap.values()) {

            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(vo)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(vo, occurrencesMap.get(vo) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(vo, 1);
            }

        }
        String keyToRemove = "openid";
        String s1 = resultMap.get(keyToRemove);
        occurrencesMap.remove(s1);
        StringBuilder stringBuilder = new StringBuilder();


        // 遍历occurrencesMap，并构建字符串
        for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }

        // 删除最后一个逗号和空格
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }


        // 打印结果
        String occurrencesString = stringBuilder.toString();

        AnswerLocalhost answerLocalhost = new AnswerLocalhost();
        List<String> list = new ArrayList<>();
        List<stuposiVo> list1 = new ArrayList<>();
        stuposiVo stuposiVo = new stuposiVo();
        stuposiVo stuposiVo1 = new stuposiVo();
        stuposiVo stuposiVo2 = new stuposiVo();
        stuposiVo stuposiVo3 = new stuposiVo();
        stuposiVo stuposiVo4 = new stuposiVo();
        stuposiVo stuposiVo5 = new stuposiVo();
        stuposiVo stuposiVo6 = new stuposiVo();
        stuposiVo stuposiVo7 = new stuposiVo();
        stuposiVo stuposiVo8 = new stuposiVo();
        stuposiVo stuposiVo9 = new stuposiVo();


        list.add("1");
        list.add("3");
        list.add("4");
        list.add("7");
        list.add("10");
        list.add("15");
        list.add("20");
        list.add("23");
        Double i =0.0;
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo.setName("生命动力");
        stuposiVo.setType(i);
        stuposiVo.setNum(32L);
        if (i <= 6){
            stuposiVo.setAnswer("日常生活中，可能表现为独立性差，较少按照自己的想法或意愿做事；从属性强，缺乏主观能动性，生活中体验到的幸福感也不是很强，偶尔可能会出现抑郁情绪。建议家长从孩子的情绪感受入手，加深沟通交流，尽可能共情孩子，引导孩子逐步恢复对生活的兴趣和热爱。");
        }else if (i >= 7 && i <= 12){
            stuposiVo.setAnswer("日常生活中，可能表现为个体主观能动性差或感知到的幸福感比较少，经营自己的生活时感到力不从心。建议家长从孩子的兴趣爱好入手，与孩子一起找出一两件最感兴趣的项目（如运动、音乐、绘画等）进行定期活动，逐步激发起孩子对生活的兴趣。");
        }else if (i >= 13 && i <= 19){
            stuposiVo.setAnswer("日常生活中，可能表现为对生活持无所谓态度，凡事任由家长或老师安排，没有足够的成长动力，也没有体验到很强的幸福感和满意度。通常来说，这样的孩子情绪相对稳定，不容易被周围环境影响。建议家长在日常生活中多为孩子创造一些自己做主的权利和机会，逐步树立主人翁意识，培养主观能动性。");
        }else if (i >= 20 && i <= 25){
            stuposiVo.setAnswer("日常生活中，可能表现为对生活有一定追求，能够独立规划自己的生活，凡事有自己的思路和想法，对待生活认真负责。偶尔会有追求过于完美的倾向，对自己造成一定的压力，产生紧张或焦虑情绪。建议家长加深与孩子的沟通，及时关注孩子的情绪，帮助孩子掌握一些调节和控制负性情绪的方式方法，提升主观幸福感。");
        } else {
            stuposiVo.setAnswer("日常生活中，可能表现为独立性强，凡事有自己的思路和想法，目标清晰，热爱生活，有极强的生活满意度和主观幸福感。建议家长帮助孩子维持平和、乐观、开朗的心态，积极学习一些心理保健知识和心理调节技能，维护心理健康状态的平衡。");
        }
//        int i1 = i / 25;
//        if (i1 >= 0.76){
//          stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。乐观者更习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！\n" +
//                  "乐观者更愿意面向未来的思考，采取积极行动，不随波逐流。需要注意乐观希望不是自欺欺人的逃避，面向未来的同时需要注意当下能够付出不亚于任何人的努力。");
//        }else if (i1 > 0.44 && i1 < 0.76){
//    stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。所以，要想成为一个乐观的人，要习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！");
//        }else {
//            stuposiVo.setAnswer("高乐观希望者在生活中总能以正向的角度看待事物与未来，期待未来会更好，并愿为之努力。在生活中习惯用积极的视角看待生活，遇到困难总是优先看到事件中有利的部分。有着乐观希望品质的人能够积极的解释发生在自己身边的事情，遇事不会自怨自艾，采用“问题为中心”的策略来调整情绪，解决问题");
//        }
        list1.add(stuposiVo);
        list.clear();
        //stuposiVo = null;
        i =0.0;
        list.add("5");
        list.add("8");
        list.add("9");
        list.add("12");
        list.add("16");
        list.add("19");
        list.add("24");
        list.add("25");

        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo3.setName("学习动力");
        stuposiVo3.setType( i);
        stuposiVo3.setNum(32L);
        if (i <= 6){
            stuposiVo3.setAnswer("日常生活中， 可能表现为与同伴相比，更容易对学习感到厌烦，学习意愿和对学习的自信都非常欠缺，或许已经产生了比较明显的厌学表现，如：不相信自己能够通过努力获得较好的学习成绩，对学习有强烈的抵触情绪，产生回避学习的行为，甚至旷课、休学等。建议家长首先安顿好自己的焦虑情绪，以稳定的状态去面对孩子，在生活中给孩子带来更多的鼓励和引导，协助孩子制定切实可行的学习目标，从小处着手，陪伴孩子一点一滴地进步，逐渐帮助孩子建立学习自信。");
        }else if (i >= 7 && i <= 12){
            stuposiVo3.setAnswer("日常生活中， 可能表现为学习方面存在一定的问题，如自感学习压力较大、学习缺乏内动力、学习目标不够明确等，也可能出现学习效率低下、在学习方面自信和动机不足等现象。建议家长支持孩子积极参与课外活动，帮助孩子减缓学习压力；另外，家长需要降低对学习成绩的关注，而更多地关注学习的过程，给孩子提供良好的支持。");
        }else if (i >= 13 && i <= 19){
            stuposiVo3.setAnswer("日常生活中， 可能表现为学习状态一般，对待学习的态度中规中矩，基本能够跟上课堂的学习进度，课后也会进行一定的学习。对学习说不上讨厌但也谈不上喜欢。建议家长给孩子更多的支持和鼓励，控制批评和指责，在生活中帮助孩子培养对某一学科的兴趣爱好，逐渐引导孩子树立学习志向，找到学习真正的意义，进一步提升学习动机。");
        }else if (i >= 20 && i <= 25){
            stuposiVo3.setAnswer("日常生活中， 可能表现为学习状态良好，有自己的学习目标，能够通过努力克服学习中遇到的困难，虽然也会感受到学习压力，但是能够进行自我调整。建议家长给予孩子更多的鼓励和支持，引导孩子客观公正地评价自身学业水平，在实事求是的基础上，以积极平和的心态面对学习压力。");
        } else {
            stuposiVo3.setAnswer("日常生活中， 可能表现为有较强的学习内动力，在学习中能够积极面对困难，并通过完成挑战性的学习任务获得快乐及成就感。虽然会感受到一定的学习压力，但是这种压力对于孩子来说也是一种积极的动力，会促进他的学习不断进步。这与家长从小对孩子的支持和引导是分不开的。建议家长继续保持，引导孩子更加深刻地理解学习的意义，同时拓展视野，辅助孩子塑造自己的格局与成长方向。");
        }
//        int i4 = i / 25;
//        if (i4 >= 0.76){
//            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。他们不会眼皮底下的东西都找不着，不会丢三落四。他们的作业写得仔细认真，考试时也很少出现漏题、丢词少句、漏看小数点等“马虎”问题。");
//        }else if (i4> 0.44 && i4 < 0.76){
//            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。专注力高的人也更加独立。由于做事专心致志，所以培养了很强的自理能力。在学习中遇到问题也会积极解决。");
//        }else {
//            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。他们不会眼皮底下的东西都找不着，不会丢三落四。他们的作业写得仔细认真，考试时也很少出现漏题、丢词少句、漏看小数点等“马虎”问题。由于注意力集中，他们阅读文章的速度快，还能清楚地辨认一些细节。");
//        }
        list1.add(stuposiVo3);
        list.clear();
        //stuposiVo = null;
        i =0.0;
        list.add("2");
        list.add("6");
        list.add("11");
        list.add("14");
        list.add("17");
        list.add("18");
        list.add("21");
        list.add("22");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo6.setName("关系动力");
        stuposiVo6.setType( i);
        stuposiVo6.setNum(32L);
        if (i <= 6){
            stuposiVo6.setAnswer("日常生活中，可能表现为亲子之间存在问题，父母难以了解孩子的想法，孩子也较少向父母求助，很难从父母中获得想要的支持。孩子处理情绪问题的能力也比较差，换位思考能力较弱，较难融入集体，长此以往，容易出现厌学、辍学的倾向。建议家长从情绪感受入手，多倾听孩子的想法，一同构建安全、可信任的亲子和社交关系。");
        }else if (i >= 7 && i <= 12){
            stuposiVo6.setAnswer("日常生活中，可能表现为亲子之间存在一些问题，如父母不太了解孩子的想法，孩子偶尔会向父母求助，也很难从父母中获得有效支持。孩子有可能不妥当地处理自己的情绪问题，只站在自己的角度处理与他人的关系，在学校的集体融入度一般，偶尔会出现社交问题。建议家长从孩子感兴趣的人与事情出发，鼓励孩子多与朋友交往，给孩子提供良好的支持。");
        }else if (i >= 13 && i <= 19){
            stuposiVo6.setAnswer("日常生活中，可能表现为与父母的关系一般，父母不能够完全了解孩子的想法，有时也不能及时地提供心理支持。社交关系方面，能够处理自己的部分情绪问题，但是从社交关系中获得的社会支持还不够。建议家长更多地支持和鼓励孩子参加集体活动，从集体中获得归属感和价值感。");
        }else if (i >= 20 && i <= 25){
            stuposiVo6.setAnswer("日常生活中，可能表现为亲子关系良好，父母比较了解孩子的想法，大部分时间孩子可以处理自己的问题，当询问父母的意见时，也可以获得有效的支持。孩子处理自己情绪问题的能力也比较好，换位思考能力较强，可以很快融入集体，在集体中有一定影响力和话语权。建议家长对孩子积极的行为及看法多加赞赏，并主动和孩子一起尝试有趣的事物。");
        } else {
            stuposiVo6.setAnswer("日常生活中，可能表现为亲子关系融洽，父母能够及时了解孩子的想法，孩子也可以独立处理自己的事情。遇到问题时，孩子会询问父母的意见，并获得足够有效的支持。孩子了解自己的情绪，也可以很好地处理自己的情绪问题；换位思考能力较强，可以又快又好地融入集体，在集体中有较高的影响力和话语权。建议家长为孩子提供更多的发展空间，在孩子需要时及时给予足够的支持和陪伴。");
        }
//        int i7 = i / 30;
//        if (i7 >= 0.76){
//            stuposiVo6.setAnswer("高感恩利他的人，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
//        }else if (i7> 0.44 && i7 < 0.76){
//            stuposiVo6.setAnswer("要想成为感恩利他的人，你需要对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
//        }else {
//            stuposiVo6.setAnswer("高感恩利他者，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。高感恩利他者，对生活更加满意，行动的动机更强，而且更加健康，睡眠也更加充足，焦虑、抑郁、孤独感都会下降。高感恩利他的青少年更容易融入生活，拥有良好的人际关系，还会赢得更多人的帮助和认可；也更容易接纳自我的成长，有更强烈的目的感、意义感和道德感。");
//        }
        list1.add(stuposiVo6);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("10");
        list.add("3");
        list.add("15");
        list.add("7");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo1.setName("独立性");
        stuposiVo1.setNum(16L);
        stuposiVo1.setAnswer("独立性指的是人的意志不易受他人的影响，有较强的独立提出和实施行为目的的能力，它反映了意志的行为价值的内在稳定性。遇事有主见，有成就动机，不依赖他人就能独立处理事情，积极主动地完成各项实际工作的心理品质，它伴随勇敢、自信、认真、专注、责任感和不怕困难的精神。");
        stuposiVo1.setType( i);

//        stuposiVo1.setNum(25L);
//        int i2 = i / 25;
//        if (i2 >= 0.76){
//            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。高自信悦纳者，能够积极地评价自己并相信自己有能力把事情做好。社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。能够正确地评价自己、接纳自己，并在此基础上使自我得到良好的发展。");
//        }else if (i2 > 0.44 && i2 < 0.76){
//            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。要想成为一个自信悦纳的人，要能够积极地评价自己并相信自己有能力把事情做好。要社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。能够正确地评价自己、接纳自己，并在此基础上使自我得到良好的发展。");
//        }else {
//            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。高自信悦纳者，能够积极地评价自己并相信自己有能力把事情做好。社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。");
//        }
        list1.add(stuposiVo1);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("20");
        list.add("4");
        list.add("1");
        list.add("23");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo2.setName("幸福感");
        stuposiVo2.setNum(16L);
        stuposiVo2.setAnswer("幸福感，又称主观幸福感（Subjective Well-Being，简称SWB），主要是指人们对其生活质量所做的情感性和认知性的整体评价。在这种意义上，决定人们是否幸福的并不是实际发生了什么，关键是人们对所发生的事情在情绪上做出何种解释，在认知上进行怎样的加工。");
        stuposiVo2.setType( i);
//        stuposiVo2.setNum(30L);
//        int i3 = i / 30;
//        if (i3 >= 0.76){
//            stuposiVo2.setAnswer("在以下4个方面，高自控力者总是让人心生羡慕：\n" +
//                    "① 延迟满足。为了达到长期的目标，高自控力者大多数时候都能够抵御眼前的诱惑。\n" +
//                    "② 及时喊停。高自控力者总是能够推翻那些无用甚至有害的想法、感觉和冲动的能力。\n" +
//                    "③ 冷静思考。在行动的时候，高自控力者更多三思而后行。头脑发热一时冲动？不存在的。\n" +
//                    "④ 超强自制。高自控力者在大多数时候总是能够有意识地给自我设定规则，并努力遵守。");
//        }else if (i3> 0.44 && i3 < 0.76){
//            stuposiVo2.setAnswer("要想成为高自控力者，要在以下4个方面下功夫：\n" +
//                    "① 延迟满足。为了达到长期的目标，高自控力者大多数时候都能够抵御眼前的诱惑。\n" +
//                    "② 及时喊停。高自控力者总是能够推翻那些无用甚至有害的想法、感觉和冲动的能力。\n" +
//                    "③ 冷静思考。在行动的时候，高自控力者更多三思而后行。头脑发热一时冲动？不存在的。\n" +
//                    "④ 超强自制。高自控力者在大多数时候总是能够有意识地给自我设定规则，并努力遵守。");
//        }else {
//            stuposiVo2.setAnswer("高自控力的人在以下4个方面表现良好：延迟满足的能力，即为了达到长期的目标，而能够抵御眼前的诱惑的能力；能够推翻那些无用甚至有害的想法、感觉和冲动的能力；在行动的时候，采用一种更为冷静的认知系统，而不是一种冲动的情绪系统的能力；能够有意识地给自我设定规则，并努力遵守。");
//        }
        list1.add(stuposiVo2);

        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("24");
        list.add("12");
        list.add("25");
        list.add("5");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo4.setName("学习态度");
        stuposiVo4.setNum(16L);
        stuposiVo4.setAnswer("学习态度是指学习者对学习较为持久的肯定或否定的行为倾向或内部反应的准备状态。它通常可以从学生对待学习的注意状况、情绪状况和意志状态等方面加以判定和说明。");
        stuposiVo4.setType( i);
//        stuposiVo4.setNum(30L);
//        int i5 = i / 30;
//        if (i5 >= 0.76){
//            stuposiVo4.setAnswer("乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。他们善于与人沟通合作，在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，他们通常有适合的方法来化解，将团队凝聚起来发挥更大的力量。");
//        }else if (i5> 0.44 && i5 < 0.76){
//            stuposiVo4.setAnswer("乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。所以，我们要善于与人沟通合作，这样在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，要找到适合的方法来化解，将团队凝聚起来发挥更大的力量。");
//        }else {
//            stuposiVo4.setAnswer("高乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。他们善于与人沟通合作，在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，他们通常有适合的方法来化解，将团队凝聚起来发挥更大的力量。");
//        }
        list1.add(stuposiVo4);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("8");
        list.add("16");
        list.add("9");
        list.add("19");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo5.setName("成就感");
        stuposiVo5.setNum(16L);
        stuposiVo5.setAnswer("成就感指一个人做完一件事情或者在做一件事情时，为自己所做的事情感到愉快或成功的感觉，即愿望与现实达到平衡产生的一种心理感受。");
        stuposiVo5.setType( i);
//        stuposiVo5.setNum(25L);
//        int i6 = i / 25;
//        if (i6 >= 0.76){
//            stuposiVo5.setAnswer("高感恩利他的人，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
//        }else if (i6> 0.44 && i6 < 0.76){
//            stuposiVo5.setAnswer("要想成为感恩利他的人，你需要对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
//        }else {
//            stuposiVo5.setAnswer("高感恩利他者，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。高感恩利他者，对生活更加满意，行动的动机更强，而且更加健康，睡眠也更加充足，焦虑、抑郁、孤独感都会下降。高感恩利他的青少年更容易融入生活，拥有良好的人际关系，还会赢得更多人的帮助和认可；也更容易接纳自我的成长，有更强烈的目的感、意义感和道德感。");
//        }
        list1.add(stuposiVo5);

        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("6");
        list.add("21");
        list.add("11");
        list.add("14");
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo7.setName("亲子关系");
        stuposiVo7.setNum(16L);
        stuposiVo7.setAnswer("亲子关系是父母与子女之间的一种双向作用的人际关系。亲子关系是一个人最早建立起来的人际关系。父母的人品，对子女的教养、抚养、教育方式以及态度等，都在这种关系中直接对孩子的身心发展产生影响，也将影响子女今后的人际交往关系。亲子关系是个体和社会生活中重要的一部分，在青少年时期，亲子关系面临着打破重建的任务。");
        stuposiVo7.setType( i);
//        stuposiVo7.setNum(30L);
//        int i8 = i / 30;
//        if (i8 >= 0.76){
//            stuposiVo7.setAnswer("领袖力强者对自己严格要求、精益求精，面对责任困难勇于担当，面对未知的事物可以保有热情和信念感，洋溢着乐观自信，拥有令人难以忽视的个人魅力，被很多人莫名地崇拜、喜欢、跟随，因为他们拥有一双伯乐一般的慧眼，善于发现个人身上的优势和特点，并将个人优势和团队目标进行有效地结合，让团队成员充分地意识到自己是有价值、有意义、有目标的；他们擅长运用灵活的激励方式调动团队成员的创新力、自信心、进取心，将每个个体的内在潜力发挥到极致。");
//        }else if (i8> 0.44 && i8 < 0.76){
//            stuposiVo7.setAnswer("领袖力强者拥有令人难以忽视的个人魅力，被很多人莫名地崇拜、喜欢、跟随。要想成为领袖力强的人，需要对自己严格要求、精益求精，面对责任困难勇于担当，面对未知的事物可以保有热情和信念感，洋溢着乐观自信，要善于发现个人身上的优势和特点，并将个人优势和团队目标进行有效地结合，让团队成员充分地意识到自己是有价值、有意义、有目标的；做事也要讲究公平公正、不唯亲不避疏，一切以团队集体的利益和目标为重，深得众人信赖。");
//        }else {
//            stuposiVo7.setAnswer("领袖力强者拥有大局观，对人对事讲究公平公正，对自己严格要求；善于发现个人身上的优势和特点并将其进行有机地结合；擅长灵活地运用激励的方式，有效地调动团队每个成员的创新力、自信心、进取心，善于寻找和激发个体目标，并将个体目标整合浓缩为团队的群体性目标，达到集体的行动效率和效果的最大化直到实现最终目标。");
//        }
        list1.add(stuposiVo7);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
        list.add("17");
        list.add("18");
        list.add("2");
        list.add("22");

        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo8.setName("社交能力");
        stuposiVo8.setNum(16L);
        stuposiVo8.setAnswer("社交能力，指觉察他人情绪意向，有效地理解他人和善于同他人交际的能力，由人际感受力、人际理解力、合作与协调力等方面构成。青少年是一个自我意识和社会需求都不断成长的一个阶段，其社交特点开始慢慢趋近成人。");
        stuposiVo8.setType( i);
//        stuposiVo8.setNum(25L);
//        int i9 = i / 25;
//        if (i9 >= 0.76){
//            stuposiVo8.setAnswer("高抗逆力的人战斗力强，有着坚定的意志和源源不断的斗志。面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。能够将困境所产生的负面影响控制在更小的范围内，并将这种负面影响的程度降至更低，在更短的时间内从困境中恢复。但同时需要小心盲目乐观的陷阱。乐观是建立在客观评价困境、周全考虑问题的基础之上的，为了解决问题，有时可以做最坏的打算、最好的努力。");
//        }else if (i9> 0.44 && i9 < 0.76){
//            stuposiVo8.setAnswer("高抗逆力的人战斗力强，有着坚定的意志和源源不断的斗志。面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。能够将困境所产生的负面影响控制在更小的范围内，并将这种负面影响的程度降至更低，在更短的时间内从困境中恢复。但同时需要小心盲目乐观的陷阱。乐观是建立在客观评价困境、周全考虑问题的基础之上的，为了解决问题，有时可以做最坏的打算、最好的努力。");
//        }else {
//            stuposiVo8.setAnswer("高抗逆力者，在面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。积极主动地做事，并且坚韧灵活，遇到困难不轻言放弃。善于运用批判性思维，针对各种问题提出富有意义的见解。具有较强的自我意识和积极的自我认同。对未来充满信心和希望，对环境具有很强的控制感。清楚地认识到使自己陷入逆境的原因，并甘愿承担责任，具有较强的行动力。");
//        }
        list1.add(stuposiVo8);

        String string = list1.toString();
        String s = resultMap.get("openid");
        answerLocalhost.setOpenid(s);
        answerLocalhost.setTestName("积极心理动力");
        answerLocalhost.setAnswer(string);
        answerLocalhost.setAnswerNum(occurrencesString);

        answerLocalhostService.insertAnswerLocalhost(answerLocalhost);
        AnswerLocalhost answerLocalhost1 = answerLocalhostService.selectAnswerLocalhostById(answerLocalhost.getId());
        stuposiVo9.setTestTime(answerLocalhost1.getTestTime());
        stuposiVo9.setName(answerLocalhost1.getTestName());
        list1.add(stuposiVo9);
        return success(list1);
    }
    private boolean areAllValuesEqual(Map<String, String> map) {
        String keyToRemove = "openid";
        String s = map.get(keyToRemove);

        map.remove(keyToRemove);
        String firstValue = null;
        for (String value : map.values()) {
            if (firstValue == null) {
                firstValue = value;
            } else if (!firstValue.equals(value)) {
                map.put(keyToRemove,s);
                return false; // 发现一个与第一个值不同的值，返回false

            }
        }
        map.put(keyToRemove,s);
        return true; // 所有值都与第一个值相同
    }
    @ApiOperation("压力测评")
    @PostMapping ("/stress")
    public AjaxResult stresslist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        boolean allValuesEqual = areAllValuesEqual(resultMap);

        if (allValuesEqual) {
            return success("不行");
        }

        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String vo : resultMap.values()) {

            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(vo)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(vo, occurrencesMap.get(vo) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(vo, 1);
            }

        }
        String keyToRemove = "openid";
        String s1 = resultMap.get(keyToRemove);
        occurrencesMap.remove(s1);
        StringBuilder stringBuilder = new StringBuilder();


        // 遍历occurrencesMap，并构建字符串
        for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }

        // 删除最后一个逗号和空格
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }



        // 打印结果
        String occurrencesString = stringBuilder.toString();

        AnswerLocalhost answerLocalhost = new AnswerLocalhost();
        List<String> list = new ArrayList<>();
        List<stuposiVo> list1 = new ArrayList<>();
        stuposiVo stuposiVo = new stuposiVo();
        stuposiVo stuposiVo1 = new stuposiVo();

        stuposiVo stuposiVo15 = new stuposiVo();
      Double i = 0.0;
        for (homeVo s: stuposi) {

            String name = s.getName();
            if (name.equals("openid")){
                break;
            }
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionSAnswer(name, type);
        }
        stuposiVo.setType( i);
        stuposiVo.setNum(20L);

        if (i>= 17){
            stuposiVo.setName("超高压力");
//            stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。乐观者更习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！\n" +
//                    "乐观者更愿意面向未来的思考，采取积极行动，不随波逐流。需要注意乐观希望不是自欺欺人的逃避，面向未来的同时需要注意当下能够付出不亚于任何人的努力。");
        }else if (i>= 13 && i<= 16){
            stuposiVo.setName("高压力");
//            stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。所以，要想成为一个乐观的人，要习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！");
        }else if (i>= 9 && i<= 12){
            stuposiVo.setName("中压力");
//            stuposiVo.setAnswer("高乐观希望者在生活中总能以正向的角度看待事物与未来，期待未来会更好，并愿为之努力。在生活中习惯用积极的视角看待生活，遇到困难总是优先看到事件中有利的部分。有着乐观希望品质的人能够积极的解释发生在自己身边的事情，遇事不会自怨自艾，采用“问题为中心”的策略来调整情绪，解决问题");
        }else {
            stuposiVo.setName("低压力");
        }
        list1.add(stuposiVo);
        String string = list1.toString();
        String s = resultMap.get("openid");
        answerLocalhost.setOpenid(s);
        answerLocalhost.setTestName("压力");
        answerLocalhost.setAnswer(string);
        answerLocalhost.setAnswerNum(occurrencesString);

        answerLocalhostService.insertAnswerLocalhost(answerLocalhost);
        AnswerLocalhost answerLocalhost1 = answerLocalhostService.selectAnswerLocalhostById(answerLocalhost.getId());
        stuposiVo15.setTestTime(answerLocalhost1.getTestTime());
        stuposiVo15.setAnswer(answerLocalhost1.getTestName());
        list1.add(stuposiVo15);
        return success(list1);
    }
    private static boolean isInvalidCombination(String combination, String[] invalidCombinations) {
        for (String invalid : invalidCombinations) {
            if (combination.equals(invalid)) {
                return true;
            }
        }
        return false;
    }
    @Autowired
    private IJjxlService jjxlService;
    @ApiOperation("积极心理")
    @PostMapping ("/stuposi")
    public AjaxResult stuposilist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        boolean allValuesEqual = areAllValuesEqual(resultMap);

        if (allValuesEqual) {
            return success("不行");
        }
        String s2 = resultMap.get("4");
        String s3 = resultMap.get("61");
        String[] invalidCombinations = {"AD", "DA", "AE", "EA", "BE", "EB"};
        String answerCombination = s2 + s3;
        if (isInvalidCombination(answerCombination, invalidCombinations)) {
            return success("不行");
        }
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (String vo : resultMap.values()) {

            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(vo)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(vo, occurrencesMap.get(vo) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(vo, 1);
            }

        }
        String keyToRemove = "openid";
        String s1 = resultMap.get(keyToRemove);
        occurrencesMap.remove(s1);
        StringBuilder stringBuilder = new StringBuilder();


        // 遍历occurrencesMap，并构建字符串
        for (Map.Entry<String, Integer> entry : occurrencesMap.entrySet()) {
            stringBuilder.append(entry.getKey())
                    .append(":")
                    .append(entry.getValue())
                    .append(",");
        }

        // 删除最后一个逗号和空格
        if (stringBuilder.length() > 0) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }



        // 打印结果
        String occurrencesString = stringBuilder.toString();

        AnswerLocalhost answerLocalhost = new AnswerLocalhost();
        List<String> list = new ArrayList<>();
        List<stuposiVo> list1 = new ArrayList<>();
        stuposiVo stuposiVo = new stuposiVo();
        stuposiVo stuposiVo1 = new stuposiVo();
        stuposiVo stuposiVo2 = new stuposiVo();
        stuposiVo stuposiVo3 = new stuposiVo();
        stuposiVo stuposiVo4 = new stuposiVo();
        stuposiVo stuposiVo5 = new stuposiVo();
        stuposiVo stuposiVo6 = new stuposiVo();
        stuposiVo stuposiVo7 = new stuposiVo();
        stuposiVo stuposiVo8 = new stuposiVo();
        stuposiVo stuposiVo9 = new stuposiVo();
        stuposiVo stuposiVo10 = new stuposiVo();
        stuposiVo stuposiVo11 = new stuposiVo();
        stuposiVo stuposiVo12 = new stuposiVo();
        stuposiVo stuposiVo13 = new stuposiVo();
        stuposiVo stuposiVo14 = new stuposiVo();
        stuposiVo stuposiVo15 = new stuposiVo();
        list.add("1");
        list.add("13");
        list.add("25");
        list.add("37");
        list.add("49");
        Double i = 0.0;
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
            i += questionAnswerService.selectQuestionAnswer(name, type);
        }

        stuposiVo.setType(i);
        stuposiVo.setNum(25L);
        StringBuilder sr1 = new StringBuilder();
        Double i1 =  (i / 25);
        if (i1 >= 0.76){
            sr1.append("具有很好的乐观希望品质");

            stuposiVo.setName("你的乐观希望得分为" + i + "，" + sr1);
            String s = jjxlService.selectJjxl("乐观希望", "1");
            stuposiVo.setAnswer(s);
        }else if (i1 > 0.44 && i1 < 0.76){
            sr1.append("具有较好的乐观希望品质");

            stuposiVo.setName("你的乐观希望得分为" + i + "，" + sr1);
            String s = jjxlService.selectJjxl("乐观希望", "2");
            stuposiVo.setAnswer(s);
        }else {
            stuposiVo.setName("你的乐观希望得分为" + i);
            String s = jjxlService.selectJjxl("乐观希望", "3");
            stuposiVo.setAnswer(s);
        }
        list1.add(stuposiVo);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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
        StringBuilder sr2 = new StringBuilder();

        stuposiVo1.setType(i);
        stuposiVo1.setNum(25L);
       Double i2 =  (i / 25.0);
        if (i2 >= 0.76){
            sr2.append("具有很好的自信悦纳品质");
            stuposiVo1.setName("你的自信悦纳得分为" + i + "，" + sr2);
            String s = jjxlService.selectJjxl("自信悦纳", "1");
            stuposiVo1.setAnswer(s);
        }else if (i2 > 0.44 && i2 < 0.76){
            sr2.append("具有较好的自信悦纳品质");
            stuposiVo1.setName("你的自信悦纳得分为" + i + "，" + sr2);
            String s = jjxlService.selectJjxl("自信悦纳", "2");
            stuposiVo1.setAnswer(s);
        }else {
            stuposiVo1.setName("你的自信悦纳得分为" + i);
            String s = jjxlService.selectJjxl("自信悦纳", "3");
            stuposiVo1.setAnswer(s);
        }
        list1.add(stuposiVo1);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo2.setType( i);
        stuposiVo2.setNum(30L);
        Double i3 = i / 30;
        StringBuilder sr3 = new StringBuilder();
        if (i3 >= 0.76){
            sr3.append("自控力强");
            stuposiVo2.setName("你的自控力得分为" + i + "，" + sr3);
            String s = jjxlService.selectJjxl("自控力", "1");
            stuposiVo2.setAnswer(s);
        }else if (i3> 0.44 && i3 < 0.76){
            sr3.append("自控力较强");
            stuposiVo2.setName("你的自控力得分为" + i + "，" + sr3);
            String s = jjxlService.selectJjxl("自控力", "2");
            stuposiVo2.setAnswer(s);
        }else {
            sr3.append("自控力一般");
            stuposiVo2.setName("你的自控力得分为" + i + "，" + sr3);
            String s = jjxlService.selectJjxl("自控力", "3");
            stuposiVo2.setAnswer(s);
        }
        list1.add(stuposiVo2);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo3.setType( i);
        stuposiVo3.setNum(25L);
        Double i4 = i / 25;
        StringBuilder sr4 = new StringBuilder();
        if (i4 >= 0.76){
            sr4.append("专注力高");
            stuposiVo3.setName("你的专注力得分为" + i + "，" + sr4);
            String s = jjxlService.selectJjxl("专注力", "1");
            stuposiVo3.setAnswer(s);
        }else if (i4> 0.44 && i4 < 0.76){
            sr4.append("专注力较高");
            stuposiVo3.setName("你的专注力得分为" + i + "，" + sr4);
            String s = jjxlService.selectJjxl("专注力", "2");
            stuposiVo3.setAnswer(s);
        }else {
            sr4.append("专注力一般");
            stuposiVo3.setName("你的专注力得分为" + i + "，" + sr4);
            String s = jjxlService.selectJjxl("专注力", "3");
            stuposiVo3.setAnswer(s);
        }
        list1.add(stuposiVo3);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo4.setType( i);
        stuposiVo4.setNum(30L);
        Double i5 = i / 30;
        StringBuilder sr5 = new StringBuilder();
        if (i5 >= 0.76){
            sr5.append("你能很好的乐群合作");
            stuposiVo4.setName("你的乐群合作得分为" + i + "，" + sr5);
            String s = jjxlService.selectJjxl1("乐群合作", "1");
            stuposiVo4.setAnswer(s);
        }else if (i5> 0.44 && i5 < 0.76){
            sr5.append("你能较好的乐群合作");
            stuposiVo4.setName("你的乐群合作得分为" + i + "，" + sr5);
            String s = jjxlService.selectJjxl1("乐群合作", "2");
            stuposiVo4.setAnswer(s);
        }else {
            stuposiVo4.setName("你的乐群合作得分为" + i );
            String s = jjxlService.selectJjxl1("乐群合作", "3");
            stuposiVo4.setAnswer(s);
        }
        list1.add(stuposiVo4);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo5.setType( i);
        stuposiVo5.setNum(25L);
        Double i6 = i / 25;
        StringBuilder sr6 = new StringBuilder();
        if (i6 >= 0.76){
            sr6.append("具有很强的同理心");
            stuposiVo5.setName("你的同理心得分为" + i + "，" + sr6);
            String s = jjxlService.selectJjxl1("同理心", "1");
            stuposiVo5.setAnswer(s);
        }else if (i6> 0.44 && i6 < 0.76){
            sr6.append("具有较强的同理心");
            stuposiVo5.setName("你的同理心得分为" + i + "，" + sr6);
            String s = jjxlService.selectJjxl1("同理心", "2");
            stuposiVo5.setAnswer(s);
        }else {
            stuposiVo5.setName("你的同理心得分为" + i + "，" + sr6);
            String s = jjxlService.selectJjxl1("同理心", "3");
            stuposiVo5.setAnswer(s);
        }
        list1.add(stuposiVo5);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo6.setType( i);
        stuposiVo6.setNum(30L);
        Double i7 = i / 30;
        StringBuilder sr7 = new StringBuilder();
        if (i7 >= 0.76){
            sr7.append("能很好的感恩利他");
            stuposiVo6.setName("你的感恩利他得分为" + i + "，" + sr7);
            String s = jjxlService.selectJjxl1("感恩利他", "1");
            stuposiVo6.setAnswer(s);
        }else if (i7> 0.44 && i7 < 0.76){
            sr7.append("能较好的感恩利他");
            stuposiVo6.setName("你的感恩利他得分为" + i + "，" + sr7);
            String s = jjxlService.selectJjxl1("感恩利他", "2");
            stuposiVo6.setAnswer(s);
        }else {
            stuposiVo6.setName("你的感恩利他得分为" + i );
            String s = jjxlService.selectJjxl1("感恩利他", "3");
            stuposiVo6.setAnswer(s);
        }
        list1.add(stuposiVo6);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo7.setType( i);
        stuposiVo7.setNum(30L);
        Double i8 = i / 30;
        StringBuilder sr8 = new StringBuilder();
        if (i8 >= 0.76){
            sr8.append("具备很强的领袖力。");
            stuposiVo7.setName("你的领袖力得分为" + i + "，" + sr8);
            String s = jjxlService.selectJjxl1("领袖力", "1");
            stuposiVo7.setAnswer(s);
        }else if (i8> 0.44 && i8 < 0.76){
            sr8.append("具备较强的领袖力。");
            stuposiVo7.setName("你的领袖力得分为" + i + "，" + sr8);
            String s = jjxlService.selectJjxl1("领袖力", "2");
            stuposiVo7.setAnswer(s);
        }else {
            sr8.append("具备一定的领袖力。");
            stuposiVo7.setName("你的领袖力得分为" + i + "，" + sr8);
            String s = jjxlService.selectJjxl1("领袖力", "3");
            stuposiVo7.setAnswer(s);
        }
        list1.add(stuposiVo7);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo8.setType( i);
        stuposiVo8.setNum(25L);
        StringBuilder sr9 = new StringBuilder();
        Double i9 = i / 25;
        if (i9 >= 0.76){
            sr9.append("抗逆力强");
            stuposiVo8.setName("你的抗逆力得分为" + i + "，" + sr9);
            String s = jjxlService.selectJjxl2("抗逆力", "1");
            stuposiVo8.setAnswer(s);
        }else if (i9> 0.44 && i9 < 0.76){
            sr9.append("抗逆力较强");
            stuposiVo8.setName("你的抗逆力得分为" + i + "，" + sr9);
            String s = jjxlService.selectJjxl2("抗逆力", "2");
            stuposiVo8.setAnswer(s);
        }else {
            sr9.append("抗逆力一般");
            stuposiVo8.setName("你的抗逆力得分为" + i + "，" + sr9);
            String s = jjxlService.selectJjxl2("抗逆力", "3");
            stuposiVo8.setAnswer(s);
        }
        list1.add(stuposiVo8);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo9.setType( i);
        stuposiVo9.setNum(25L);
        Double i10 = i / 25;
        StringBuilder sr10 = new StringBuilder();
        if (i10 >= 0.76){
            sr10.append("感悟力敏锐");
            stuposiVo9.setName("你的感悟力得分为" + i + "，" + sr10);
            String s = jjxlService.selectJjxl2("感悟力", "1");
            stuposiVo9.setAnswer(s);
        }else if (i10> 0.44 && i10 < 0.76){
            sr10.append("感悟力较敏锐");
            stuposiVo9.setName("你的感悟力得分为" + i + "，" + sr10);
            String s = jjxlService.selectJjxl2("感悟力", "2");
            stuposiVo9.setAnswer(s);
        }else {
            sr10.append("感悟力较一定");
            stuposiVo9.setName("你的感悟力得分为" + i + "，" + sr10);
            String s = jjxlService.selectJjxl2("感悟力", "3");
            stuposiVo9.setAnswer(s);
        }
        list1.add(stuposiVo9);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo10.setType( i);
        stuposiVo10.setNum(30L);
        Double i11 = i / 30;
        StringBuilder sr11= new StringBuilder();
        if (i11 >= 0.76){
            sr11.append("展现出很强的好奇心");
            stuposiVo10.setName("你的好奇心得分为" + i + "，" + sr11);
            String s = jjxlService.selectJjxl2("好奇心", "1");
            stuposiVo10.setAnswer(s);
        }else if (i11> 0.44 && i11 < 0.76){
            sr11.append("展现出较强的好奇心");
            stuposiVo10.setName("你的好奇心得分为" + i + "，" + sr11);
            String s = jjxlService.selectJjxl2("好奇心", "2");
            stuposiVo10.setAnswer(s);
        }else {
            sr11.append("展现出一定的好奇心");
            stuposiVo10.setName("你的好奇心得分为" + i + "，" + sr11);
            String s = jjxlService.selectJjxl2("好奇心", "3");
            stuposiVo10.setAnswer(s);
        }
        list1.add(stuposiVo10);
        list.clear();
        //stuposiVo = null;
        i = 0.0;
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

        stuposiVo11.setType( i);
        stuposiVo11.setNum(30L);
        Double i12 = i / 30;
        StringBuilder sr12= new StringBuilder();
        if (i12 >= 0.76){
            sr12.append("展现出很强的创造力。");
            stuposiVo11.setName("你的创造力得分为" + i + "，" + sr12);
            String s = jjxlService.selectJjxl2("创造力", "1");
            stuposiVo11.setAnswer(s);
        }else if (i12> 0.44 && i12 < 0.76){
            sr12.append("展现出较强的创造力。");
            stuposiVo11.setName("你的创造力得分为" + i + "，" + sr12);
            String s = jjxlService.selectJjxl2("创造力", "2");
            stuposiVo11.setAnswer(s);
        }else {
            sr12.append("展现出一定的创造力。");
            stuposiVo11.setName("你的创造力得分为" + i + "，" + sr12);
            String s = jjxlService.selectJjxl2("创造力", "3");
            stuposiVo11.setAnswer(s);
        }
        list1.add(stuposiVo11);
        Double l = stuposiVo.getType() + stuposiVo1.getType() + stuposiVo2.getType() + stuposiVo3.getType();
        stuposiVo12.setName("自我认知");
        StringBuilder stringBuilder1 = new StringBuilder();
        StringBuilder stringBuilder2 = new StringBuilder();
        StringBuilder stringBuilder3 = new StringBuilder();
        stuposiVo12.setType(l);
        stuposiVo12.setNum(105L);
        stringBuilder1.append("你的自我认知意识");
        double v = l / 105;
        if (v >= 0.76){
            stringBuilder1.append("强，");
        }else if (v> 0.44 && v < 0.76){
            stringBuilder1.append("较强，");
        }else {
            stringBuilder1.append("一般，");
        }

        if (sr1 != null && sr1.length() > 0) {

            stringBuilder1.append(sr1);
        }
        if (sr2 != null && sr2.length() > 0) {
            // 将逗号和 sr2 的内容追加到 stringBuilder1 中
            stringBuilder1.append(",");
            stringBuilder1.append(sr2);
        }

        if (sr3 != null && sr3.length() > 0) {
            stringBuilder1.append(",");
            stringBuilder1.append(sr3);
        }

        if (sr4 != null && sr4.length() > 0) {
            stringBuilder1.append(",");
            stringBuilder1.append(sr4);
        }
        stuposiVo12.setAnswer(stringBuilder1.toString());
        list1.add(stuposiVo12);
        Double l1 = stuposiVo4.getType() + stuposiVo5.getType() + stuposiVo6.getType() + stuposiVo7.getType();
        stuposiVo13.setName("社会协作");
        stuposiVo13.setType(l1);
        stuposiVo13.setNum(115L);
        stringBuilder2.append("你的社会协作能力");
        double v1 = l1 / 115;
        if (v1 >= 0.76){
            stringBuilder2.append("强，");
        }else if (v1> 0.44 && v1 < 0.76){
            stringBuilder2.append("较强，");
        }else {
            stringBuilder2.append("一般，");
        }
        if (sr5 != null && sr5.length() > 0) {

            stringBuilder2.append(sr5);
        }
        if (sr6 != null && sr6.length() > 0) {
            // 将逗号和 sr2 的内容追加到 stringBuilder1 中
            stringBuilder2.append(",");
            stringBuilder2.append(sr6);
        }

        if (sr7 != null && sr7.length() > 0) {
            stringBuilder2.append(",");
            stringBuilder2.append(sr7);
        }

        if (sr8 != null && sr8.length() > 0) {
            stringBuilder2.append(",");
            stringBuilder2.append(sr8);
        }
        stuposiVo13.setAnswer(stringBuilder2.toString());
        list1.add(stuposiVo13);
        Double l2 = stuposiVo8.getType() + stuposiVo9.getType() + stuposiVo10.getType() + stuposiVo11.getType();
        stuposiVo14.setName("人际发展");
        stuposiVo14.setType(l2);
        stuposiVo14.setNum(110L);
        stringBuilder3.append("你的人际发展能力");
        double v2 = l2 / 110;
        if (v2 >= 0.76){
            stringBuilder3.append("高，");
        }else if (v2> 0.44 && v2 < 0.76){
            stringBuilder3.append("较高，");
        }else {
            stringBuilder3.append("一般，");
        }
        if (sr9 != null && sr9.length() > 0) {

            stringBuilder3.append(sr9);
        }
        if (sr10 != null && sr10.length() > 0) {
            // 将逗号和 sr2 的内容追加到 stringBuilder1 中
            stringBuilder3.append(",");
            stringBuilder3.append(sr10);
        }

        if (sr11 != null && sr11.length() > 0) {
            stringBuilder3.append(",");
            stringBuilder3.append(sr11);
        }

        if (sr12 != null && sr12.length() > 0) {
            stringBuilder3.append(",");
            stringBuilder3.append(sr12);
        }
        stuposiVo14.setAnswer(stringBuilder3.toString());
        list1.add(stuposiVo14);
        String string = list1.toString();
        String s = resultMap.get("openid");
        answerLocalhost.setOpenid(s);
        answerLocalhost.setTestName("青少年积极心理品质");
        answerLocalhost.setAnswer(string);
        answerLocalhost.setAnswerNum(occurrencesString);

        answerLocalhostService.insertAnswerLocalhost(answerLocalhost);
        AnswerLocalhost answerLocalhost1 = answerLocalhostService.selectAnswerLocalhostById(answerLocalhost.getId());
        stuposiVo15.setTestTime(answerLocalhost1.getTestTime());
        stuposiVo15.setAnswer(answerLocalhost1.getTestName());
        list1.add(stuposiVo15);
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
