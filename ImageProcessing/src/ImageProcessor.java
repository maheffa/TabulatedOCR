// File:    ImageProcessor.java
// Created: 19/02/2015

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * @author mahefa
 */
public class ImageProcessor {

    private class ParallelProcessor implements Runnable {

        private int begin, end;
        private ProcessorFunction pf;
        private int[] src, dst;
        private int height, width;

        public ParallelProcessor(int begin, int[] src, int[] dst, int height, int width, ProcessorFunction pf) {
            this.pf = pf;
            this.src = src;
            this.dst = dst;
            this.height = height;
            this.width = width;
            this.begin = begin;
            this.end = Math.min(begin + CHUNK_SIZE, src.length);
        }

        @Override
        public void run() {
            for (int i = begin; i < end; i++) {
                dst[i] = pf.processPoint(i, src, height, width);
            }
        }
    }

    private static int CHUNK_SIZE = 100000;

    public void process(int[] src, int[] dst, int height, int width, ProcessorFunction pf) {
//        ExecutorService service = Executors.newFixedThreadPool(1);
        ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        ArrayList<Future> futureList = new ArrayList<Future>();
        for (int i = 0; i < src.length; i += CHUNK_SIZE) {
            futureList.add(service.submit(new ParallelProcessor(i, src, dst, height, width, pf)));
        }
        service.shutdown();
        for (Future f : futureList) {
            try {
                f.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}
