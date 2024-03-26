package com.ruoyi.xljk.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.xljk.domain.AnswerLocalhost;
import com.ruoyi.xljk.domain.Vo.stuposiVo;
import com.ruoyi.xljk.domain.correctionalMode;
import com.ruoyi.xljk.domain.Vo.homeVo;
import com.ruoyi.xljk.domain.Vo.nameVo;
import com.ruoyi.xljk.domain.positiveNature;
import com.ruoyi.xljk.service.IAnswerLocalhostService;
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

    @ApiOperation("积极心理")
    @PostMapping ("/stuposis")
    public AjaxResult stuposislist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (homeVo vo : stuposi) {
            String name = vo.getType();
            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(name)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(name, occurrencesMap.get(name) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(name, 1);
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
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
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

        list.add("1");
        list.add("3");
        list.add("4");
        list.add("7");
        list.add("10");
        list.add("15");
        list.add("20");
        list.add("23");
        int i = 0;
        for (String s: list) {
            String name = s;
            String type = resultMap.get(name);
             i += questionAnswerService.selectQuestionAnswers(name, type);
        }
        stuposiVo.setName("生命动力");
        stuposiVo.setType((long) i);
//        stuposiVo.setNum(25L);
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
        i = 0;
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
        stuposiVo3.setType((long) i);
//        stuposiVo3.setNum(25L);
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
        i = 0;
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
        stuposiVo6.setType((long) i);
//        stuposiVo6.setNum(30L);
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
        i = 0;
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
        stuposiVo1.setType((long) i);
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
        i = 0;
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
        stuposiVo2.setType((long) i);
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
        i = 0;
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
        stuposiVo4.setType((long) i);
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
        i = 0;
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
        stuposiVo5.setType((long) i);
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
        i = 0;
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
        stuposiVo7.setType((long) i);
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
        i = 0;
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
        stuposiVo8.setType((long) i);
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
        return success(list1);
    }
    @ApiOperation("积极心理")
    @PostMapping ("/stuposi")
    public AjaxResult stuposilist(@RequestBody List<homeVo> stuposi){
        Map<String, String> resultMap = convertListToMap(stuposi);
        Map<String, Integer> occurrencesMap = new HashMap<>();

        for (homeVo vo : stuposi) {
            String name = vo.getType();
            // 检查映射中是否已经存在该字符串
            if (occurrencesMap.containsKey(name)) {
                // 如果存在，则将其出现次数加1
                occurrencesMap.put(name, occurrencesMap.get(name) + 1);
            } else {
                // 如果不存在，则将其初始出现次数设为1
                occurrencesMap.put(name, 1);
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
        if (stringBuilder.length() > 2) {
            stringBuilder.setLength(stringBuilder.length() - 2);
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
        int i1 = i / 25;
        if (i1 >= 0.76){
            stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。乐观者更习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！\n" +
                    "乐观者更愿意面向未来的思考，采取积极行动，不随波逐流。需要注意乐观希望不是自欺欺人的逃避，面向未来的同时需要注意当下能够付出不亚于任何人的努力。");
        }else if (i1 > 0.44 && i1 < 0.76){
            stuposiVo.setAnswer("高乐观希望者的生活仿佛自带阳光滤镜，在遇到不同的情况时，总能保持良好的心态，拥有相信坏事会过去，阳光总会到来的积极心境。所以，要想成为一个乐观的人，要习惯用积极的视角看待生活，优先看到事件中有利的部分。积极的生活状态带来健康的习惯和健康的身体，研究发现乐观希望品质突出的人更少生病哦！");
        }else {
            stuposiVo.setAnswer("高乐观希望者在生活中总能以正向的角度看待事物与未来，期待未来会更好，并愿为之努力。在生活中习惯用积极的视角看待生活，遇到困难总是优先看到事件中有利的部分。有着乐观希望品质的人能够积极的解释发生在自己身边的事情，遇事不会自怨自艾，采用“问题为中心”的策略来调整情绪，解决问题");
        }
        list1.add(stuposiVo);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo1.setName("自信悦纳");
        stuposiVo1.setType((long) i);
        stuposiVo1.setNum(25L);
        int i2 = i / 25;
        if (i2 >= 0.76){
            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。高自信悦纳者，能够积极地评价自己并相信自己有能力把事情做好。社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。能够正确地评价自己、接纳自己，并在此基础上使自我得到良好的发展。");
        }else if (i2 > 0.44 && i2 < 0.76){
            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。要想成为一个自信悦纳的人，要能够积极地评价自己并相信自己有能力把事情做好。要社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。能够正确地评价自己、接纳自己，并在此基础上使自我得到良好的发展。");
        }else {
            stuposiVo1.setAnswer("自信悦纳是一个人对自己是否有能力达成特定任务，如何善用所拥有技能的一种信念。高自信悦纳者，能够积极地评价自己并相信自己有能力把事情做好。社会适应良好，积极情感体验丰富，个人自主性强，自我认识清晰，善于应对批评或负面反馈。");
        }
        list1.add(stuposiVo1);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo2.setName("自控力");
        stuposiVo2.setType((long) i);
        stuposiVo2.setNum(30L);
        int i3 = i / 30;
        if (i3 >= 0.76){
            stuposiVo2.setAnswer("在以下4个方面，高自控力者总是让人心生羡慕：\n" +
                    "① 延迟满足。为了达到长期的目标，高自控力者大多数时候都能够抵御眼前的诱惑。\n" +
                    "② 及时喊停。高自控力者总是能够推翻那些无用甚至有害的想法、感觉和冲动的能力。\n" +
                    "③ 冷静思考。在行动的时候，高自控力者更多三思而后行。头脑发热一时冲动？不存在的。\n" +
                    "④ 超强自制。高自控力者在大多数时候总是能够有意识地给自我设定规则，并努力遵守。");
        }else if (i3> 0.44 && i3 < 0.76){
            stuposiVo2.setAnswer("要想成为高自控力者，要在以下4个方面下功夫：\n" +
                    "① 延迟满足。为了达到长期的目标，高自控力者大多数时候都能够抵御眼前的诱惑。\n" +
                    "② 及时喊停。高自控力者总是能够推翻那些无用甚至有害的想法、感觉和冲动的能力。\n" +
                    "③ 冷静思考。在行动的时候，高自控力者更多三思而后行。头脑发热一时冲动？不存在的。\n" +
                    "④ 超强自制。高自控力者在大多数时候总是能够有意识地给自我设定规则，并努力遵守。");
        }else {
            stuposiVo2.setAnswer("高自控力的人在以下4个方面表现良好：延迟满足的能力，即为了达到长期的目标，而能够抵御眼前的诱惑的能力；能够推翻那些无用甚至有害的想法、感觉和冲动的能力；在行动的时候，采用一种更为冷静的认知系统，而不是一种冲动的情绪系统的能力；能够有意识地给自我设定规则，并努力遵守。");
        }
        list1.add(stuposiVo2);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo3.setName("专注力");
        stuposiVo3.setType((long) i);
        stuposiVo3.setNum(25L);
        int i4 = i / 25;
        if (i4 >= 0.76){
            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。他们不会眼皮底下的东西都找不着，不会丢三落四。他们的作业写得仔细认真，考试时也很少出现漏题、丢词少句、漏看小数点等“马虎”问题。");
        }else if (i4> 0.44 && i4 < 0.76){
            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。专注力高的人也更加独立。由于做事专心致志，所以培养了很强的自理能力。在学习中遇到问题也会积极解决。");
        }else {
            stuposiVo3.setAnswer("专注力高的人办事稳当妥帖。他们不会眼皮底下的东西都找不着，不会丢三落四。他们的作业写得仔细认真，考试时也很少出现漏题、丢词少句、漏看小数点等“马虎”问题。由于注意力集中，他们阅读文章的速度快，还能清楚地辨认一些细节。");
        }
        list1.add(stuposiVo3);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo4.setName("乐群合作");
        stuposiVo4.setType((long) i);
        stuposiVo4.setNum(30L);
        int i5 = i / 30;
        if (i5 >= 0.76){
            stuposiVo4.setAnswer("乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。他们善于与人沟通合作，在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，他们通常有适合的方法来化解，将团队凝聚起来发挥更大的力量。");
        }else if (i5> 0.44 && i5 < 0.76){
            stuposiVo4.setAnswer("乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。所以，我们要善于与人沟通合作，这样在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，要找到适合的方法来化解，将团队凝聚起来发挥更大的力量。");
        }else {
            stuposiVo4.setAnswer("高乐群合作者乐于在集体中发挥自己的能力，是团队中真诚可靠的伙伴。他们善于与人沟通合作，在集体中也往往更受欢迎和喜爱。对于人际关系中的矛盾和冲突，他们通常有适合的方法来化解，将团队凝聚起来发挥更大的力量。");
        }
        list1.add(stuposiVo4);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo5.setName("同理心");
        stuposiVo5.setType((long) i);
        stuposiVo5.setNum(25L);
        int i6 = i / 25;
        if (i6 >= 0.76){
            stuposiVo5.setAnswer("高感恩利他的人，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
        }else if (i6> 0.44 && i6 < 0.76){
            stuposiVo5.setAnswer("要想成为感恩利他的人，你需要对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
        }else {
            stuposiVo5.setAnswer("高感恩利他者，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。高感恩利他者，对生活更加满意，行动的动机更强，而且更加健康，睡眠也更加充足，焦虑、抑郁、孤独感都会下降。高感恩利他的青少年更容易融入生活，拥有良好的人际关系，还会赢得更多人的帮助和认可；也更容易接纳自我的成长，有更强烈的目的感、意义感和道德感。");
        }
        list1.add(stuposiVo5);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo6.setName("感恩利他");
        stuposiVo6.setType((long) i);
        stuposiVo6.setNum(30L);
        int i7 = i / 30;
        if (i7 >= 0.76){
            stuposiVo6.setAnswer("高感恩利他的人，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
        }else if (i7> 0.44 && i7 < 0.76){
            stuposiVo6.setAnswer("要想成为感恩利他的人，你需要对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。这种感恩利他的品格，会让人更关注自己拥有什么，也更加愿意分享。当我们感谢我们所拥有的东西的时候，内心会充满一种满足、幸福、意义和仁爱之心。当我们将所拥有的东西分享给他人的时候，会获得激情、福流、信任、超越、意义感、归属感、安全感等一系列积极的情绪。");
        }else {
            stuposiVo6.setAnswer("高感恩利他者，对他人的恩惠常怀有感恩之心，常在明知没有回报的情况下，贡献自己的时间、精力、金钱等。高感恩利他者，对生活更加满意，行动的动机更强，而且更加健康，睡眠也更加充足，焦虑、抑郁、孤独感都会下降。高感恩利他的青少年更容易融入生活，拥有良好的人际关系，还会赢得更多人的帮助和认可；也更容易接纳自我的成长，有更强烈的目的感、意义感和道德感。");
        }
        list1.add(stuposiVo6);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo7.setName("领袖力");
        stuposiVo7.setType((long) i);
        stuposiVo7.setNum(30L);
        int i8 = i / 30;
        if (i8 >= 0.76){
            stuposiVo7.setAnswer("领袖力强者对自己严格要求、精益求精，面对责任困难勇于担当，面对未知的事物可以保有热情和信念感，洋溢着乐观自信，拥有令人难以忽视的个人魅力，被很多人莫名地崇拜、喜欢、跟随，因为他们拥有一双伯乐一般的慧眼，善于发现个人身上的优势和特点，并将个人优势和团队目标进行有效地结合，让团队成员充分地意识到自己是有价值、有意义、有目标的；他们擅长运用灵活的激励方式调动团队成员的创新力、自信心、进取心，将每个个体的内在潜力发挥到极致。");
        }else if (i8> 0.44 && i8 < 0.76){
            stuposiVo7.setAnswer("领袖力强者拥有令人难以忽视的个人魅力，被很多人莫名地崇拜、喜欢、跟随。要想成为领袖力强的人，需要对自己严格要求、精益求精，面对责任困难勇于担当，面对未知的事物可以保有热情和信念感，洋溢着乐观自信，要善于发现个人身上的优势和特点，并将个人优势和团队目标进行有效地结合，让团队成员充分地意识到自己是有价值、有意义、有目标的；做事也要讲究公平公正、不唯亲不避疏，一切以团队集体的利益和目标为重，深得众人信赖。");
        }else {
            stuposiVo7.setAnswer("领袖力强者拥有大局观，对人对事讲究公平公正，对自己严格要求；善于发现个人身上的优势和特点并将其进行有机地结合；擅长灵活地运用激励的方式，有效地调动团队每个成员的创新力、自信心、进取心，善于寻找和激发个体目标，并将个体目标整合浓缩为团队的群体性目标，达到集体的行动效率和效果的最大化直到实现最终目标。");
        }
        list1.add(stuposiVo7);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo8.setName("抗逆力");
        stuposiVo8.setType((long) i);
        stuposiVo8.setNum(25L);
        int i9 = i / 25;
        if (i9 >= 0.76){
            stuposiVo8.setAnswer("高抗逆力的人战斗力强，有着坚定的意志和源源不断的斗志。面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。能够将困境所产生的负面影响控制在更小的范围内，并将这种负面影响的程度降至更低，在更短的时间内从困境中恢复。但同时需要小心盲目乐观的陷阱。乐观是建立在客观评价困境、周全考虑问题的基础之上的，为了解决问题，有时可以做最坏的打算、最好的努力。");
        }else if (i9> 0.44 && i9 < 0.76){
            stuposiVo8.setAnswer("高抗逆力的人战斗力强，有着坚定的意志和源源不断的斗志。面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。能够将困境所产生的负面影响控制在更小的范围内，并将这种负面影响的程度降至更低，在更短的时间内从困境中恢复。但同时需要小心盲目乐观的陷阱。乐观是建立在客观评价困境、周全考虑问题的基础之上的，为了解决问题，有时可以做最坏的打算、最好的努力。");
        }else {
            stuposiVo8.setAnswer("高抗逆力者，在面对生活逆境、创伤、悲剧、威胁和其他生活重大压力事件时能够呈现出良好的适应能力。积极主动地做事，并且坚韧灵活，遇到困难不轻言放弃。善于运用批判性思维，针对各种问题提出富有意义的见解。具有较强的自我意识和积极的自我认同。对未来充满信心和希望，对环境具有很强的控制感。清楚地认识到使自己陷入逆境的原因，并甘愿承担责任，具有较强的行动力。");
        }
        list1.add(stuposiVo8);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo9.setName("感悟力");
        stuposiVo9.setType((long) i);
        stuposiVo9.setNum(25L);
        int i10 = i / 25;
        if (i10 >= 0.76){
            stuposiVo9.setAnswer("高感悟力者具有独特的鉴赏力和审美力，相信美好、见证美好的存在，在平平无奇的自然事物中感知到真、善、美的真谛。无论是对人、事物、景色、影像、音乐还是物品，都具有独到的观察力、理解力和思考力，能够引发情感的升华，思想的启迪，思维认知层面上的飞跃。");
        }else if (i10> 0.44 && i10 < 0.76){
            stuposiVo9.setAnswer("无论是对人、事物、景色、影像、音乐还是物品，都具有独到的观察力、理解力和思考力，能够引发情感的升华，思想的启迪，思维认知层面上的飞跃。感悟力的升级就是鉴赏力和审美力的升级，是相信美好、见证美好的存在，在平平无奇的自然事物中感知到真、善、美的真谛。");
        }else {
            stuposiVo9.setAnswer("高感悟力者，更能够在习以为常的事物中感知到真、善、美的存在，具备别人领悟不到的领悟力，具有敏锐的观察能力。优越的观察力、思考力和理解能力，可以在自然事物中从细微处着手，捕捉和欣赏事物的美好，在人际交往中看到别人的优点，形成自我独到的见解及表现形式，达到情感的升华，思想的启迪，思维认知层面的飞跃及升华。");
        }
        list1.add(stuposiVo9);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo10.setName("好奇心");
        stuposiVo10.setType((long) i);
        stuposiVo10.setNum(30L);
        int i11 = i / 30;
        if (i11 >= 0.76){
            stuposiVo10.setAnswer("好奇心强的人看什么都新鲜，什么都想知道、都想学。好奇心强者善于观察，能够通过观察周围的事物找到自己的兴趣，然后追踪下去。");
        }else if (i11> 0.44 && i11 < 0.76){
            stuposiVo10.setAnswer("好奇心强的人看什么都新鲜，什么都想知道、都想学。要想培养自己的好奇心，首先需要善于观察，能够通过观察周围的事物找到自己的兴趣，然后追踪下去。");
        }else {
            stuposiVo10.setAnswer("好奇心强的人看什么都新鲜，什么都想知道、都想学。他们通常都善于观察。他们能够通过观察周围的事物找到自己的兴趣，然后追踪下去。");
        }
        list1.add(stuposiVo10);
        list.clear();
        //stuposiVo = null;
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
        stuposiVo11.setName("创造力");
        stuposiVo11.setType((long) i);
        stuposiVo11.setNum(30L);
        int i12 = i / 30;
        if (i12 >= 0.76){
            stuposiVo11.setAnswer("高创造力的创新者思维随机应变，举一反三，不易受功能固着等心理定势的干扰，因此能产生超常的构想，提出新观念。反应既快又多，能够在较短的时间内表达出较多的观念。对事物具有不同寻常的独特见解，能够利用已有定论的原理、定律、规律、方法，创造性地、更好地解决问题。但是要注意，创造力不是妄想，它建立在知识经验的基础之上。");
        }else if (i12> 0.44 && i12 < 0.76){
            stuposiVo11.setAnswer("成为高创造力需要随机应变，举一反三，产生超常的构想，提出新观念，对事物具有不同寻常的独特见解，能够利用已有定论的原理、定律、规律、方法，创造性地、更好地解决问题。但是要注意，创造力不是妄想，它建立在知识经验的基础之上。辩证地认识知识经验对创造力的双重作用，对现有知识经验批判地继承，弱化习惯性思维定势的影响，在借鉴中有所突破，有所创新，使现有的知识经验能在创新活动中发挥正面的作用。");
        }else {
            stuposiVo11.setAnswer("高创造力者，思维随机应变，举一反三，不易受功能固着等心理定势的干扰，能够在较短的时间内表达出较多的观念，对事物具有不寻常的独特见解。他们既善于通过独处思考孵化复杂念头，又善于通过社交采集衡量各种信息。他们具有很高的想象力和独创性，同时又有很强的现实感，使想象不会脱离实际。他们兴趣广泛，有很强的玩儿心，喜欢冒险，但同时又自律专注，效率很高。");
        }
        list1.add(stuposiVo11);
        long l = stuposiVo.getType() + stuposiVo1.getType() + stuposiVo2.getType() + stuposiVo3.getType();
        stuposiVo12.setName("自我认知");
        stuposiVo12.setType(l);
        list1.add(stuposiVo12);
        long l1 = stuposiVo4.getType() + stuposiVo5.getType() + stuposiVo6.getType() + stuposiVo7.getType();
        stuposiVo13.setName("社会协作");
        stuposiVo13.setType(l1);
        list1.add(stuposiVo13);
        long l2 = stuposiVo8.getType() + stuposiVo9.getType() + stuposiVo10.getType() + stuposiVo11.getType();
        stuposiVo14.setName("人际发展");
        stuposiVo14.setType(l2);
        list1.add(stuposiVo14);
        String string = list1.toString();
        String s = resultMap.get("openid");
        answerLocalhost.setOpenid(s);
        answerLocalhost.setTestName("青少年积极心理品质");
        answerLocalhost.setAnswer(string);
        answerLocalhost.setAnswerNum(occurrencesString);

        answerLocalhostService.insertAnswerLocalhost(answerLocalhost);
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
