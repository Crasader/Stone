package com.stone.core.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.stone.core.concurrent.NamedThreadFactory;
import com.stone.core.log.ILogger;
import com.stone.core.log.SimpleLoggers;
import com.stone.core.msg.IMessage;
import com.stone.core.msg.IProtobufMessage;
import com.stone.core.msg.MessageParseException;

/**
 * 队列消息处理器;
 * 
 * @author crazyjohn
 *
 */
public class QueueMessageProcessor implements IMessageProcessor, Runnable {
	private ILogger logger = SimpleLoggers
			.getLogger(QueueMessageProcessor.class);
	/** 阻塞队列 */
	private BlockingQueue<IMessage> queue = new LinkedBlockingQueue<IMessage>();
	/** 执行器 */
	private ExecutorService executor;
	/** 停止服务标记 */
	private volatile boolean stop = true;

	public QueueMessageProcessor(String name) {
		executor = Executors.newSingleThreadExecutor(new NamedThreadFactory(
				name));
	}

	@Override
	public void start() {
		executor.execute(this);
		stop = false;
	}

	@Override
	public void stop() {
		stop = true;
		executor.shutdown();
	}

	@Override
	public void put(IMessage msg) {
		try {
			queue.put(msg);
		} catch (InterruptedException e) {
			logger.error("Interrupted when put msg to queue", e);
		}
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				IMessage msg = queue.take();
				process(msg);
			} catch (InterruptedException e) {
				logger.error("Interrupted when take msg from queue", e);
			} catch (MessageParseException e) {
				logger.error("Parse error when process msg", e);
			}
		}
	}

	/**
	 * 处理消息入口;
	 * 
	 * @param msg
	 * @throws MessageParseException
	 */
	private void process(IMessage msg) throws MessageParseException {
		if (msg instanceof IProtobufMessage) {
			IProtobufMessage protobufMessage = (IProtobufMessage) msg;
			protobufMessage.execute();
		}
	}

}
