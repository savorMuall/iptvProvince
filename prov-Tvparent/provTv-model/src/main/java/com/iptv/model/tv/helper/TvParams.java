package com.iptv.model.tv.helper;

import lombok.Data;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project
 * @Description 页面展示的电视信息数据
 * @encoding UTF-8
 * @date 2017/05/17
 * @time 20:54
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Data
public class TvParams {

    /**
     *     电视的表主键id
     */
    private int id;

    /*
     * 电视mac地址
     */
    private String mac;


}
