package com.iptv.model.platform.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author wangzhan  <wangzhan, w_z911@163.comm>
 * @version v1.0
 * @project provTv-model
 * @Description
 * @encoding UTF-8
 * @date 2017/5/17
 * @time 下午7:29
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Data
public class Platform {

    /**
     * 平台表主键id
     */
    private Integer id;

    /**
     * 平台名称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}