package com.iptv.model.bindingPlaces.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class BindingPlaces implements Serializable{

    private Integer id;

    //省份编码
    private Integer provId;

    //批次号
    private Integer batchNumber;

    //名额数量
    private Integer number;

    //发布平台（1总部发布 2省份发布）
    private Byte publishingPlatform;

    //操作者
    private Integer operator;

    //1生效  2强制失效 3号码用光
    private Byte state;

    private Date createTime;

    private Date updateTime;

}