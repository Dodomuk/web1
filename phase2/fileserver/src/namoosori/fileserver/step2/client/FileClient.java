package namoosori.fileserver.step2.client;

import namoosori.fileserver.step2.client.transfer.FileServiceStub;
import namoosori.fileserver.step2.context.FileContext;
import namoosori.fileserver.step2.context.FileService;
import namoosori.fileserver.util.FileUtil;

import java.io.File;

public class FileClient extends Thread{

    private String id;
    private int fileCount;
    private FileClientRunner parent;
    private double elapseTime;

    public FileClient(String id, int fileCount, FileClientRunner parent) {
        this.id = id;
        this.fileCount = fileCount;
        this.parent = parent;
    }

    public void run(){
        showStoreDemo();

    }

    private void before(){
        for (int i = 1; i < fileCount; i++) {
            String fileName = String.format("TestFile %s %d.text", id , i);
            File testFile = FileUtil.createFile(FileContext.CLIENT_TEMP_FOLDER,FileContext.CLIENT_STEP_STORE,fileName);
            FileUtil.write(testFile,(i + "테스트").toCharArray());
        }
    }

    private void after(){

        for (int i = 1; i < fileCount; i++) {

            String fileName = String.format("TestFile %s %d.text",id,i);
            FileUtil.deleteFile(FileContext.CLIENT_TEMP_FOLDER,FileContext.CLIENT_STEP_STORE,fileName);

        }

    }

    private void showStoreDemo(){

        before();
        FileService fileService = new FileServiceStub();

        long begin = System.currentTimeMillis();

        for (int i = 1; i < fileCount; i++) {
            String fileName = String.format("TestFile-%s-%d.text", id, i);
            File testFile = FileUtil.findFile(FileContext.CLIENT_TEMP_FOLDER, FileContext.CLIENT_STEP_STORE, fileName);
            fileService.store(testFile);
            fileService.delete(fileName);
        }

        long end = System.currentTimeMillis();
        this.elapseTime = (end - begin) / 1000.0;
        after();

    }

    private void showFindDemo(){

        FileService fileService = new FileServiceStub();

        for(int i = 1; i < fileCount; i++){
            String fileName = "TestFile" + i + ".txt";
            File file = fileService.find(fileName);

            System.out.println("Find find ---> " + file.getName());
        }
    }
}
