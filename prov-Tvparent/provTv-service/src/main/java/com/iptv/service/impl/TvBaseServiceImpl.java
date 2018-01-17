package com.iptv.service.impl;

import com.iptv.dao.tv.TvBaseMapper;
import com.iptv.model.tv.helper.TvBase;
import com.iptv.service.tv.TvBaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视基本信息mapper
 * @encoding UTF-8
 * @date 2017/03/30
 * @time 13:56
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class TvBaseServiceImpl implements TvBaseService {
	
	@Autowired
	private TvBaseMapper tvBaseDao;

	public int deleteByPrimaryKey(int id) {
		
		return tvBaseDao.deleteByPrimaryKey(id);
	}

	public int insert(TvBase record) {
		
		return tvBaseDao.insert(record);
	}

	public int insertSelective(TvBase record) {
		
		return tvBaseDao.insertSelective(record);
	}

	public TvBase selectByPrimaryKey(int id) {
		 
		return tvBaseDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TvBase record) {
		 
		return tvBaseDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TvBase record) {
		
		return tvBaseDao.updateByPrimaryKey(record);
	}



	@Override
	public TvBase getTvBaseByMac(String mac) {
		return tvBaseDao.getTvBaseByMac(mac);
	}

	@Override
	public List<TvBase> getTvBasesByTime(String beginTime, String endTime) {
		return tvBaseDao.getTvBasesByTime(beginTime,endTime);
	}

	@Override
	public void insertList(List<TvBase> tvBases) {
		for(TvBase vo:tvBases){
			if(null!= this.getTvBaseByMac(vo.getMac())){
				tvBaseDao.updateByPrimaryKey(vo);
			}else{
				tvBaseDao.insert(vo);
			}
		}
	}
}
