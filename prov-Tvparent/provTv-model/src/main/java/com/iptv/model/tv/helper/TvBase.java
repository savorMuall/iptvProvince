package com.iptv.model.tv.helper;

import lombok.Data;

import java.util.Date;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视基础信息表entity
 * @encoding UTF-8
 * @date 2017/03/28
 * @time 14:23
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Data
public class TvBase {

	/*
	 * 主键id
	 */
	private int id;

	/*
	 * 电视mac地址
	 */
    private String mac;

    /*
     * 用户名
     */
    private String userName;

    /*
     * 密码
     */
    private String password;

    /*
     * 健全地址
     */
    private String soundServerAddress;

    /*
     * 升级服务器地址
     */
    private String upgradeServerAddress;

    /*
     * 平台类型
     */
    private byte platformType;

    /*
     * 激活状态（1->激活、2->未激活、3->冻结）
     */
    private byte activationType;
    
	//创建时间
	private Date createTime;
	
	//更新时间
	private Date updateTime;

	public String toString(){
	    return "{\"id\":\""+id+"\",\"mac\":\""+mac+"\",\"userName\":\""+userName+"\",\"password\":\""+password+"\"," +
                " \"soundServerAddress\":\""+soundServerAddress+"\",\"upgradeServerAddress\":\""+upgradeServerAddress+"\",\"platformType\":\""+platformType+"\" }";

    }
	
}
