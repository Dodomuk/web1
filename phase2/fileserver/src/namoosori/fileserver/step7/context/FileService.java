/*
 * COPYRIGHT (c) NEXTREE Consulting 2014
 * This software is the proprietary of NEXTREE Consulting CO.  
 * 
 * @author <a href="mailto:tsong@nextree.co.kr">Song, Taegook</a>
 * @since 2014. 6. 10.
 */

package namoosori.fileserver.step7.context;

import java.io.File;
import java.util.List;

public interface FileService {
	//
	public String store(File file); 
	public String delete(String fileName); 
	public File find(String fileName); 
	public List<String> listFiles(); 
}