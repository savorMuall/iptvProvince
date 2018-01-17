package com.iptv.util;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class GlobalShareMemory {

	private static ConcurrentHashMap<Integer, Object> map = new ConcurrentHashMap<Integer, Object>();
	
	private static GlobalShareMemory shareMem;
	
	private GlobalShareMemory(){}
	
	public static synchronized GlobalShareMemory getInstance(){
		if (shareMem != null){
			return shareMem;
		}
		return new GlobalShareMemory();
	}
	
	/**
	 * 清除内存缓存数据
	 */
	public static void clear() {
		map.clear();
	}
	
	/**
	 * 判断该用户是否有操作权限
	 * @param userId
	 * @param uri
	 * @return
	 */
	public static boolean contains(int userId, String uri) {
		if (map.containsKey(userId)) {
			Set<String> set = (Set<String>) map.get(userId);
			if (!set.contains(uri)) {
				if (map.containsKey(-2)){
					if (!((Set<String>)map.get(-2)).contains(uri)) {
						return true;
					}
				}
			} else {
				return true;
			}
		}
		return false;
	}
	
	public static boolean containsKey(int key) {
		return map.containsKey(key);
	}
	
	public static void add(Integer userId, String uri) {
		if (map.containsKey(userId)) {
			((Set<String>) map.get(userId)).add(uri);
		} else {
			Set<String> set = new HashSet<String>();
			set.add(uri);
			
			map.put(userId, set);
		}
	}
	
	public static void put(int key, Object value) {
		if (map.containsKey(key)) {
			return;
		} else {
			map.put(key, value);
		}
	}
	
	public static Long getValue(int key) {
		return (Long) map.get(key);
	}
	
	public static boolean isNotEmpty () {
		return (map != null && !map.isEmpty());
	}
}
