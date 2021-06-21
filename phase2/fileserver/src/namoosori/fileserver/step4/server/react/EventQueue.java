package namoosori.fileserver.step4.server.react;

import java.util.concurrent.ArrayBlockingQueue;

public class EventQueue {
	//
	private static final int QUEUE_CAPACITY = 100; 
	
	private ArrayBlockingQueue<EventInfo> queue; 
	
	private EventQueue() {
		this.queue = new ArrayBlockingQueue<EventInfo>(QUEUE_CAPACITY); 
	}
	
	public static EventQueue newInstance() {
		return new EventQueue(); 
	}
	
	public void add(EventInfo message) {
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