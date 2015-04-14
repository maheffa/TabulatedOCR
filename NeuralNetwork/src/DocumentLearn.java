// File:    DocumentLearn.java
// Created: 14/04/2015

/**
 * @author mahefa
 */
public class DocumentLearn {

    private String docImgPath, docTxtath;
    private BinaryImage bImg;
    private String txtDocument;

    public DocumentLearn(String docImgPath, String docTxtPath) {
        this.docImgPath = docImgPath;
        this.docTxtath = docTxtPath;
        this.bImg = new BinaryImage(docImgPath);

    }



}
