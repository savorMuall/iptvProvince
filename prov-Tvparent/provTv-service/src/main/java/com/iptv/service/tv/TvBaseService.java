package com.iptv.service.tv;


import com.iptv.model.tv.helper.TvBase;

import java.util.List;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视基本信息service
 * @encoding UTF-8
 * @date 2017/03/28
 * @time 14:32
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
public interface TvBaseService {
	
	int deleteByPrimaryKey(int id);

    int insert(TvBase record);

    int insertSelective(TvBase record);

    TvBase selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(TvBase record);

    int updateByPrimaryKey(TvBase record);

    /**
     * 根据电视mac地址查询电视基本信息
     * @param mac
     * @return
     */
    TvBase getTvBaseByMac(String mac);

    /**
     * 根据时间获取电视基本信息列表
     * @param beginTime
     * @param endTime
     * @return
     */
    List<TvBase> getTvBasesByTime(String beginTime, String endTime);

    /**
     * 批量入库电视信息
     * @param tvBases
     */
    void insertList(List<TvBase> tvBases);
	
}
