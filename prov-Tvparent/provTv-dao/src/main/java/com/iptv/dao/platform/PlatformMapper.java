package com.iptv.dao.platform;


import com.iptv.model.platform.entity.Platform;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author wangzhan  <wangzhan, w_z911@163.comm>
 * @version v1.0
 * @project provTv-dao
 * @Description
 * @encoding UTF-8
 * @date 2017/5/17
 * @time 下午7:43
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Repository
public interface PlatformMapper {

    //基础方法 begin
    int deleteByPrimaryKey(Integer id);

    int insert(Platform record);

    int insertSelective(Platform record);

    Platform selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Platform record);

    int updateByPrimaryKey(Platform record);
    //基础方法 end

    /**
     * 获取所有平台类型list
     * @return
     */
    List<Platform> getAllPlatform();

}
