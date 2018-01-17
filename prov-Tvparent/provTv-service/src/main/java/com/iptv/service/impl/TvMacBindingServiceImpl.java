package com.iptv.service.impl;

import com.iptv.dao.tvMacBinding.TvMacBindingMapper;
import com.iptv.model.tvMacBinding.entity.TvMacBinding;
import org.springframework.stereotype.Service;
import tvMacBinding.TvMacBindingService;

import javax.annotation.Resource;

@Service
public class TvMacBindingServiceImpl implements TvMacBindingService {

    @Resource
    private TvMacBindingMapper tvMacBindingMapper;
    @Override
    public int deleteByPrimaryKey(Integer id) {
        return tvMacBindingMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(TvMacBinding record) {
        return tvMacBindingMapper.insert(record);
    }

    @Override
    public int insertSelective(TvMacBinding record) {
        return tvMacBindingMapper.insertSelective(record);
    }

    @Override
    public TvMacBinding selectByPrimaryKey(Integer id) {
        return tvMacBindingMapper.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(TvMacBinding record) {
        return tvMacBindingMapper.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(TvMacBinding record) {
        return tvMacBindingMapper.updateByPrimaryKey(record);
    }

    /**
     * 根据mac  账号  省份id查询是否有绑定记录
     * @param mac
     * @param accountNumber
     * @return
     */
    @Override
    public TvMacBinding selectByMacIpProvId(String mac, String accountNumber, Integer provId,byte state) {
        return tvMacBindingMapper.selectByMacIpProvId(mac ,accountNumber,provId,state);
    }


    /**
     * 查询当前省份回滚的编号 最旧的一个
     * @param provId
     * @return
     */
    @Override
    public TvMacBinding selectByProvIdState(Integer provId) {
        return tvMacBindingMapper.selectByProvIdState(provId);
    }

    @Override
    public TvMacBinding selectByProvIdStateUnUsed(Integer provId) {
        return tvMacBindingMapper.selectByProvIdStateUnUsed(provId);
    }

    @Override
    public TvMacBinding selectOldByProvIdStateNumber(Integer provId, Integer batchNumber, Integer number) {
        return tvMacBindingMapper.selectOldByProvIdStateNumber(provId,batchNumber,number);
    }


}
