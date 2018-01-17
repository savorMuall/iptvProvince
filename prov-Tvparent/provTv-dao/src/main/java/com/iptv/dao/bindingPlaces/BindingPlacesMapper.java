package com.iptv.dao.bindingPlaces;


import com.iptv.model.bindingPlaces.entity.BindingPlaces;
import com.iptv.model.tvMacBinding.entity.TvMacBinding;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface BindingPlacesMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(BindingPlaces record);

    int insertSelective(BindingPlaces record);

    BindingPlaces selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(BindingPlaces record);

    int updateByPrimaryKey(BindingPlaces record);

    /**
     * 查询省份下是否还有编码
     * @param provId
     * @return
     */
    BindingPlaces selectByProvIdTimedesc(final @Param("provId") long provId);



}