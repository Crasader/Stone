package com.stone.gate.session;

import org.apache.mina.core.session.IoSession;

import com.stone.core.session.BaseSession;

public class GateInternalSession extends BaseSession {

	public GateInternalSession(IoSession session) {
		super(session);
	}

}
