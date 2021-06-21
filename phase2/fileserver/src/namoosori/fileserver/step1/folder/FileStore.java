package namoosori.fileserver.step1.folder;

public class FileStore {
	//
	private final String[] storePaths;
	
	private String name; 
	
	public FileStore(String name) {
		// 
		this.name = name; 
		this.storePaths = new String[] {"resource", this.name, "step1"};
	}
	
	public char[] readFile(String fileName) {
		// 
		NamooFile namooFile = new NamooFileFinder().find(storePaths, fileName);
		
		if (!namooFile.exists()) {
			return null; 
		}
		
		return namooFile.read();
	}
	
	public void writeFile(String fileName, char[] fileStream) {
		// 
		NamooFile namooFile = new NamooFileFinder().find(storePaths, fileName);
		namooFile.create();
		
		namooFile.write(fileStream);
	}
}