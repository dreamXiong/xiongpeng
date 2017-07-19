package com.liguo.hgl.exceptions;

import com.liguo.hgl.common.MessageEnum;


public class TransactionException extends Exception {
	
	protected String messageCode = "";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public TransactionException(String message) {
		super(message);
	}
	
	public TransactionException(String messageCode, String message) {
		super(message);
		this.messageCode = messageCode;
	}
	
	public TransactionException(Throwable t) {
		super(t);
	} 
	
	public TransactionException(String messageCode, Throwable t) {
		super(t);
		this.messageCode = messageCode;
	}

    public TransactionException(MessageEnum message) {
        this(message.getMessageCode().toString(), message.getMessageValue());
    }
	
	public String getMessageCode() {
		return messageCode;
	}
}
