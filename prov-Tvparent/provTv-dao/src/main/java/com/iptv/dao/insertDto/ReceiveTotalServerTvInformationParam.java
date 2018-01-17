package com.iptv.dao.insertDto;

import com.iptv.model.tv.helper.TvBase;
import lombok.Data;

import java.util.List;

/**
 * Created by w_z91 on 2017/4/19.
 */
@Data
public class ReceiveTotalServerTvInformationParam {

    /**
     * 电视基础信息列表
     */
    private List<TvBase> list;



}
