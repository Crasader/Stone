package com.stone.core.state;

/**
 * ״̬�������ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IStateManager {
	/**
	 * ��ȡ��ǰ״̬;
	 * 
	 * @return
	 */
	public IState getCurrentState();

	/**
	 * ���õ�ǰ״̬;
	 * 
	 * @param state
	 * @return
	 */
	public IState setCurrentState(IState state);

	/**
	 * �Ƿ����ת����ָ��״̬;
	 * 
	 * @param state
	 * @return
	 */
	public boolean canTransferStateTo(IState state);

	/**
	 * ת����ָ��״̬;
	 * 
	 * @param state
	 * @return
	 */
	public boolean transferStateTo(IState state);

}
