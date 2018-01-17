package com.iptv.dao.insertDto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;
import javax.validation.constraints.Min;

@Data
public class TvInsertParam {

    /*
	 * 电视mac地址
	 */
    @NotBlank(message = "mac地址不能为空")
    private String mac;

    /*
     * 用户名
     */
    @NotBlank(message = "用户名不能为空")
    private String userName;

    /*
     * 密码
     */
    @NotBlank(message = "密码不能为空")
    private String password;

    /*
     * 健全地址
     */
    @NotBlank(message = "健全地址不能为空")
    private String soundServerAddress;

    /*
     * 升级服务器地址
     */
    @NotBlank(message = "升级服务器地址不能为空")
    private String upgradeServerAddress;

    /*
     * 平台类型
     */
    @Min(value = 1,message = "平台类型必须大于0")
    private byte platformType;

}
