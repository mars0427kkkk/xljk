package com.ruoyi.xljk.wxnpay.Dao;


import com.ruoyi.xljk.domain.OrderInform;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author pocky
 * @date 2021/3/26
 */
public interface OrderInformDao extends JpaRepository<OrderInform,Integer> {
    OrderInform findByOrderId(String orderId);

    @Query(value = "SELECT * FROM order_inform WHERE openId=?1 AND flags=1 ORDER BY createTime",nativeQuery = true)
    List<OrderInform> findByOpenId(String openId);
    
    @Query(value = "select * from order_inform where orderId = ?1", nativeQuery = true)
    OrderInform getByOrderId(String orderNo);

    @Query(value = "select * from order_inform where  openId = ?1", nativeQuery = true)
    OrderInform getOrderByOpenId(String openid);
}
