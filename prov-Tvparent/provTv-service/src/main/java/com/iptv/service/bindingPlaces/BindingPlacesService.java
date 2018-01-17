package com.iptv.service.bindingPlaces;

import com.iptv.model.bindingPlaces.entity.BindingPlaces;

public interface BindingPlacesService {

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
    BindingPlaces selectByProvIdTimedesc(final long provId);



}
