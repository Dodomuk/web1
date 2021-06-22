package namoosori.fileserver.step4.server.react;

import namoosori.fileserver.step4.context.FileCommand;
import namoosori.fileserver.step4.server.handler.*;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

import java.io.IOException;

public class EventRouter extends Thread {
	//
	private SocketWorker socketWorker; 
	private EventQueue eventQueue; 
	
	private EventRouter(int sequence, EventQueue eventQueue) {
		//
		super("event_router_" + sequence);
		this.eventQueue = eventQueue; 
	}
	
	public static EventRouter newInstance(int sequence, EventQueue eventQueue) {
		// 
		return new EventRouter(sequence, eventQueue); 
	}
	
	public void run() {
		// 
		while(true) {
			//
			EventInfo eventInfo = null;
			try {
				eventInfo = eventQueue.take();
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
		
		socketWorker.closeSocket(); 
	}
}