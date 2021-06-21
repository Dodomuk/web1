package namoosori.fileserver.step5.client;

import java.util.concurrent.TimeUnit;

public class FileStory {
	//
	public static void main(String[] args) throws InterruptedException {
		// 
		FileStory story = new FileStory(); 
		story.start(); 
	}
	
	public void start() throws InterruptedException {
		//
		int fileCount = 1000; 
		int delta = 5; 

		for(int i=0 ; i<5; i++) {
			int id = i + 1; 
			int threadCount = id * delta; 
			FileClientRunner runner = new FileClientRunner(String.valueOf(id), fileCount, threadCount); 
			runner.start();
			TimeUnit.SECONDS.sleep(1);
			runner.join(); 
		}
	}
}