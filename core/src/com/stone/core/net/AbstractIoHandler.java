package com.stone.core.net;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import akka.actor.ActorRef;

import com.stone.core.msg.ISessionMessage;
import com.stone.core.session.ISession;

/**
 * Base io handler;
 * 
 * @author crazyjohn
 *
 * @param <S>
 */
public abstract class AbstractIoHandler<S extends ISession> extends IoHandlerAdapter {
	/** game master */
	protected ActorRef gameMaster;
	private static final String SESSION_INFO = "SESSION_INFO";
	protected Logger logger = LoggerFactory.getLogger(AbstractIoHandler.class);

	/**
	 * You should give the main master actor;
	 * 
	 * @param mainMasterActor
	 */
	public AbstractIoHandler(ActorRef mainMasterActor) {
		this.gameMaster = mainMasterActor;
	}

	@Override
	public void messageReceived(IoSession session, Object message) throws Exception {
		@SuppressWarnings("unchecked")
		S sessionInfo = (S) session.getAttribute(SESSION_INFO);
		if (sessionInfo == null) {
			// just close
			session.close(true);
		}
		if (message instanceof ISessionMessage) {
			@SuppressWarnings("unchecked")
			ISessionMessage<S> msg = (ISessionMessage<S>) message;
			msg.setSession(sessionInfo);
			gameMaster.tell(msg, ActorRef.noSender());
		}
	}

	@Override
	public void sessionClosed(IoSession session) throws Exception {
		@SuppressWarnings("unchecked")
		S sessionInfo = (S) session.getAttribute(SESSION_INFO);
		if (sessionInfo != null) {
			session.setAttribute(SESSION_INFO, null);
		}
		ISessionMessage<S> sessionCloseMessage = createSessionCloseMessage(sessionInfo);
		if (sessionCloseMessage == null) {
			return;
		}
		this.gameMaster.tell(sessionCloseMessage, ActorRef.noSender());
	}

	@Override
	public void sessionOpened(IoSession session) throws Exception {
		S sessionInfo = createSessionInfo(session);
		session.setAttributeIfAbsent(SESSION_INFO, sessionInfo);
		ISessionMessage<S> sessionOpenMessage = createSessionOpenMessage(sessionInfo);
		if (sessionOpenMessage == null) {
			return;
		}
		this.gameMaster.tell(sessionOpenMessage, ActorRef.noSender());
	}

	@Override
	public void exceptionCaught(IoSession session, Throwable cause) throws Exception {
		// close session
		session.close(true);
	}

	/**
	 * Create a session open message, when received mina session open event;
	 * 
	 * @param sessionInfo
	 * @return
	 */
	protected abstract ISessionMessage<S> createSessionOpenMessage(S sessionInfo);

	/**
	 * Create the session info;
	 * 
	 * @param session
	 * @return
	 */
	protected abstract S createSessionInfo(IoSession session);

	/**
	 * Create a session close message, when received mina session close event;
	 * 
	 * @param sessionInfo
	 * @return
	 */
	protected abstract ISessionMessage<S> createSessionCloseMessage(S sessionInfo);

}
