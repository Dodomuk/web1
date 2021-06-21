package namoosori.fileserver.step2.server.react;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import namoosori.fileserver.util.ReactFailException;

public class FileServerReactor extends Thread {
	//
	private int servicePort; 
	private ServerSocket serverSocket; 
	
	public FileServerReactor() {
		// 
		this.servicePort = 3333; 
	}

	private void initServerSocket() {
		// 
		try {
			serverSocket = new ServerSocket(servicePort); 
		} catch (IOException e) {
			throw new ReactFailException(e.getMessage());
		} 
	}

	@Override
	public void run() {
		//
		this.initServerSocket(); 
		int count = 0; 
		while(true) {
			//
			Socket clientSocket = null;

			try {
				synchronized (serverSocket) {
					clientSocket = serverSocket.accept();
				}
				
				new EventRouter(clientSocket).route(); //확인 : 괄호 제거 이상 무
				
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