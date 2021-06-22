package namoosori.fileserver.step3.client;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class FileClientRunner extends Thread {

    private int fileCount;
    private int threadCount;
    private List<Double> elapseTimes;

    public FileClientRunner(String id, int fileCount, int threadCount) {
        super(id);
        this.fileCount = fileCount;
        this.threadCount = threadCount;
        this.elapseTimes = new ArrayList<>();
    }

    public void join(FileClientRunner parent) throws InterruptedException {
        parent.join();
    }

    public void addElapseTime(double time) {
        this.elapseTimes.add(time);
    }

    public double getAverageTime() {
        double total = 0.0;
        for (Double elapseTime : elapseTimes) {
            total += elapseTime;
        }

        return total / elapseTimes.size();
    }

    public void run() {
        System.out.format("\n## id:%s, threadCount:%, fileCount:%d Started... \n", getName(), threadCount, fileCount);
        FileClient client = null;

        for (int i = 0; i < threadCount; i++) {
            client = new FileClient(String.valueOf(i), fileCount,this);
            client.start();
        }

        try {
            TimeUnit.MICROSECONDS.sleep(1000);
            client.join();
            System.out.format(" ====> 처리시간 : %f \n", getAverageTime());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
