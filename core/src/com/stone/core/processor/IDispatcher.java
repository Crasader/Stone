package com.stone.core.processor;


/**
 * �ַ����ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IDispatcher extends IMessageProcessor {

	/**
	 * ��ȡ����������;
	 * 
	 * @return
	 */
	public int getProcessorCount();

	/**
	 * ���ݴ�����������ȡ������;
	 * 
	 * @param processorIndex
	 * @return
	 */
	public IMessageProcessor getProcessor(int processorIndex);
}
