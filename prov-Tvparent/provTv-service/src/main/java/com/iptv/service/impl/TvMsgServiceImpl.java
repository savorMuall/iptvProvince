package com.iptv.service.impl;

import com.iptv.dao.tvMsg.TvMsgMapper;
import com.iptv.model.tvMsg.entity.TvMsg;
import com.iptv.service.tvMsg.TvMsgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TvMsgServiceImpl implements TvMsgService {
	
	@Autowired
	private TvMsgMapper tvMsgMapper;

	public List<TvMsg> findAll(TvMsg tvMsg) {

		return tvMsgMapper.findAll(tvMsg);
	}

	public TvMsg find(TvMsg tvMsg) {
	
		return tvMsgMapper.find(tvMsg);
	}

}
