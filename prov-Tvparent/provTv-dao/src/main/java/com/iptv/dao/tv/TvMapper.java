package com.iptv.dao.tv;


import com.iptv.model.tv.entity.Tv;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface TvMapper {
	
	/*
	 * 根据查询条件查找，可查找指定，也可全表都查出来
	 */
	public List<Tv> findAll(Tv tv);
	
    /*
     * 分页查询所有的电视对象
     * @return List<Tv>
     * date:2017/01/20
     */
    public List<Tv> selectTvAllFy(Map<String, Object> map);
	
	/*
	 * 插入电视信息
	 */
	int insert(Tv tv);
	
	
	int update(Tv tv);
	
}
