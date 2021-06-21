/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.react;

import java.net.Socket;

public class EventInfo {
	//
	private long sequence; 
	private Socket socket; 
	
	public EventInfo(long sequence, Socket socket) {
		// 
		this.sequence = sequence; 
		this.socket = socket; 
	}
	
	public Socket getSocket() {
		// 
		return socket; 
	}
	
	public long getSequence() {
		return sequence; 
	}
}