package com.stone.game.handler;

import com.stone.core.annotation.Handler;
import com.stone.core.processor.MessageType;
import com.stone.game.msg.CGMessage;

/**
 * ��Ϣ�������ӿ�;
 * <p>
 * 1. ������Ϣ���߼�������ڣ�������߼�����ί�и�����ƶ���ҵ�������ȥ��;<br>
 * 2. ʵ�ֿ������ע��{@link Handler}
 * Ȼ������ioc����(����spring)�����Լ�����ȫ��ɨ��,������message��ӳ��,�����״���ֶ�ע��;
 * 
 * 
 * @author crazyjohn
 *
 */
public interface IMessageHandlerWithType {
	/**
	 * ��Ϣ����ӿ�;
	 * 
	 * @param msg
	 */
	public void execute(CGMessage msg);

	/**
	 * ��ȡ�������Ϣ����;
	 * 
	 * @return
	 */
	public MessageType getMessageType();
}
