package com.stone.core.processor;

/**
 * �ɷַ�����Ľӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IDispatchable {
	/**
	 * ��ȡ������;
	 * 
	 * @param myDispatcher
	 * @return
	 */
	public IMessageProcessor getProcessor(IDispatcher myDispatcher);
}
