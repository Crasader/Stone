package com.stone.core.msg;

import com.stone.core.entity.IEntity;

/**
 * ���ݿ������Ϣ�ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IDBMessage extends IMessage {
	public Class<? extends IEntity<?>> getEntityClass();
}
