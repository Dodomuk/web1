/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.handler;

import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public interface FileHandler {
	//
	public ResponseMessage handle(RequestMessage request);
}