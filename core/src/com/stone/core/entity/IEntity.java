package com.stone.core.entity;

import java.io.Serializable;

/**
 * ʵ��ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface IEntity<ID extends Serializable> {
	public ID getId();
}
