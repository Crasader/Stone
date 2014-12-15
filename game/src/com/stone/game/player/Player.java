package com.stone.game.player;

import com.stone.core.msg.ISession;
import com.stone.core.state.IState;
import com.stone.core.state.IStateManager;
import com.stone.game.human.Human;

/**
 * ���ҵ�����;
 * 
 * @author crazyjohn
 *
 */
public class Player implements IStateManager {
	/** ��ǰ��ɫ */
	private Human human;
	/** �ػ� */
	private ISession session;
	/** ��ǰ״̬ */
	private IState currentState;

	public Human getHuman() {
		return human;
	}

	public void setHuman(Human human) {
		this.human = human;
	}

	public ISession getSession() {
		return session;
	}

	public void setSession(ISession session) {
		this.session = session;
	}

	@Override
	public IState getCurrentState() {
		return currentState;
	}

	@Override
	public IState setCurrentState(IState state) {
		currentState = state;
		return currentState;
	}

	@Override
	public boolean canTransferStateTo(IState state) {
		return currentState.canTransferTo(state);
	}

	@Override
	public boolean transferStateTo(IState state) {
		if (!canTransferStateTo(state)) {
			return false;
		}
		setCurrentState(state);
		return true;
	}
}
