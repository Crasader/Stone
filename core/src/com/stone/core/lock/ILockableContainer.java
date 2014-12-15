package com.stone.core.lock;

/**
 * ֧�������Ķ�������;
 * 
 * @author crazyjohn
 *
 * @param <Key>
 * @param <Value>
 */
public interface ILockableContainer<Key, Value extends ILockable> {

	public Value lock(Key key);

	public void unlock(Value value);

	public void put(Key key, Value value);

	public Value get(Key key);
}
