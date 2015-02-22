// File:    TestOpenCV.java
// Created: 20/02/2015

import java.awt.image.BufferedImage;

/**
 * @author mahefa
 */
public class TestOpenCV extends Tester{

    public TestOpenCV() {
    }

    @Override
    public void runTest() {
        for(int i = 0; i < 9; i++) {
            BufferedImage img = ImgProcUtil.readImage("Test/t"+i+".jpg");
//        img = ImgProcUtil.resize(img, 1.0 / 4.0);
//        img = ImgProcUtil.resize(img, 4.0);
            BufferedImage img1, img2;
            ImageProcessor ip = new ImageProcessor();
            BoxBlur bb = new BoxBlur(5);
            long a = System.currentTimeMillis();
            img1 = ImgProcUtil.boxblur(img, 5);
            a = System.currentTimeMillis() - a;
            System.out.println("done opencv");
            long b = System.currentTimeMillis();
            int[][] c = ImgProcUtil.getRGBChannels(img);
            int[][] d = new int[3][c[0].length];
            for (int j = 0; j < 3; j++) {
                ip.process(c[j], d[j], img.getHeight(), img.getWidth(), bb);
            }
            img2 = ImgProcUtil.writeFromRGBChannels(d, img.getHeight(), img.getWidth());
            b = System.currentTimeMillis() - b;
            System.out.println("Opencv : " + a);
            System.out.println("Mine : " + b);
            ImgProcUtil.writeImage("Test/h"+i+"0.jpg", img1, "jpeg");
            ImgProcUtil.writeImage("Test/h"+i+"1.jpg", img2, "jpeg");
        }
    }

    class BoxBlur implements ProcessorFunction {

        private int boxSize;
        private int neighbor;

        public BoxBlur(int boxSize) {
            this.boxSize = boxSize;
            neighbor = boxSize / 2 - 1;
        }

        @Override
        public int processPoint(int index, int[] src, int height, int width) {
            int i0 = index / width, j0 = index % width;
            int ii = i0 < neighbor ? neighbor : i0 >= height - neighbor ? height - neighbor - 1 : i0;
            int jj = j0 < neighbor ? neighbor : j0 >= width - neighbor ? width - neighbor - 1 : j0;
            int sum = 0;
            int count = 0;
            for (int i = ii - neighbor; i <= ii + neighbor; i++) {
                for (int j = jj - neighbor; j <= jj + neighbor; j++) {
                    sum += src[i * width + j];
                    count++;
                }
            }
            return sum / count;
        }
    }
}
