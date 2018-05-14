package com.vsc.modules.entity;

import org.springframework.transaction.interceptor.TransactionAspectSupport;

/**
 * 消息异常类
 * @author XiangXiaoLin
 *
 */
public class MessageException extends Exception {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6159035942562166900L;
	private String message;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public MessageException(){
		
	}
	public MessageException(String message){
		//手动回滚事物
        TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		this.message=message;
	}
}
