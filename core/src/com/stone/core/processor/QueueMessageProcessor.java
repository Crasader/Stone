package com.stone.core.processor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.stone.core.msg.IMessage;

/**
 * ������Ϣ������;
 * 
 * @author crazyjohn
 *
 */
public class QueueMessageProcessor implements IMessageProcessor, Runnable {
	/** ��Ϣ���� */
	private BlockingQueue<IMessage> queue = new LinkedBlockingQueue<IMessage>();
	/** ִ���߳� */
	private ExecutorService executor = Executors.newSingleThreadExecutor();
	/** ֹͣ���λ */
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
	 * ��Ϣ����ӿ�;
	 * 
	 * @param msg
	 */
	private void process(IMessage msg) {
		// TODO Auto-generated method stub

	}

}
