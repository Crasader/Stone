package com.stone.game;

import org.apache.mina.core.session.IoSession;

import akka.actor.ActorRef;

import com.stone.core.msg.ISessionMessage;
import com.stone.core.msg.ProtobufMessage;
import com.stone.core.net.AbstractIoHandler;
import com.stone.core.session.BaseActorSession;
import com.stone.game.server.msg.AgentServerForwardMessage;
import com.stone.game.server.msg.AgentSessionOpenMessage;
import com.stone.game.server.msg.AgentSesssionCloseMessage;

public class AgentIoHandler extends AbstractIoHandler<BaseActorSession> {

	public AgentIoHandler(ActorRef mainMasterActor) {
		super(mainMasterActor);
	}

	@Override
	protected ISessionMessage<BaseActorSession> createSessionOpenMessage(BaseActorSession sessionInfo) {
		return new AgentSessionOpenMessage(sessionInfo);
	}

	@Override
	protected Object doMessageWrapper(ISessionMessage<BaseActorSession> msg) {
		return new AgentServerForwardMessage(((ProtobufMessage) msg));
	}

	@Override
	protected BaseActorSession createSessionInfo(IoSession session) {
		return new BaseActorSession(session);
	}

	@Override
	protected ISessionMessage<BaseActorSession> createSessionCloseMessage(BaseActorSession sessionInfo) {
		return new AgentSesssionCloseMessage(sessionInfo);
	}

}
