/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.server.handler;


import namoosori.fileserver.step7.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public class FileFindHandler implements FileHandler {
	//
	public FileFindHandler() {
		// 
		
	}
	
	@Override
	public ResponseMessage handle(RequestMessage request) {
		// 
		FileStore fileStore = getFileStore();
		String fileName = request.getValue(); 
		
		char[] fileContents = fileStore.readFile(fileName); 
		
		return new ResponseMessage(request.getServiceName(), String.valueOf(fileContents)); 
	}

	public FileStore getFileStore() {
		// 
		return FileStore.newInstance(); 
	}
}