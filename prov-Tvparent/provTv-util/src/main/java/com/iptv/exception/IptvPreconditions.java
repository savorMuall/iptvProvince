package com.iptv.exception;

public class IptvPreconditions {
	
	public static void assertTrue(boolean expression) {
        if(!expression) {
            throw new IptvException();
        }
    }

    public static void assertTrue(boolean expression, String errorMessage) {
        if(!expression) {
            throw new IptvException(errorMessage);
        }
    }

    public static void assertTrue(boolean expression, int code, String errorMessage) {
        if(!expression) {
            throw new IptvCodeException(code, errorMessage);
        }
    }

  

    public static <T> T checkNotNull(T reference) {
        if(reference == null) {
            throw new IptvNullException();
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, String errorMessage) {
        if(reference == null) {
            throw new IptvNullException(errorMessage);
        } else {
            return reference;
        }
    }

    public static <T> T checkNotNull(T reference, int code, String errorMessage) {
        if(reference == null) {
            throw new IptvCodeException(code, errorMessage);
        } else {
            return reference;
        }
    }
    private IptvPreconditions() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }

}
