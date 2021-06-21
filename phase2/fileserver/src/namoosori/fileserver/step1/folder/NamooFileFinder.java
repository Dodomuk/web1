package namoosori.fileserver.step1.folder;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class NamooFileFinder {
	//
	
	public NamooFile find(String folderName, String fileName) {
		//
		return NamooFile.getFile(folderName, fileName);
	}
	
	public NamooFile find(String[] folderPaths, String fileName) {
		//
		
		String pathName = null;
		
		try {
			String cannonicalPath = (new File(".")).getCanonicalPath();
		    String fileSeparator = System.getProperty("file.separator"); 

		    StringBuilder builder = new StringBuilder();
		    builder.append(cannonicalPath).append(fileSeparator);
		    
		    for (String path: folderPaths) {
		    	builder.append(path).append(fileSeparator);
		    }
		    pathName = builder.toString();

		    
			Path path = Paths.get(pathName); 
	        if (!Files.exists(path)) {
                Files.createDirectories(path);
	        }
	        
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NamooFile.getFile(pathName, fileName);
	}

}
