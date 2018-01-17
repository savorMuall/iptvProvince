package com.iptv.service.impl;


import com.iptv.dao.tv.TvAreaMapper;
import com.iptv.model.tv.helper.TvArea;
import com.iptv.service.tv.TvAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 王占  <w_z911@163.com>
 * @version v1.0
 * @project 
 * @Description 电视对于区域serviceImpl
 * @encoding UTF-8
 * @date 2017/03/30
 * @time 13:54
 * @修改记录 <pre>
 * 版本       修改人         修改时间         修改内容描述
 * --------------------------------------------------
 * <p>
 * --------------------------------------------------
 * </pre>
 */
@Service
public class TvAreaServiceImpl implements TvAreaService {
	
	@Autowired
	private TvAreaMapper  tvAreaDao;

	public int deleteByPrimaryKey(int id) {
		return tvAreaDao.deleteByPrimaryKey(id);
	}

	public int insert(TvArea record) {
		return tvAreaDao.insert(record);
	}

	public int insertSelective(TvArea record) {
		return tvAreaDao.insertSelective(record);
	}

	public TvArea selectByPrimaryKey(int id) {
		return tvAreaDao.selectByPrimaryKey(id);
	}

	public int updateByPrimaryKeySelective(TvArea record) {
		return tvAreaDao.updateByPrimaryKeySelective(record);
	}

	public int updateByPrimaryKey(TvArea record) {
		return tvAreaDao.updateByPrimaryKey(record);
	}

	@Override
	public TvArea getTvAreaByMacAndProvince(String mac, int provinceCode) {
		return tvAreaDao.getTvAreaByMacAndProvince(mac, provinceCode);
	}


}
