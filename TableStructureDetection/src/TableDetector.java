// File:    TableDetector.java
// Created: 04/05/2015

import java.awt.image.BufferedImage;
import java.io.File;

import org.bytedeco.javacpp.Pointer;
import static org.bytedeco.javacpp.opencv_core.*;
import static org.bytedeco.javacpp.opencv_imgproc.*;

/**
 * @author mahefa
 */
public class TableDetector {

    private IplImage iplImage = null;
    private BufferedImage bufferedImage = null;
    private String filePath = "";

    public TableDetector(BufferedImage bufferedImage) {
        this.bufferedImage = bufferedImage;
        this.iplImage = IplImage.createFrom(bufferedImage);
    }

    public TableDetector(String filePath) {
        this(ImgProcUtil.readImage(filePath));
        this.filePath = filePath;
    }

    public BufferedImage detectLine(boolean writeIntoFile) {
        BufferedImage res = applyHoughLineProbabilistic(bufferedImage);
        String filePath = this.filePath.equals("") ? "res.png" : this.filePath;
        if (writeIntoFile) {
            String[] pbe = SerializerUtil.getPathBaseExtension(filePath);
            ImgProcUtil.writeImage(pbe[0] + File.separator + pbe[1] + ".hough." + pbe[2], res);
        }
        return res;
    }

    public static BufferedImage applyHoughLineProbabilistic(BufferedImage input) {
        IplImage src = IplImage.createFrom(input);
        IplImage dst = cvCreateImage(src.cvSize(), src.depth(), 1);
        IplImage colorDst = cvCreateImage(src.cvSize(), src.depth(), 3);
        CvMemStorage storage = cvCreateMemStorage(0);

        cvCanny(src, dst, 50, 100, 3);
        cvCvtColor(dst, colorDst, CV_GRAY2BGR);

        CvSeq lines = cvHoughLines2(dst, storage, CV_HOUGH_PROBABILISTIC, 1, Math.PI / 180, 40, 50, 10);

        for (int i = 0; i < lines.total(); i++) {
            // Based on JavaCPP, the equivalent of the C code:
            // CvPoint* line = (CvPoint*)cvGetSeqElem(lines,i);
            // CvPoint first=line[0], second=line[1]
            // is:
            Pointer line = cvGetSeqElem(lines, i);
            CvPoint pt1  = new CvPoint(line).position(0);
            CvPoint pt2  = new CvPoint(line).position(1);

//            System.out.println("Line spotted: ");
//            System.out.println("\t pt1: " + pt1);
//            System.out.println("\t pt2: " + pt2);
            cvLine(colorDst, pt1, pt2, CV_RGB(0, 255, 0), 3, CV_AA, 0); // draw the segment on the image
        }
        return colorDst.getBufferedImage();
    }

}
