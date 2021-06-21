package namoosori.fileserver.step6.server.main;

import namoosori.fileserver.step6.server.react.EventQueue;
import namoosori.fileserver.step6.server.react.EventRouterPool;
import namoosori.fileserver.step6.server.react.FileServerReactor;

public class FileServer {
	//
	
	public static void main(String[] args) {
		// 
		startServer(); 
	}
	
	private static void startServer() {
		// 
		EventQueue sharedEventQueue = EventQueue.newInstance();
		
		EventRouterPool.newInstance(sharedEventQueue).start(); 
		FileServerReactor.newInstance(sharedEventQueue).start();
		
		System.out.println("FileServer is started..."); 
	}
}