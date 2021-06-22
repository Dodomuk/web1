package namoosori.fileserver.step3.client;

import java.util.concurrent.TimeUnit;

public class FileStory {

    public static void main(String[] args) {

        FileStory story = new FileStory();
        try {
            story.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    public void start() throws InterruptedException{
        int fileCount = 1000;
        int delta = 5;

        for (int i = 0; i < 5; i++) {

            int id = i + 1;
            int threadCount = id * delta;
            FileClientRunner runner = new FileClientRunner(String.valueOf(id), fileCount, threadCount);
            runner.start();
            TimeUnit.SECONDS.sleep(1);
            runner.join();

        }
    }

}
