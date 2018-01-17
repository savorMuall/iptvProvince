package com.iptv.model.category.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Category implements Serializable{

	private static final long serialVersionUID = -5517277526216139899L;

	private int id;
	
	private String name;

}
