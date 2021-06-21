/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.react;

import java.util.concurrent.LinkedBlockingQueue;

public class EventQueue {
	//
	private static final int QUEUE_CAPACITY = 500; 
	
	private LinkedBlockingQueue<EventInfo> queue; 			// alternative ArrayBlockingQueue
	
	private EventQueue(int capacity) {
		this.queue = new LinkedBlockingQueue<EventInfo>(capacity); 
	}
	
	public static EventQueue newInstance(int capacity) {
		return new EventQueue(capacity); 
	}
	
	public static EventQueue newInstance() {
		return new EventQueue(QUEUE_CAPACITY); 
	}

	public boolean isFull() {
		// 
		if (queue.remainingCapacity() <= 0) {
			return true; 
		}
		
		return false; 
	}
	
	public void add(EventInfo message) {
		//
		if (queue.size() > 100) {
			System.out.println("Queue size --> " + queue.size()); 
		}
		
		queue.add(message); 
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