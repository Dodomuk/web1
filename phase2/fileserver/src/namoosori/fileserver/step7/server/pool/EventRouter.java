/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.pool;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import namoosori.fileserver.step7.context.FileCommand;
import namoosori.fileserver.step7.server.handler.FileDeleteHandler;
import namoosori.fileserver.step7.server.handler.FileFindHandler;
import namoosori.fileserver.step7.server.handler.FileHandler;
import namoosori.fileserver.step7.server.handler.FileListHandler;
import namoosori.fileserver.step7.server.handler.FileStoreHandler;
import namoosori.fileserver.step7.server.react.EventInfo;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

public class EventRouter extends Thread implements MonitorTarget {
	//
	private static final int INTERNAL_QUEUE_CAPACITY = 50; 
	
	private SocketWorker socketWorker;
	private InternalQueue internalEventQueue; 
	
	private EventRouter(int sequence) {
		//
		super("event_router_" + sequence);
		this.internalEventQueue = InternalQueue.newInstance(INTERNAL_QUEUE_CAPACITY); 
	}
	
	public static EventRouter newInstance(int sequence) {
		// 
		return new EventRouter(sequence); 
	}
	
	@Override
	public int getQueueSize() {
		//
		return internalEventQueue.size(); 
	}
	
	public boolean hasRoom() {
		// 
		if (!internalEventQueue.isFull()) {
			return true; 
		}
		
		return false; 
	}
	
	public void addEvent(EventInfo eventInfo) {
		// 
		internalEventQueue.add(eventInfo);
	}
	
	public void run() {
		// 
		while(true) {
			//
			EventInfo eventInfo = null;
			try {
				eventInfo = internalEventQueue.take();
			} catch (InterruptedException e) {
				break; 
			} 

			if (eventInfo != null) {
				route(eventInfo); 
			} 
			
			if (isRouterInterrupted()) {
				break; 
			}
		}
		
		System.out.println(" ## " + getName() + " EventRouter is closed."); 
	}
 	
	private boolean isRouterInterrupted() {
		// 
		try {
			TimeUnit.MILLISECONDS.sleep(1);
			return false; 
		} catch (InterruptedException e) {
			return true; 
		}
	}
	
	public void route(EventInfo eventInfo) {
		// 
		this.socketWorker = new SocketWorker(eventInfo.getSocket()); 
		String json = socketWorker.readMessage(); 
		RequestMessage request = RequestMessage.fromJson(json); 

		String serviceName = request.getServiceName(); 
		FileCommand command = FileCommand.valueOf(serviceName);
		
		FileHandler fileHandler = null;
		
		switch(command) {
		case Store:		
			fileHandler = new FileStoreHandler();
			break;  
		case Delete:		
			fileHandler = new FileDeleteHandler();
			break;  
		case Find:		
			fileHandler = new FileFindHandler();
			break; 
		case ListFiles: 
			fileHandler = new FileListHandler();
			break; 
		}
		
		ResponseMessage response = fileHandler.handle(request);
		try {
			socketWorker.writeMessage(response.toJson());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		this.socketWorker.closeSocket(); 
	}
}