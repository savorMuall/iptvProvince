package com.iptv.exception;

/**
 * iptv空异常封装类
 * @author w_z91
 *
 */
public class IptvNullException extends IptvException {
	
	public IptvNullException() {
    }

    public IptvNullException(final String message) {
        super(message);
    }

    public IptvNullException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public IptvNullException(final Throwable cause) {
        super(cause);
    }

    public IptvNullException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
