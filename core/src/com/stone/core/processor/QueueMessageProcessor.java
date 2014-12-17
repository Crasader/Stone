package com.stone.core.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.stone.core.msg.IMessage;

/**
 * 队列消息处理器;
 * 
 * @author crazyjohn
 *
 */
public class QueueMessageProcessor implements IMessageProcessor, Runnable {
	/** 阻塞队列 */
	private BlockingQueue<IMessage> queue = new LinkedBlockingQueue<IMessage>();
	/** 执行器 */
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	/** 停止服务标记 */
	private volatile boolean stop = true;

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
			// TODO cancel policy
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (!stop) {
			try {
				IMessage msg = queue.take();
				process(msg);
			} catch (InterruptedException e) {
				// TODO cancel policy
				e.printStackTrace();
			}
		}
	}

	/**
	 * 处理消息入口;
	 * 
	 * @param msg
	 */
	private void process(IMessage msg) {
		// TODO Auto-generated method stub

	}

}
