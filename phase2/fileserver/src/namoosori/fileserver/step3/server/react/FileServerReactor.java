package namoosori.fileserver.step3.server.react;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import namoosori.fileserver.util.ReactFailException;

public class FileServerReactor extends Thread {
	//
	private int servicePort; 
	private ServerSocket serverSocket; 
	private ExecutorService executorService; 
	
	public FileServerReactor() {
		// 
		this.servicePort = 3333; 
		this.executorService = Executors.newFixedThreadPool(100);
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
		
		int threadSequence = 0; 
		int count = 0; 
		while(true) {
			//
			Socket clientSocket = null;

			try {
				synchronized (serverSocket) {
					clientSocket = serverSocket.accept();
				}
				executorService.execute(EventRouter.newInstance(threadSequence++, clientSocket)); 
				// EventRouter.newInstance(threadSequence++, clientSocket).start();
				
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