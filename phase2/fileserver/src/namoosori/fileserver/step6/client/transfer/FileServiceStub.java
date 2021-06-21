package namoosori.fileserver.step6.client.transfer;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import namoosori.fileserver.step3.context.FileCommand;
import namoosori.fileserver.step3.context.FileContext;
import namoosori.fileserver.step3.context.FileService;
import namoosori.fileserver.util.DispatchFailException;
import namoosori.fileserver.util.FileUtil;
import namoosori.fileserver.util.RequestMessage;
import namoosori.fileserver.util.ResponseMessage;

public class FileServiceStub implements FileService {
	//
	private SocketDispatcher dispatcher; 
	
	public FileServiceStub() {
		// 
	}
	
	@Override
	public String store(File file) {
		// 
		this.dispatcher = getDispatcher(); 
		
		char[] contents = FileUtil.read(file); 
		RequestMessage requestMessage = new RequestMessage(FileCommand.Store.name(), String.valueOf(contents));
		requestMessage.setRemark(file.getName());
		
		ResponseMessage response = null;
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace(); 
			return null; 
		} 
		
		dispatcher.close();
		
		return response.getValue(); 
	}
	
	@Override
	public String delete(String fileName) {
		// 
		this.dispatcher = getDispatcher(); 
		
		RequestMessage requestMessage = new RequestMessage(FileCommand.Delete.name(), fileName); 
		ResponseMessage response = null; 
		try {
			response = dispatcher.dispatchReturn(requestMessage);
		} catch (IOException e) {
			e.printStackTrace(); 
			return null; 
		} 
		
		dispatcher.close();
		
		return response.getValue(); 
	}

	@Override
	public File find(String fileName) {
		// 
		this.dispatcher = getDispatcher(); 
		RequestMessage requestMessage = new RequestMessage(FileCommand.Find.name(), fileName); 
		
		File resultFile = null; 
		try {
			ResponseMessage responseMessage = dispatcher.dispatchReturn(requestMessage);
			this.dispatcher.close();
			if (!responseMessage.isSuccess()) {
				throw new DispatchFailException(responseMessage.getReason());
			}
			
			char[] contents = responseMessage.getValue().toCharArray(); 
			resultFile = FileUtil.createFile(FileContext.CLIENT_TEMP_FOLDER, FileContext.CLIENT_STEP_FIND, fileName);
			FileUtil.write(resultFile, contents); 	
			
		} catch (IOException e) {
			throw new DispatchFailException(e.getMessage());
		}
		
		dispatcher.close();
		return resultFile;
	}
	
	@Override
	public List<String> listFiles() {
		// 
		this.dispatcher = getDispatcher(); 
		RequestMessage requestMessage = new RequestMessage(FileCommand.ListFiles.name(), 
				String.valueOf(0) + "-" + String.valueOf(10));  
		
		List<String> resultList = null; 
		try {
			ResponseMessage responseMessage = dispatcher.dispatchReturn(requestMessage);
			this.dispatcher.close();
			if (!responseMessage.isSuccess()) {
				throw new DispatchFailException(responseMessage.getReason());
			}
			resultList = (new Gson()).fromJson(responseMessage.getValue(), new TypeToken<ArrayList<String>>() {}.getType()); 
		} catch (IOException e) {
			throw new DispatchFailException(e.getMessage());
		}
		dispatcher.close();
		
		return resultList; 
	}
	
	private SocketDispatcher getDispatcher() {
		// 
		return SocketDispatcher.getInstance("127.0.0.1", 3333); 
	}
}
