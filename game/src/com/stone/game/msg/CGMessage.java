package com.stone.game.msg;

import com.stone.core.msg.ISessionMessage;
import com.stone.game.human.Human;

/**
 * �ͻ��˷�������������Ϣ�ӿ�;
 * 
 * @author crazyjohn
 *
 */
public interface CGMessage extends ISessionMessage {
	public Human getHuman();
}
