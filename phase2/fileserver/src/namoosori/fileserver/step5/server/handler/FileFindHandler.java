package namoosori.fileserver.step5.server.handler;


import namoosori.fileserver.step5.server.repo.FileStore;
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