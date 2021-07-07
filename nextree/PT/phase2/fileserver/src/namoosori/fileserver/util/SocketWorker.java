package namoosori.fileserver.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class SocketWorker {
	//
	private static final String DEFAULT_CHAR_SET = "UTF-8"; 
	public static final int HEADER_LENGTH = 4; 
	private static int MAX_READ_WRITE_LENGTH = (1024 * 1024 * 10); //10MB

	//스트림은 단방향 통신을 한다는 특징이 있다. 하나의 스트림으로 입출력을 동시에 진행 할 수 없기 때문에 입력스트림과 출력스트림을 따로 만들어줘야한다.
	private InputStream inputStream;
	private OutputStream outputStream;
	
	private Socket socket; //AutoCloseable 인터페이스를 implements 하고 있다. ==> 자동으로 닫아줌

	public SocketWorker(Socket socket) {
		//
		this.socket = socket;		
		try {
			this.inputStream = socket.getInputStream();
			this.outputStream = socket.getOutputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void closeSocket() {
		//
		try {
			if (!this.socket.isClosed()) {
				this.socket.close();
			}
		} catch (IOException e) {
			throw new ReactFailException("Fail to close the socket --> " + e.getMessage());
		}
	}

	public String readMessage() {
		//
		String resultMessage = null; 
		
		try {
			byte[] headerBytes = read(HEADER_LENGTH);
			int bodyLength = ByteUtil.toInt(headerBytes);
			byte[] bodyBytes = read(bodyLength); 
			
			resultMessage = new String(bodyBytes, DEFAULT_CHAR_SET); //deprecated(완벽하게 bytes를 characters 타입으로 변환해주지 못함)
			                                                         // 다른 방법을 찾아보자!
		} catch (IOException e) {
			e.printStackTrace();
			throw new ReactFailException("Fail to read message. --> " + e.getMessage()); 
		} 

		return resultMessage;
	}

	public void writeMessage(String message) throws IOException {
		//
		write(ByteUtil.toBytes(message.length())); 
		write(message.getBytes());
	}
	
	public boolean isAlive() {
		//
		return !socket.isClosed();
	}
	
	public byte[] getReqeusterIp() {
		//
		return socket.getInetAddress().getAddress();
	}
	
	public byte[] read(int targetLen) throws IOException {
		
		if (targetLen > MAX_READ_WRITE_LENGTH) {
			throw new ReactFailException("Can't read more than 10MB -> " + targetLen); 
		}
		
		int readCount = 0; 
		int retryCount = 0;
		int allReadCount = 0;
		byte[] readBuffer = new byte[targetLen];
		
		while (allReadCount < targetLen) {
			readCount = inputStream.read(readBuffer, allReadCount, targetLen - allReadCount);
			if (readCount > 0) {
				allReadCount += readCount;
			} else if (readCount == -1) {
				throw new ReactFailException("Read EOF.") ;
			} else if (readCount == 0 && ++retryCount == 20) {
				throw new ReactFailException("Retry more than 20 times.") ;
			}
		}
		return readBuffer;
	}
	
	public void write(byte[] buffer) throws IOException {
		//
		if (outputStream == null || buffer == null) {
			throw new ReactFailException("buffer is null.");
		}

		if (buffer.length > MAX_READ_WRITE_LENGTH) {
			throw new ReactFailException("Can't write more than 10MB -> " + buffer.length); 
		}

		outputStream.write(buffer);
	}
}