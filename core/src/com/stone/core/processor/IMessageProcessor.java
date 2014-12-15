package com.stone.core.processor;

import com.stone.core.msg.IMessage;

/**
 * ��Ϣ�������ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IMessageProcessor {
	/**
	 * ����������;
	 */
	public void start();

	/**
	 * �رմ�����;
	 */
	public void stop();

	/**
	 * Ͷ����Ϣ;
	 * 
	 * @param msg
	 */
	public void put(IMessage msg);
}
