package namoosori.fileserver.step5.server.react;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import namoosori.fileserver.util.ReactFailException;

public class FileServerReactor extends Thread {
	//
	private int servicePort; 
	private ServerSocket serverSocket;
	private EventQueue eventQueue; 
	
	public FileServerReactor(EventQueue eventQueue) {
		// 
		this.servicePort = 3333; 
		this.eventQueue = eventQueue; 
	}
	
	private void initServerSocket() {
		// 
		try {
			serverSocket = new ServerSocket(servicePort); 
		} catch (IOException e) {
			throw new ReactFailException(e.getMessage()); 
		} 
	}
	
	public void run() {
		//
		this.initServerSocket(); 
		long sequence = 0; 
		int count = 0; 
		
		while(true) {
			//
			Socket clientSocket = null;

			try {
				synchronized (serverSocket) {
					clientSocket = serverSocket.accept();
				}
				EventInfo eventInfo = new EventInfo(sequence, clientSocket); 
				eventQueue.add(eventInfo);
				
				if((++count % 1000) == 0) {
					System.out.format(" File processing: %05d \n", count); 
				}

			} catch (IOException e) {
				e.printStackTrace(); 
				continue; 
			}
		}
	}
}