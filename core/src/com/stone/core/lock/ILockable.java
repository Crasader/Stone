package com.stone.core.lock;

/**
 * ֧�������Ľӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface ILockable {
	/**
	 * �����Լ�;
	 * 
	 * @return
	 */
	public ILockable lock();

	/**
	 * ���i;
	 */
	public void unlock();

}
