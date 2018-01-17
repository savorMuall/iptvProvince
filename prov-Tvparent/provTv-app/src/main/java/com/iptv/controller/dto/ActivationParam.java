package com.iptv.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ActivationParam {
	
	private String code;
	
	private String message;

}
