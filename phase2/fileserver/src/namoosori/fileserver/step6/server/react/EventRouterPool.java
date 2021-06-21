package namoosori.fileserver.step6.server.react;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class EventRouterPool extends Thread {
	//
	private static final int ROUTER_COUNTER = 10;

	private int currentIndex;
	private EventQueue sharedEventQueue;
	private List<EventRouter> eventRouters;

	private EventRouterPool(EventQueue eventQueue) {
		//
		this.currentIndex = 0;
		this.sharedEventQueue = eventQueue;
		this.eventRouters = new ArrayList<>();
		this.initEventRoutors();
	}

	public static EventRouterPool newInstance(EventQueue sharedQueue) {
		//
		return new EventRouterPool(sharedQueue);
	}

	private void initEventRoutors() {
		//
		for (int i = 0; i < ROUTER_COUNTER; i++) {
			EventRouter router = EventRouter.newInstance(i);
			router.start();
			eventRouters.add(router);
		}
	}

	private EventRouter nextRouter() {
		//
		EventRouter nextRouter = eventRouters.get(currentIndex);
		currentIndex++;
		if (currentIndex == ROUTER_COUNTER) {
			currentIndex = 0;
		}

		return nextRouter;
	}

	public void run() {
		//
		while (true) {
			//
			try {
				EventInfo eventInfo = sharedEventQueue.take();
				while (true) {
					EventRouter nextRouter = nextRouter();

					if (nextRouter.hasRoom()) {
						nextRouter.addEvent(eventInfo);
						break; 
					} else {
						System.out.println(nextRouter.getName() + " is passed...");
						if(!sleepWellInMillis(5)) {
							break; 
						}
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	private boolean sleepWellInMillis(long millis) {
		// 
		try {
			TimeUnit.MILLISECONDS.sleep(millis);
			return true; 
		} catch (InterruptedException e) {
			e.printStackTrace();
			return false; 
		} 
	}
}