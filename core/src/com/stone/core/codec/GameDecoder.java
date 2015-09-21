package com.stone.core.codec;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.core.msg.IMessage;

/**
 * The game decoder;
 * 
 * @author crazyjohn
 *
 */
public class GameDecoder implements ProtocolDecoder {
	private Logger logger = LoggerFactory.getLogger(this.getClass());
	private IoBuffer readBuffer = IoBuffer.allocate(IMessage.DECODE_MESSAGE_LENGTH).setAutoExpand(true).setAutoShrink(true);
	private IMessageFactory messageFactory;

	public GameDecoder(IMessageFactory messageFactory) {
		this.messageFactory = messageFactory;
	}

	@Override
	public synchronized void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out) throws Exception {
		// decode
		readBuffer.put(in);
		readBuffer.flip();
		while (true) {
			// 是否足够消息头的长度?
			if (readBuffer.remaining() < IMessage.HEADER_SIZE) {
				break;
			}
			// 读出消息包的长度
			short messageLength = readBuffer.getShort(0);
			short messageType = readBuffer.getShort(2);
			logger.info(String.format("===============================Pos: %d, type: %d, length: %d", this.readBuffer.position(), messageType,
					messageLength));
			if (readBuffer.remaining() < messageLength) {
				break;
			}
			// 读出消息包
			byte[] datas = new byte[messageLength];
			// readBuffer.flip();
			readBuffer.get(datas);
			IMessage aMessage = messageFactory.createMessage(messageType);
			if (aMessage != null) {
				IoBuffer aMessageBuffer = IoBuffer.wrap(datas).setAutoExpand(true);
				aMessage.setBuffer(aMessageBuffer);
				// read
				aMessage.read();
				// write to out stream
				out.write(aMessage);
			}
		}
		// compact
		if (readBuffer.hasRemaining()) {
			readBuffer.compact();
		} else {
			readBuffer.clear();
		}
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out) throws Exception {
		// do nothing

	}

	@Override
	public void dispose(IoSession session) throws Exception {
		this.readBuffer.free();
	}

}
