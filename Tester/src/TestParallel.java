import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TestParallel implements Runnable {
    public int chunkSize;

    public TestParallel(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    @Override
    public void run() {
        int[] t = new int[chunkSize];
        int[] k = Arrays.copyOf(t, t.length);
        Random r = new Random();
        for (int i = 0; i < chunkSize; i++) {
            t[i] = r.nextInt();
        }
        for (int i = 0; i < chunkSize; i++) {
            t[i] *= t[i];
        }
        for (int i = 1; i < chunkSize - 1; i++) {
            k[i] = t[i-1] * t[i+1] - t[i];
        }
    }

    public static void main(String[] args) {
        int totalNum = 10000000;
        int ncore = Runtime.getRuntime().availableProcessors();
        for (int taskNumber = 1; taskNumber < 20; taskNumber++) {
            System.out.print("nTask = " + taskNumber + "; ");
            for (int threadPoolSize = 1; threadPoolSize < 6; threadPoolSize++) {
                long a = System.currentTimeMillis();
                ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
                for (int i = 0; i < taskNumber; i++) {
                    executor.execute(new TestParallel(totalNum / taskNumber));
                }
                executor.shutdown();
                try {
                    executor.awaitTermination(1, TimeUnit.DAYS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print((System.currentTimeMillis() - a) + "\t");
            }
            System.out.println();
        }
    }

}
