/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.main;

import namoosori.fileserver.step7.server.pool.HandlerPool;
import namoosori.fileserver.step7.server.react.EventQueue;
import namoosori.fileserver.step7.server.react.FileServerReactor;

public class FileServer {
	//
	
	public static void main(String[] args) {
		// 
		startServer(); 
	}
	
	private static void startServer() {
		// 
		EventQueue sharedEventQueue = EventQueue.newInstance();
		
		HandlerPool handlerPool = HandlerPool.newInstance(sharedEventQueue); 
		FileServerReactor reactor = FileServerReactor.newInstance(sharedEventQueue);
		
		reactor.start(); 
		System.out.println("FileServer is started..."); 

		handlerPool.start();
		handlerPool.getPoolMonitor().start(); 
	}
}