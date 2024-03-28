package com.ruoyi.xljk.wxnpay.service;

//import com.ruoyi.commons.utils.DateUtils;
//import com.ruoyi.enums.ResultEnum;
//import com.ruoyi.enums.UserEnum;
//import com.ruoyi.exception.SdcException;
//import com.ruoyi.repository.mysql.user.dao.AppUserDao;
//import com.ruoyi.repository.mysql.user.dao.OrderInformDao;
//import com.ruoyi.repository.mysql.user.model.*;
//import com.ruoyi.service.weixin.UserWXService;
import com.ruoyi.xljk.domain.OrderInform;
import com.ruoyi.xljk.wxnpay.Dao.OrderInformDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
//
//import static com.ruoyi.commons.utils.DateUtils.YYYY_MM_DD_HH_MM_SS;

/**
 * @author pocky
 * @date 2021/3/26
 */
@Service
@Slf4j
public class OrderInformService {
    @Autowired
    private OrderInformDao orderInformDao;

//    @Autowired
//    private AppUserDao appUserDao;
//
//    @Autowired
//    private UserWXService userWXService;

//    public OrderInform findByOrderId(String orderId){
//        OrderInform byOrderId = orderInformDao.findByOrderId(orderId);
//        if (byOrderId==null){
//            throw new SdcException(ResultEnum.INFO_NOTFOUND_EXCEPTION);
//        }
//        return byOrderId;
//    }
//
//    public OrderInform paid(OrderInform orderInform) throws ParseException {
//        if (orderInform.getFlags()){
//            log.error("用户已经支付");
//            throw new SdcException(UserEnum.user_is_pay);
//        }
//        //将支付状态改为已经支付
//        orderInform.setFlags(true);
//        OrderInform save = orderInformDao.save(orderInform);
//        int i=0;
//        while (save==null && i<3){
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            log.error("第"+(i++)+"次插入数据库错误");
//            orderInformDao.save(orderInform);
//        }
//        if (save==null){
//            log.error("插入数据库错误："+orderInform);
//            throw new SdcException(ResultEnum.DATABASE_OP_EXCEPTION);
//        }
//        String openid = save.getOpenId();
//
//        AppUser byOpenId = appUserDao.findByOpenId(openid);
//        byOpenId.setVip(1);
//        String nowTimeStr = DateUtils.parseDateToStr(YYYY_MM_DD_HH_MM_SS, new Date());
//        String time = nowTimeStr.substring(10, 19);
//        String endTimeStr = "2022-09-01" + time;
//        byOpenId.setEndTime(DateUtils.parseDate(endTimeStr));
//        appUserDao.save(byOpenId);
//        return save;
//    }

    public OrderInform getOrderInformByOpenId(String openid) {

        return orderInformDao.getOrderByOpenId(openid);
    }

    public OrderInform createNewOrderInform(OrderInform orderInform) {
        return orderInformDao.save(orderInform);
    }

    public void updateOrderInformOfPrepayId(OrderInform orderInform) {
        orderInformDao.save(orderInform);
    }

    public OrderInform getOrderInformByOrderId(String orderNo) {
        return orderInformDao.getByOrderId(orderNo);
    }

    public void updateOrderInformOfFlags(OrderInform orderInform) {
        orderInformDao.save(orderInform);
    }

    public void deleteOrderInformById(int id) {
        orderInformDao.deleteById(id);
    }
}
