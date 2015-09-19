package com.stone.game.network;

import com.stone.core.codec.IMessageFactory;
import com.stone.core.msg.IMessage;
import com.stone.core.msg.server.AGForwardMessage;
import com.stone.game.server.msg.AGPlayerLogoutMessage;
import com.stone.proto.MessageTypes.MessageType;

public class GameMessageFactory implements IMessageFactory {

	@Override
	public IMessage createMessage(int type) {
		switch (type) {
		case MessageType.AG_PLAYER_LOGOUT_VALUE:
			return new AGPlayerLogoutMessage(type);
		default:
			return new AGForwardMessage(type);
		}
	}

}
