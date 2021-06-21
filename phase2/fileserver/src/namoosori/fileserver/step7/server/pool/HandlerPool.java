/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.pool;

import java.util.ArrayList;
import java.util.List;

import namoosori.fileserver.step7.server.react.EventInfo;
import namoosori.fileserver.step7.server.react.EventQueue;

public class HandlerPool extends Thread {
	//
	private static final int INITIAL_ROUTER_COUNT = 15;
	private static final int MAX_ROUTER_COUNT = 30;
	private static final int MIN_ROUTER_COUNT = 2;
	
	private int totalAverageQueueSize; 
	private int currentIndex; 
	private int currentRouterCount; 
	private EventQueue sharedEventQueue;
	private List<EventRouter> eventRouters; 
	private PoolMonitor poolMonitor; 
	
	private HandlerPool(EventQueue eventQueue) {
		//
		this.currentIndex = 0; 
		this.currentRouterCount = INITIAL_ROUTER_COUNT; 
		this.sharedEventQueue = eventQueue; 
		this.eventRouters = new ArrayList<>(); 
		this.initEventRoutors();
		this.poolMonitor = PoolMonitor.newInstance(this); 
	}
	
	public static HandlerPool newInstance(EventQueue sharedQueue) {
		// 
		return new HandlerPool(sharedQueue); 
	}
	
	public void reportMonitoringResult(int totalAverageQueueSize) {
		// 
		this.totalAverageQueueSize = totalAverageQueueSize; 
		System.out.println(" >> Report is comming from the monitor --> " + totalAverageQueueSize);
		adjustEventRouter(); 
	}
	
	public PoolMonitor getPoolMonitor() {
		// 
		return poolMonitor; 
	}
	
	public List<MonitorTarget> getMonitorTargets() {
		// 
		List<MonitorTarget> targets = new ArrayList<MonitorTarget>(); 
		for(EventRouter router : eventRouters) {
			targets.add(router); 
		}
		
		return targets; 
	}
	
	public void run() {
		// 
		while(true) {
			// 
			takeQueueItem(); 
		}
	}
	
	private void adjustEventRouter() {
		// 
		if (this.totalAverageQueueSize <= 2) {
			decreaseHandler(); 
		} else if (this.totalAverageQueueSize >= 20) {
			increaseHandler(); 
		}
	}

	private void decreaseHandler() {
		// 
		if (currentRouterCount <= MIN_ROUTER_COUNT) {
			System.out.println(" ## Can't decrease any more --> " + currentRouterCount);
			return; 
		}

		int lastIndex = currentRouterCount - 1; 
		EventRouter lastRouter = eventRouters.get(lastIndex); 
		eventRouters.remove(lastIndex); 
		lastRouter.interrupt(); 
		
		this.currentRouterCount--; 
	}
	
	private void increaseHandler() {
		// 
		if (currentRouterCount >= MAX_ROUTER_COUNT) {
			System.out.println(" ## Can't increase any more --> " + currentRouterCount);
			return; 
		}
		
		EventRouter router =  EventRouter.newInstance(currentRouterCount);
		router.start(); 
		eventRouters.add(router); 
		this.currentRouterCount++; 

		System.out.println(" ## A event router is added --> " + currentRouterCount);
	}
	
	private void takeQueueItem() {
		// 
		try {
			EventInfo eventInfo = sharedEventQueue.take();
			EventRouter nextRouter = nextRouter(); 
			if(nextRouter.hasRoom()) {
				nextRouter().addEvent(eventInfo);
			} else {
				System.out.println(nextRouter.getName() + " is passed..."); 
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} 
	}
	
	private void initEventRoutors() {
		// 
		for(int i=0; i<INITIAL_ROUTER_COUNT; i++) {
			EventRouter router =  EventRouter.newInstance(i);
			router.start(); 
			eventRouters.add(router); 
		}
	}
	
	private EventRouter nextRouter() {
		// 
		EventRouter nextRouter = eventRouters.get(currentIndex); 
		currentIndex++; 
		if (currentIndex == this.currentRouterCount) {
			currentIndex = 0; 
		}
		
		return nextRouter; 
	}
}