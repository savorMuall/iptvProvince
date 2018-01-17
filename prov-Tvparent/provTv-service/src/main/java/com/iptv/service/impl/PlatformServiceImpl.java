package com.iptv.service.impl;

import com.iptv.model.platform.entity.Platform;
import com.iptv.service.platform.PlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author wangzhan  <wangzhan, w_z911@163.comm>
 * @version v1.0
 * @project provTv-service
 * @Description
 * @encoding UTF-8
 * @date 2017/5/17
 * @time 下午7:37
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class PlatformServiceImpl implements PlatformService {

    @Autowired
    private PlatformService platformService;

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return platformService.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Platform record) {
        return platformService.insert(record);
    }

    @Override
    public int insertSelective(Platform record) {
        return platformService.insertSelective(record);
    }

    @Override
    public Platform selectByPrimaryKey(Integer id) {
        return platformService.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Platform record) {
        return platformService.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Platform record) {
        return platformService.updateByPrimaryKey(record);
    }

    /**
     * 获取所有平台类型list
     * @return
     */
    @Override
    public List<Platform> getAllPlatform() {
        return platformService.getAllPlatform();
    }
}
