package namoosori.fileserver.step6.server.react;

import java.io.IOException;

import namoosori.fileserver.step6.context.FileCommand;
import namoosori.fileserver.step6.server.handler.FileDeleteHandler;
import namoosori.fileserver.step6.server.handler.FileFindHandler;
import namoosori.fileserver.step6.server.handler.FileHandler;
import namoosori.fileserver.step6.server.handler.FileListHandler;
import namoosori.fileserver.step6.server.handler.FileStoreHandler;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

public class EventRouter extends Thread {
	//
	private static final int INTERNAL_QUEUE_CAPACITY = 50; 
	
	private SocketWorker socketWorker; 
	private EventQueue internalEventQueue; 
	
	private EventRouter(int sequence) {
		//
		super("event_router_" + sequence);
		this.internalEventQueue = EventQueue.newInstance(INTERNAL_QUEUE_CAPACITY); 
	}
	
	public static EventRouter newInstance(int sequence) {
		// 
		return new EventRouter(sequence); 
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
				e.printStackTrace();
			} 

			if (eventInfo != null) {
				route(eventInfo); 
			} 
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