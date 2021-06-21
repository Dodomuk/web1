/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.pool;

import java.util.concurrent.ArrayBlockingQueue;

import namoosori.fileserver.step7.server.react.EventInfo;

public class InternalQueue {
	//
	private int capacity; 
	private ArrayBlockingQueue<EventInfo> queue;
	
	private InternalQueue(int capacity) {
		//
		this.capacity = capacity; 
		this.queue = new ArrayBlockingQueue<EventInfo>(capacity); 
	}
	
	public static InternalQueue newInstance(int capacity) {
		return new InternalQueue(capacity); 
	}
	
	public boolean isFull() {
		// 
		if (queue.remainingCapacity() <= 0) {
			return true; 
		}
		
		return false; 
	}
	
	public boolean add(EventInfo message) {
		//
		if (queue.size() > capacity) {
			System.out.println("Queue size --> " + queue.size()); 
			return false; 
		} else {
			queue.add(message);
			return true; 
		}
	}
	
	public EventInfo take() throws InterruptedException {
		return queue.take(); 
	}
	
	public EventInfo poll() throws InterruptedException {
		return queue.poll(); 
	}
	
	public int size() {
		return queue.size();
	}
}