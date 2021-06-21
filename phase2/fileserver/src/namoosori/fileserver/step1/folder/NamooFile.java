package namoosori.fileserver.step1.folder;

import java.io.BufferedReader;
import java.io.CharArrayWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class NamooFile {
	//
	private String fileName;
	private File file; 
	
	private NamooFile(String fileName, File file) {
		// 
		this.fileName = fileName; 
		this.file = file; 
	}
	
	public String getFileName() {
		return fileName; 
	}
	
	public static NamooFile getFile(String folderFullPath, String fileName) {
		//
		String fullFileName = folderFullPath + System.getProperty("file.separator") + fileName;
		
		return new NamooFile(fileName, new File(fullFileName)); 
	}
	
	public boolean exists() {
		//
		return this.file.exists();
	}
	
	public boolean create() {
		//
		if (this.file.exists()) {
			this.file.delete();
		}
		boolean created = false;
		
		try {
			created = this.file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return created;
	}
	
	public boolean write(char[] contents) {
		//
		FileWriter fileWriter = null;
		boolean successful = false;
		
		try {
			fileWriter = requestFileWriter();
			fileWriter.write(contents);
			
			successful = true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try { 
				if (fileWriter != null) fileWriter.close(); 
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return successful;
	}
	
	public char[] read() {
		// 
        BufferedReader reader = null; 
        CharArrayWriter contents = new CharArrayWriter( (int) this.file.length() );
        
		try {
	        reader = requestReader();
	        String line = null;
	        
			while (true) {
				if((line = reader.readLine()) == null) {
					break;
				}
				contents.write(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (reader != null) reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		return contents.toCharArray(); 
	}

	private BufferedReader requestReader() throws IOException {
		//
		return new BufferedReader(new FileReader(this.file));
	}

	private FileWriter requestFileWriter() throws IOException {
		//
		return new FileWriter(this.file);
	}
	

	public static void main(String[] args) {
		// 
		NamooFile namooFile = NamooFile.getFile("resource/FileRepository/step1", "TestFile000001.txt");
		
		if (!namooFile.exists()) {
			namooFile.create();
		}
		namooFile.write("Hello.".toCharArray());
		
		char[] result = namooFile.read(); 
		System.out.println(String.valueOf(result));
	}
}