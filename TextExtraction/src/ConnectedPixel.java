import java.util.*;

class ConnectedPixelComparator implements Comparator<ConnectedPixel> {

    @Override
    public int compare(ConnectedPixel c1, ConnectedPixel c2) {
        if (c1.getMaxx() > c2.getMinx() && c2.getMaxx() > c1.getMinx()) {
            return c1.getMiny() - c2.getMiny();
        } else if (c1.getMaxx() < c2.getMinx()) {
            return -1;
        } else {
            return 1;
        }
    }
}

public class ConnectedPixel {

    private Set<int[]> pixels;
    private int minx, miny, maxx, maxy;

    public ConnectedPixel() {
        pixels = new HashSet<int[]>();
        minx = Integer.MAX_VALUE;
        miny = Integer.MAX_VALUE;
        maxx = Integer.MIN_VALUE;
        maxy = Integer.MIN_VALUE;
    }

    public BinaryImage rasterize() {
        int height = this.getHeight() + 2;
        int width = this.getWidth() + 2;
        BinaryImage bwimg = new BinaryImage(height, width);
        for (int[] pix : pixels) {
            bwimg.setPixel(pix[0] - minx + 1, pix[1] - miny + 1, BinaryImage.BLACK);
        }
        return bwimg;
    }

    public CharacterPixel createCharaterPixel() {
        CharacterPixel cp = new CharacterPixel(this.getHeight() + 2, this.getWidth() + 2,
                minx + getHeight() / 2, miny + getWidth() / 2);
        for (int[] pix : pixels) {
            cp.setPixel(pix[0] + - minx + 1, pix[1] - miny + 1, BinaryImage.BLACK);
        }
        return cp;
    }

    public CharacterPixel createCharaterPixel(int x, int y) {
        CharacterPixel cp = new CharacterPixel(this.getHeight() + 2, this.getWidth() + 2, x, y);
        for (int[] pix : pixels) {
            cp.setPixel(pix[0] + - minx + 1, pix[1] - miny + 1, BinaryImage.BLACK);
        }
        return cp;
    }

    public void add(int x, int y) {
        if (x < minx) minx = x;
        if (x > maxx) maxx = x;
        if (y < miny) miny = y;
        if (y > maxy) maxy = y;
        pixels.add(new int[]{x, y});
    }

    public static ArrayList<ConnectedPixel> getConnectedPixels(BinaryImage img) {
        return getConnectedPixels(1, 1, img);
    }

    public static ArrayList<ConnectedPixel> getConnectedPixels( int radius, int margin, BinaryImage img) {
        ArrayList<ConnectedPixel> listConnectedPixel = new ArrayList<ConnectedPixel>();
        if (radius < 1) {
            return null;
        }

        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (img.getPixel(i, j) == BinaryImage.BLACK) {
                    listConnectedPixel.addAll(
                            getConnectedPixels(i, j, radius, img).parseCharacters(margin));
                }
            }
        }

        // returning everything to B & W after detecting all the letters.
        for (int i = 0; i < img.getHeight(); i++) {
            for (int j = 0; j < img.getWidth(); j++) {
                if (img.getPixel(i, j) != BinaryImage.WHITE) {
                    img.setPixel(i, j, BinaryImage.BLACK);
                }
            }
        }

        return listConnectedPixel;
    }

    private static ConnectedPixel getConnectedPixels(int x, int y, int radius, BinaryImage img) {
        /*
        IMPORTANT: the side effect (changing pixel color) must remain and fix it only outside
        this function. Reason: If the color is not changed, the letter will be re-processed.
         */
        ConnectedPixel pixels = new ConnectedPixel();
        LinkedList<Integer> queueX = new LinkedList<Integer>();
        LinkedList<Integer> queueY = new LinkedList<Integer>();
        queueX.add(x);
        queueY.add(y);
        while (!queueX.isEmpty()) {
            int i = queueX.removeFirst();
            int j = queueY.removeFirst();
            if (img.getPixel(i, j) == BinaryImage.BLACK) {
                img.setPixel(i, j, 100);
                pixels.add(i, j);
                for (int k = i - radius; k <= i + radius; k++) {
                    for (int l = j - radius; l <= j + radius; l++) {
                        if ((k >= 0 && k < img.getHeight() )
                                && (l >= 0 && l < img.getWidth())
                                && (img.getPixel(k, l) == BinaryImage.BLACK)) {
                            queueX.addLast(k);
                            queueY.addLast(l);
                        }
                    }
                }
            }
        }

        return pixels;
    }

    public ArrayList<ConnectedPixel> parseCharacters(int margin) {
        ArrayList<ConnectedPixel> lcp = new ArrayList<ConnectedPixel>();
        if (this.getWidth() > this.getHeight()) {
            ArrayList<Integer> split = new ArrayList<Integer>();
            BinaryImage img = rasterize();

            for (int i = 0; i < img.getWidth() - margin;) {
                boolean detected = false;
                for (int j = 0; j < img.getHeight() && !detected; j++) {
                    for (int k = 0; k < margin && !detected; k++) {
                        if (img.getPixel(j, i + k) != BinaryImage.WHITE) {
                            detected = true;
                            i = i + k + 1;
                        }
                    }
                }
                if (!detected) {
                    split.add(miny + i + margin / 2);
                    i += margin;
                    lcp.add(new ConnectedPixel());
                }
            }
            lcp.add(new ConnectedPixel());
            split.add(maxy);

            for (int[] pix : pixels) {
                for (int i = 0; i < split.size(); i++) {
                    if (pix[1] <= split.get(i)) {
                        lcp.get(i).add(pix[0], pix[1]);
                        break;
                    }
                }
            }
        } else {
            lcp.add(this);
        }
        return lcp;
    }

    public int getBlackPixelCount() {
        return this.pixels.size();
    }

    public int getHeight() {
        return maxx - minx;
    }

    public int getWidth() {
        return maxy - miny;
    }

    public int size() {
        return pixels.size();
    }

    public int getMinx() {
        return minx;
    }

    public int getMiny() {
        return miny;
    }

    public int getMaxx() {
        return maxx;
    }

    public int getMaxy() {
        return maxy;
    }

    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("x : [").append(minx).append("-").append(maxx).append("], ");
        str.append("y : [").append(miny).append("-").append(maxy).append("], ");
        str.append("pixel count: ").append(pixels.size());
        return str.toString();
    }
}
