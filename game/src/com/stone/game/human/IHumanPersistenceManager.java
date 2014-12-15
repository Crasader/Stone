package com.stone.game.human;

import com.stone.db.entity.HumanEntity;

/**
 * ֧�ֳ־û����������ҵ��������ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IHumanPersistenceManager {
	/**
	 * ���ؽӿ�;
	 * 
	 * @param entity
	 */
	public void onLoad(HumanEntity entity);

	/**
	 * �־û��ӿ�;
	 * 
	 * @param entity
	 */
	public void onPersistence(HumanEntity entity);

	public Human getOwner();
}
