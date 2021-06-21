package namoosori.fileserver.step3.server.react;

import java.io.IOException;
import java.net.Socket;

import namoosori.fileserver.step3.context.FileCommand;
import namoosori.fileserver.step3.server.handler.FileDeleteHandler;
import namoosori.fileserver.step3.server.handler.FileFindHandler;
import namoosori.fileserver.step3.server.handler.FileHandler;
import namoosori.fileserver.step3.server.handler.FileListHandler;
import namoosori.fileserver.step3.server.handler.FileStoreHandler;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;
import namoosori.fileserver.util.SocketWorker;

public class EventRouter implements Runnable {
	//
	private SocketWorker socketWorker; 
	
	private EventRouter(int sequence, Socket socket) {
		//
		// super("event_router_" + sequence); 
		this.socketWorker = new SocketWorker(socket); 
	}
	
	public static EventRouter newInstance(int sequence, Socket socket) {
		// 
		return new EventRouter(sequence, socket); 
	}
	
	public void run() {
		// 
		while(true) {
			route(); 
			break; 
		}
	}
	
	public void route() {
		// 
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