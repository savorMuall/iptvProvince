package com.iptv.dao.tvMsg;


import com.iptv.model.tvMsg.entity.TvMsg;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TvMsgMapper {
	
	/**
	 * 根据查询条件获取电视列表
	 * @param
	 * @return List<TvMsg>
	 */
	public List<TvMsg> findAll(TvMsg tvMsg);
	
	/**
	 * 根据指定查询条件获取指定电视
	 * @param
	 * @return TvMsg
	 */
	public TvMsg find(TvMsg tvMsg);
	
	
}
