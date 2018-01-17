package com.iptv.dao.searchDto;

import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

@Data
public class TvSearchParam {
    @NotBlank(message = "mac地址不能为空")
    private String mac;

    //省份id
    @Min(value = 1,message = "省份编码必须大于0")
    private int provId;

}
