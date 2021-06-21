package namoosori.fileserver.step3.server.handler;


import namoosori.fileserver.step3.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public class FileDeleteHandler implements FileHandler {
	//
	public FileDeleteHandler() {
		// 
		
	}
	
	@Override
	public ResponseMessage handle(RequestMessage request) {
		// 
		FileStore fileStore = getFileStore();
		String fileName = request.getValue(); 
		
		fileStore.deleteFile(fileName); 
		
		return new ResponseMessage(request.getServiceName(), String.valueOf(fileName)); 
	}

	public FileStore getFileStore() {
		// 
		return FileStore.newInstance(); 
	}
}