import java.util.ArrayList;

public class TestClass {

    public static void main(String[] args) {
        int radius = 4;
        int margin = 1;
        int minSize = 5;
        int maxSize = 100;
        int charSize = 30;
        for (int i = 0; i < 7 ; i+=2) {
            BinaryImage img = new BinaryImage("Test/h"+i+"0.jpg");
            BinaryImage img0 = new BinaryImage(img.getHeight(), img.getWidth());
            BinaryImage img1 = new BinaryImage(img.getHeight(), img.getWidth());
            BinaryImage img2 = new BinaryImage(img.getHeight(), img.getWidth());
            ConnectedPixel cp = new ConnectedPixel();
            ArrayList<ConnectedPixel> limg = cp.getConnectedPixels(radius, margin, img);
            int[] backgrounds = new int[] { 50, 100, 150};
            int ii = charSize * 2;
            int jj = charSize * 2;
            for (int j = 0, k = 0; j < limg.size(); j++) {
                if (limg.get(j).getHeight() > minSize
                        && limg.get(j).getHeight() < maxSize
                        && limg.get(j).getWidth() > minSize
                        && limg.get(j).getWidth() < maxSize) {
//                    BinaryImage nimg = limg.get(j).rasterize();
//                    limg.get(j).writeOnImage(img0, backgrounds[k++ % 3]);
//                    TextExtractionUtil.writeImage("/home/mahefa/Ts/tt" + j + ".jpg", nimg.rasterize() , "jpg");
                    CharacterPixel ch = limg.get(j).createCharaterPixel();
                    CharacterPixel ch1 = limg.get(j).createCharaterPixel(ii, jj);
                    jj += charSize;
                    if (jj > img1.getWidth() - 2 * charSize) {
                        jj = charSize * 2;
                        ii += charSize + 2;
                    }
//                    ch.scaleInto(charSize);
//                    ch1.scaleInto(charSize);
//                    ch.writeOnImage(img0, backgrounds[k++ % 3]);
//                    ch1.writeOnImage(img1, backgrounds[k++ % 3]);
                } else {
//                    if (limg.get(j).getBlackPixelCount() > HoughTransform.MIN_INTERSECTION) {
//                        limg.get(j).writeOnImage(img2, ColorB.WHITE);
//                        System.out.println("hopla!");
//                    }
                }
            }
            System.out.println("Finding lines");
//            HoughTransform ht = new HoughTransform();
//            ArrayList<double[]> v = ht.findLines(img2);
//            ht.drawLines(img2, v, ColorB.RED, 2);
            System.out.println("Done searching line");
            ImgProcUtil.writeImage("Test/h" + i + "1.jpg", img.rasterize(), "jpeg");
//            TEUtil.writeImage("/home/mahefa/a" + i + ".jpg", gimg.rasterize(), "jpeg");
//            TEUtil.writeImage("/home/mahefa/v" + (i) + ".jpg", img0.rasterize(), "jpeg");
//            TEUtil.writeImage("/home/mahefa/vv" + (i) + ".jpg", img1.rasterize(), "jpeg");
//            ImgProcUtil.writeImage("/home/mahefa/y" + (i) + ".jpg", img2.rasterize(), "jpeg");
            System.out.println("Done " + i);
        }
    }
}
