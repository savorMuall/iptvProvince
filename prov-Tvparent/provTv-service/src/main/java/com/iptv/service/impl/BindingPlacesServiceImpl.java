package com.iptv.service.impl;

import com.iptv.dao.bindingPlaces.BindingPlacesMapper;
import com.iptv.model.bindingPlaces.entity.BindingPlaces;
import com.iptv.service.bindingPlaces.BindingPlacesService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class BindingPlacesServiceImpl implements BindingPlacesService {

    @Resource
    private BindingPlacesMapper bindingPlacesMapper;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return bindingPlacesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(BindingPlaces record) {
        return bindingPlacesMapper.insert(record);
    }

    @Override
    public int insertSelective(BindingPlaces record) {
        return bindingPlacesMapper.insertSelective(record);
    }

    @Override
    public BindingPlaces selectByPrimaryKey(Integer id) {
        return bindingPlacesMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(BindingPlaces record) {
        return bindingPlacesMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(BindingPlaces record) {
        return bindingPlacesMapper.updateByPrimaryKey(record);
    }

    /**
     * 查询省份下是否还有编码
     * @param provId
     * @return
     */
    @Override
    public BindingPlaces selectByProvIdTimedesc(long provId) {
        return bindingPlacesMapper.selectByProvIdTimedesc(provId);
    }
}
