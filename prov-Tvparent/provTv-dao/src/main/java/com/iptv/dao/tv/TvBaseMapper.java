package com.iptv.dao.tv;

import com.iptv.model.tv.helper.TvBase;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视基本信息mapper
 * @encoding UTF-8
 * @date 2017/03/30
 * @time 13:51
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Repository
public interface TvBaseMapper {

	int deleteByPrimaryKey(int id);

    int insert(TvBase record);

    int insertSelective(TvBase record);

    TvBase selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(TvBase record);

    int updateByPrimaryKey(TvBase record);
    
    /**
     * 根据电视mac地址查询电视基本信息
     * @param mac
     * @return TvBase
     */
     TvBase getTvBaseByMac(final @Param("mac") String mac);

    /**
     * 根据时间获取电视基本信息列表
     * @param beginTime
     * @param endTime
     * @return
     */
    List<TvBase> getTvBasesByTime(final @Param("beginTime") String beginTime, final @Param("endTime") String endTime);
}
