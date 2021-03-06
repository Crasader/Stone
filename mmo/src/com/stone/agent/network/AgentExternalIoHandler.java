package com.stone.agent.network;

import org.apache.mina.core.session.IoSession;

import akka.actor.ActorRef;

import com.stone.agent.msg.external.ClientSessionCloseMessage;
import com.stone.agent.msg.external.ClientSessionOpenMessage;
import com.stone.core.msg.ISessionMessage;
import com.stone.core.net.AbstractIoHandler;
import com.stone.core.session.BaseActorSession;

/**
 * The gate client io handler;
 * 
 * @author crazyjohn
 *
 */
public class AgentExternalIoHandler extends AbstractIoHandler<BaseActorSession> {

	public AgentExternalIoHandler(ActorRef mainMasterActor) {
		super(mainMasterActor);
	}

	@Override
	protected ISessionMessage<BaseActorSession> createSessionOpenMessage(BaseActorSession sessionInfo) {
		return new ClientSessionOpenMessage(sessionInfo);
	}

	@Override
	protected BaseActorSession createSessionInfo(IoSession session) {
		BaseActorSession sessionInfo = new BaseActorSession(session);
		return sessionInfo;
	}

	@Override
	protected ISessionMessage<BaseActorSession> createSessionCloseMessage(BaseActorSession sessionInfo) {
		return new ClientSessionCloseMessage(sessionInfo);
	}

}
