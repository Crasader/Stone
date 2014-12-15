package com.stone.core.state;

import com.stone.core.processor.MessageType;

/**
 * ״̬�ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IState {
	/**
	 * �Ƿ���Դ���ָ����Ϣ����;
	 * 
	 * @param type
	 * @return
	 */
	public boolean canProcessMessage(MessageType type);

	/**
	 * �Ƿ����ת����ָ����״̬;
	 * 
	 * @param state
	 * @return
	 */
	public boolean canTransferTo(IState state);
}
