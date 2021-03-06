package com.stone.game.module;

import akka.actor.ActorRef;

import com.stone.core.msg.MessageParseException;
import com.stone.core.msg.server.AGForwardMessage;

/**
 * The game module;
 * 
 * @author crazyjohn
 *
 */
public interface IGameModule {
	/**
	 * Handle the system internal message;
	 * 
	 * @param msg
	 * @param playerActor
	 */
	public void onInternalMessage(Object msg, ActorRef playerActor);

	/**
	 * Handle the net(io) external message;
	 * 
	 * @param msg
	 * @param playerActor
	 * @param dbMaster
	 * @throws MessageParseException
	 */
	public void onExternalMessage(AGForwardMessage msg, ActorRef playerActor, ActorRef dbMaster) throws Exception;
}
