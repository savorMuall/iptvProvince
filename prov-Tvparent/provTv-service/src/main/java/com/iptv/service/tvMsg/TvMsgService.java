package com.iptv.service.tvMsg;


import com.iptv.model.tvMsg.entity.TvMsg;

import java.util.List;

public interface TvMsgService {
	
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
