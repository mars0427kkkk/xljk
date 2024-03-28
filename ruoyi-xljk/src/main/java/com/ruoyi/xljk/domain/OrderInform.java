package com.ruoyi.xljk.domain;

import lombok.Data;
import lombok.ToString;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.util.Date;

/**
 * @author pocky
 * @date 2021/3/26
 */
@Entity
@Data
@ToString
@Table(name = "order_inform")
@DynamicUpdate
@DynamicInsert
public class OrderInform {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;

    private String openId;

    private Boolean flags;

    private String orderId;

    private String prepayId;

    private double money;

    @Column(name = "describe_info")
    private String describeInfo;

    @CreatedDate
    private Date createTime;

    @LastModifiedDate
    private Date updateTime;
}
