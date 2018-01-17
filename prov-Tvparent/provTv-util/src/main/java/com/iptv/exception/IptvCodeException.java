package com.iptv.exception;

import lombok.Getter;

/**
 * iptv 代码类异常封装
 * @author w_z91
 *
 */
public class IptvCodeException extends IptvException{
	
	@Getter
    private int code;

    public IptvCodeException(final int code) {
        super();
        this.code = code;
    }

    public IptvCodeException(final int code, final String message) {
        super(message);
        this.code = code;
    }

    public IptvCodeException(final int code, final String message, final Throwable cause) {
        super(message, cause);
        this.code = code;
    }

    public IptvCodeException(final int code, final Throwable cause) {
        super(cause);
        this.code = code;
    }

    public String format() {
        return "code:" + this.code + ",msg:" + getMessage();
    }
}
