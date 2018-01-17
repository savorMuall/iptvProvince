package com.iptv.model.tv.helper;

import com.iptv.model.base.entity.BaseEntity;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class SearchTv extends BaseEntity implements Serializable {

	private static final long serialVersionUID = -6168825403274529380L;

	// 地市id
	private String areaId;

	// 平台（烽火、中兴、华为）
	private String platform;

	// 激活状态（1->激活、2->未激活、3->冻结）
	private byte state;

	// 查询开始时间
	private Date beginTime;

	// 查询结束时间
	private Date endTime;

}