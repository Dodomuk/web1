package namoosori.fileserver.step6.server.react;

import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue {
	//
	private static final int QUEUE_CAPACITY = 500; 
	
	private ArrayBlockingQueue<EventInfo> queue; 
	
	private EventQueue(int capacity) {
		this.queue = new ArrayBlockingQueue<EventInfo>(capacity); 
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