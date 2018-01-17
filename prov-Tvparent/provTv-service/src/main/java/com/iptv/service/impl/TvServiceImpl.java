package com.iptv.service.impl;

import com.iptv.dao.tv.TvMapper;
import com.iptv.model.tv.entity.Tv;
import com.iptv.service.tv.TvService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TvServiceImpl implements TvService{

	@Autowired
	private TvMapper tvDao;
	public List<Tv> findAll(Tv tv) {
		
		return tvDao.findAll(tv);
	}

	public int insert(Tv tv) {
		
		return tvDao.insert(tv);
	}

	public List<Tv> selectTvAllFy(Map<String, Object> map) {
		
		return tvDao.selectTvAllFy(map);
	}

	public int insertList(List<Tv> tv) {
		
		for(Tv tvEn:tv){
			tvDao.insert(tvEn);
		}
		return 0;
	}

	public int update(Tv tv) {
		
		return tvDao.update(tv);
		
	}
	
	

}
