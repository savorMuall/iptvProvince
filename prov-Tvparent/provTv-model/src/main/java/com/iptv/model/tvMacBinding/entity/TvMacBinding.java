package com.iptv.model.tvMacBinding.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TvMacBinding implements Serializable {

    private Integer id;

    //mac
    private String mac;

    //账号
    private String accountNumber;

    //省份id
    private Integer provId;

    //ip地址
    private String ipAddress;

    //绑定批次号
    private Integer batchNumber;

    //编号
    private Integer number;

    //号码是否被重用 1否 2 是
    private Byte isReusing;
    //1 暂存 2 生效  3撤销  4过期
    private Byte state;

    private Date updateTime;

    private Date createTime;

}