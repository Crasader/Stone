package com.stone.game.player.state;

import com.stone.core.processor.MessageType;
import com.stone.core.state.IState;

/**
 * ���״̬;
 * 
 * @author crazyjohn
 *
 */
public enum PlayerState implements IState {
	NONE() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** �������� */
	CONNECTED() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** �Ѿ���֤ */
	AUTHORIZED() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** ������Ϸ�� */
	ENTERING() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** ������Ϸ�� */
	GAMEING() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	/** ս���� */
	BATTLING() {
		@Override
		public boolean canProcessMessage(MessageType type) {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public boolean canTransferTo(IState state) {
			// TODO Auto-generated method stub
			return false;
		}
	},
	;

}
