/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step1;

import namoosori.fileserver.step1.folder.FileStore;

public class StoryAssistant {
	//
	public static void main(String[] args) {
		// 
		showDemo(); 
	}
	
	private static void showDemo() {
		//
		String folderName = "FileRepository"; 
		String fileName = "TestFile000001.txt"; 
		String contents = "Hello, file server."; 
		
		FileStore folder = new FileStore(folderName);
		folder.writeFile(fileName, contents.toCharArray());
		
		char[] resultContents = folder.readFile(fileName);
		
		System.out.println(String.valueOf(resultContents)); 
	}
}