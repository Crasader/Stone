package com.stone.actor.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.stone.actor.IActor;
import com.stone.actor.IActorCall;
import com.stone.actor.IActorCallback;
import com.stone.actor.IActorFuture;
import com.stone.actor.IActorId;
import com.stone.actor.IActorSystem;

public abstract class BaseActor implements IActor {
	protected BlockingQueue<QueueCall> callQueue = new LinkedBlockingQueue<QueueCall>();
	protected BlockingQueue<IActorCallback> callbackQueue = new LinkedBlockingQueue<>();
	protected Map<IActorCall<?>, IActorCallback> registerCallbacks = new ConcurrentHashMap<IActorCall<?>, IActorCallback>();
	private volatile boolean stop = true;
	protected IActorSystem actorSystem;
	private Logger logger = LoggerFactory.getLogger(BaseActor.class);
	protected IActorId actorId;

	@Override
	public void start() {
		this.stop = false;
	}

	@Override
	public IActorId getActorId() {
		return actorId;
	}

	@Override
	public void stop() {
		this.stop = true;
	}

	@Override
	public <T>IActorFuture<T> put(IActorCall<T> call) {
		IActorFuture<T> future = new ActorFuture<T>();
		callQueue.add(new QueueCall(call, future));
		return future;
	}

	@Override
	public void put(IActorCallback callback) {
		callbackQueue.add(callback);
	}

	@Override
	public void put(IActorCall<?> call, IActorCallback callback, IActorId source) {
		callback.setTarget(source);
		registerCallbacks.put(call, callback);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void run() {
		while (!stop) {
			try {
				Iterator<QueueCall> iterator = this.callQueue.iterator();
				while (iterator.hasNext()) {
					QueueCall queueCall = iterator.next();
					IActorCall<?> call = queueCall.getCall();
					@SuppressWarnings("rawtypes")
					IActorFuture future = queueCall.getFuture();
					future.setResult(call.execute());
					if (registerCallbacks.get(call) != null) {
						actorSystem.dispatch(registerCallbacks.get(call).getTarget(), registerCallbacks.get(call));
					}
					iterator.remove();
				}
			} catch (Exception e) {
				logger.error("Execute call error", e);
			}
		}
	}

	class QueueCall {
		private IActorCall<?> call;
		private IActorFuture<?> future;

		public QueueCall(IActorCall<?> call, IActorFuture<?> future) {
			this.call = call;
			this.future = future;
		}

		public IActorCall<?> getCall() {
			return call;
		}

		public IActorFuture<?> getFuture() {
			return future;
		}
	}

}