package com.iptv.dao.tv;


import com.iptv.model.tv.helper.TvArea;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视对于区域mapper
 * @encoding UTF-8
 * @date 2017/03/30
 * @time 13:50
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Repository
public interface TvAreaMapper {
	
	int deleteByPrimaryKey(int id);

    int insert(TvArea record);

    int insertSelective(TvArea record);

    TvArea selectByPrimaryKey(int id);

    int updateByPrimaryKeySelective(TvArea record);

    int updateByPrimaryKey(TvArea record);
    
    /**
     * 根据电视mac地址和省份code获取当前电视是否属于这个省份
     * @param mac
     * @param provinceCode
     * @return Optional<TvArea>
     * @author w_z91 
     * @date 2017/03/30
     * @time 14:03
     */
    TvArea getTvAreaByMacAndProvinceCode(final @Param("mac") String mac, final @Param("provinceCode") int provinceCode);

    /**
     * 根据电视mac地址和省份code获取当前电视是否属于这个省份
     * @param mac
     * @param provinceCode
     * @return TvArea
     * @author w_z91
     * @date 2017/03/30
     * @time 14:03
     */
    TvArea getTvAreaByMacAndProvince(final @Param("mac") String mac, final @Param("provinceCode") int provinceCode);

}
