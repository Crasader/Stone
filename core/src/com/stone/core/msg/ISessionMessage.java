package com.stone.core.msg;

/**
 * ���лỰ����Ϣ�ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface ISessionMessage extends IMessage {
	/**
	 * ��ȡ�ػ���Ϣ;
	 * 
	 * @return
	 */
	public ISession getSession();
}
