package namoosori.fileserver.step2.client;

import java.util.concurrent.TimeUnit;

public class FileStory {

    public static void main(String[] args) {
        FileStory fs = new FileStory();

        try {
            fs.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void start() throws InterruptedException{
        int fileCount = 1000;
        int delta = 5;

        for (int i = 0; i < 5; i++) {
            int id = i + 1;
            int threadCnt = id * delta;
            FileClientRunner runner = new FileClientRunner(String.valueOf(id),fileCount,threadCnt);
            runner.start();
            TimeUnit.SECONDS.sleep(1);
            runner.join();
        }
    }

}
