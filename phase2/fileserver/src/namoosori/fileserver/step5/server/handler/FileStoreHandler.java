package namoosori.fileserver.step5.server.handler;

import java.nio.file.FileAlreadyExistsException;

import namoosori.fileserver.step5.server.repo.FileStore;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public class FileStoreHandler implements FileHandler {
	//
	public FileStoreHandler() {
		// 
	}

	@Override
	public ResponseMessage handle(RequestMessage request) {
		// 
		FileStore fileStore = getFileStore();
		char[] contents = request.getValue().toCharArray();
		String fileName = request.getRemark(); 
		
		ResponseMessage response = null; 
		try {
			fileStore.writeFile(fileName, contents);
			response = new ResponseMessage(request.getServiceName(), fileName); 
		} catch (FileAlreadyExistsException e) {
			// 
			e.printStackTrace();
			response = new ResponseMessage(request.getServiceName(), fileName); 
			response.setSuccess(false);
			response.setReason(e.getMessage());
		} 

		return response; 
	}

	public FileStore getFileStore() {
		// 
		return FileStore.newInstance(); 
	}
}