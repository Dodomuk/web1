package namoosori.fileserver.step5.server.main;

import namoosori.fileserver.step5.server.react.EventQueue;
import namoosori.fileserver.step5.server.react.EventRouter;
import namoosori.fileserver.step5.server.react.FileServerReactor;

public class FileServer {
	//
	private static final int ROUTER_COUNTER = 10; 
	
	public static void main(String[] args) {
		// 
		startServer(); 
	}
	
	private static void startServer() {
		// 
		EventQueue eventQueue = EventQueue.newInstance(); 
		
		for(int i=0; i<ROUTER_COUNTER; i++) {
			EventRouter router =  EventRouter.newInstance(i, eventQueue);
			router.start(); 
		}
		
		FileServerReactor reactor = new FileServerReactor(eventQueue); 
		reactor.start(); 
		
		System.out.println("FileServer is started..."); 
	}
}