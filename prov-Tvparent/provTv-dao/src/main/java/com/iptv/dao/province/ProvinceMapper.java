package com.iptv.dao.province;


import com.iptv.model.province.entity.Province;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvinceMapper {

	int deleteByPrimaryKey(Integer id);

	int insert(Province record);

	int insertSelective(Province record);

	Province selectByPrimaryKey(Integer id);

	int updateByPrimaryKeySelective(Province record);

	int updateByPrimaryKey(Province record);

	
	//获取所有省份地市
	public List<Province> findAll(Province p);

	/**
	 * 根据省份id获取省份信息
	 * @param provId
	 * @return
	 */
	Province selectByProvId(final @Param("provId") int provId);
}
