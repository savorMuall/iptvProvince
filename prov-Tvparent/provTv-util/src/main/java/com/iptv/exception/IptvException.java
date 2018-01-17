package com.iptv.exception;

/**
 * IPTV业务异常封装类
 * @author w_z91
 *
 */
public class IptvException extends RuntimeException{

	 public IptvException() { super();}
	 
	 public IptvException(String message) {  super(message); }
	 
	 public IptvException(String message, Throwable cause) { super(message, cause); }
	 
	 public IptvException(Throwable cause) {  super(cause); }
	 
	 protected IptvException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		 	super(message, cause, enableSuppression, writableStackTrace);
	 }
}
