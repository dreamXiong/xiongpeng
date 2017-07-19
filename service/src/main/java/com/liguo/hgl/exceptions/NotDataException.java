package com.liguo.hgl.exceptions;

import com.liguo.hgl.common.MessageEnum;

public class NotDataException extends WapException {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public NotDataException(String msg) {

        super(msg);
        // TODO Auto-generated constructor stub
    }

    public NotDataException(String errorMessage, String msg) {

        super(errorMessage, msg);
    }

    public NotDataException(String errorMessage, String msg, Throwable t) {

        super(errorMessage, msg, t);
    }

    public NotDataException(MessageEnum messageEnum) {

        super(messageEnum);
    }
}
