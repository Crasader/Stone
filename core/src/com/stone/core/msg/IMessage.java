package com.stone.core.msg;

import com.stone.core.processor.MessageType;

/**
 * ��Ϣ�ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IMessage {
	/**
	 * ��ȡ��Ϣ����;
	 * 
	 * @return
	 */
	public MessageType getMessageType();

	/**
	 * ��ȡ��Ϣ����;
	 * 
	 * @return
	 */
	public String getShortName();

	/**
	 * д�Լ�����;
	 * <p>
	 * ���л�����;
	 */
	public void write();

	/**
	 * �����ж����Լ�;
	 * <p>
	 * �����л�;
	 */
	public void read();
}
