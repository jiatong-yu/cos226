/* *****************************************************************************
 *  Name:    Jiatong Yu
 *  NetID:   jiatongy
 *  Precept: P04
 *
 *  Partner Name:    NA
 *  Partner NetID:   NA
 *  Partner Precept: NA
 *
 *  Description:  content-aware image resizing technique
 * where the image is reduced in size by one pixel of height (or width) at a time
 * Using bottom-up dynamic programming approach to find the minimum
 * energy path
 *
 **************************************************************************** */

import edu.princeton.cs.algs4.Picture;
import edu.princeton.cs.algs4.StdOut;

public class SeamCarver {
    // copy of input picture
    private Picture picture;

    // height and weight of picture
    private int h, w;


    // create a seam carver object based on the given picture
    public SeamCarver(Picture picture) {
        if (picture == null) throw new IllegalArgumentException();
        this.picture = new Picture(picture);
        this.h = picture.height();
        this.w = picture.width();
    }

    // current picture
    public Picture picture() {
        return new Picture(picture);
    }

    // width of current picture
    public int width() {
        return w;
    }

    // height of current picture
    public int height() {
        return h;
    }

    // energy of pixel at column x, row y
    public double energy(int col, int row) {
        if (col < 0 || col > w - 1 || row < 0 || row > h - 1) {
            throw new IllegalArgumentException();
        }

        /* @citation Adapted from precept: https://us.edstem.org/courses/638/
        lessons/6595/slides/31117/
     */
        int prevRow = (row + h - 1) % h;
        int nextRow = (row + h + 1) % h;
        int prevCol = (col + w - 1) % w;
        int nextCol = (col + w + 1) % w;


        // calculate delta row
        // get(col, row)
        int rgbPrevRow = picture.getRGB(col, prevRow);
        int rgbNextRow = picture.getRGB(col, nextRow);

        double dRed = ((rgbPrevRow >> 16) & 0xFF) - ((rgbNextRow >> 16) & 0xFF);
        double dGreen = ((rgbPrevRow >> 8) & 0xFF) - ((rgbNextRow >> 8) & 0xFF);
        double dBlue = ((rgbPrevRow) & 0xFF) - ((rgbNextRow) & 0xFF);

        double dRow = dRed * dRed + dGreen * dGreen + dBlue * dBlue;


        // calculate delta col
        int rgbPrevCol = picture.getRGB(prevCol, row);
        int rgbNextCol = picture.getRGB(nextCol, row);

        /* @citation Adapted from: https://algs4.cs.princeton.edu/code/
        javadoc/edu/princeton/cs/algs4/Picture.html
     */
        dRed = ((rgbPrevCol >> 16) & 0xFF) - ((rgbNextCol >> 16) & 0xFF);
        dGreen = ((rgbPrevCol >> 8) & 0xFF) - ((rgbNextCol >> 8) & 0xFF);
        dBlue = ((rgbPrevCol) & 0xFF) - ((rgbNextCol) & 0xFF);

        double dCol = dRed * dRed + dGreen * dGreen + dBlue * dBlue;

        double energy = Math.sqrt(dCol + dRow);
        return energy;
    }

    // sequence of indices for vertical seam
    public int[] findVerticalSeam() {
        double[][] table = energyTable();
        return minPathDP(table);
    }

    // sequence of indices for horizontal seam
    public int[] findHorizontalSeam() {
        double[][] table = energyTable();
        table = transpose(table);
        return minPathDP(table);

    }


    // remove horizontal seam from current picture
    public void removeHorizontalSeam(int[] seam) {
        if (seam == null || seam.length != w || h == 1)
            throw new IllegalArgumentException();


        Picture pic = new Picture(w, h - 1);
        int j = seam[0];
        for (int col = 0; col < w; col++) {
            int row = 0, index = 0;
            while (index < h - 1) {
                // check if differ by more than 1
                int toCarve = seam[col];
                if (Math.abs(j - toCarve) > 1) throw new IllegalArgumentException();
                if (toCarve < 0 || toCarve >= h)
                    throw new IllegalArgumentException();
                if (row == toCarve) row++;

                int rgb = picture.getRGB(col, row);
                pic.setRGB(col, index, rgb);

                row++;
                index++;
            }
            j = seam[col];
        }
        picture = pic;
        h--;

    }

    // remove vertical seam from current picture
    public void removeVerticalSeam(int[] seam) {
        if (seam == null || seam.length != h || w == 0)
            throw new IllegalArgumentException();

        Picture pic = new Picture(w - 1, h);

        int j = seam[0];
        for (int row = 0; row < h; row++) {
            int col = 0, index = 0;
            while (index < w - 1) {
                int toCarve = seam[row];
                // check if differ by more than 1
                if (Math.abs(j - toCarve) > 1) throw new IllegalArgumentException();
                if (toCarve < 0 || toCarve >= w) throw new IllegalArgumentException();

                if (col == toCarve) {
                    col++;
                }
                int rgb = picture.getRGB(col, row);
                pic.setRGB(index, row, rgb);

                index++;
                col++;
            }
            j = seam[row];
        }

        picture = pic;
        w--;

    }

    // helper method: transpose
    private double[][] transpose(double[][] a) {

        double[][] b = new double[h][w];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                b[row][col] = a[col][row];
            }
        }
        return b;
    }


    // helper method: Dynamic Programming approach to seam
    private int[] minPathDP(double[][] energyT) {
        int width = energyT.length;
        int height = energyT[0].length;
        int[][] edgeTo = new int[width][height - 1];
        double[][] distTo = new double[width][height];

        // initialize for DP
        for (int col = 0; col < width; col++) {
            distTo[col][0] = energyT[col][0];
        }

        // bottom-up DP
        for (int row = 1; row < height; row++) {     // this is topological order
            for (int col = 0; col < width; col++) {
                double champ = distTo[col][row - 1];
                int champCol = col;

                if (col != 0 &&
                        distTo[col - 1][row - 1] < champ) {
                    champ = distTo[col - 1][row - 1];
                    champCol = col - 1;
                }

                if (col != width - 1 &&
                        distTo[col + 1][row - 1] < champ) {
                    champ = distTo[col + 1][row - 1];
                    champCol = col + 1;
                }

                distTo[col][row] = champ + energyT[col][row];
                edgeTo[col][row - 1] = champCol;
            }
        }


        // track back
        int track = 0;
        for (int col = 0; col < width; col++) {
            if (distTo[col][height - 1] < distTo[track][height - 1]) track = col;
        }

        int[] res = new int[height];
        res[height - 1] = track;
        for (int row = height - 2; row >= 0; row--) {
            res[row] = edgeTo[res[row + 1]][row];
        }

        return res;
    }


    // helper method: create energy table
    private double[][] energyTable() {
        double[][] energyT = new double[w][h];
        for (int row = 0; row < h; row++) {
            for (int col = 0; col < w; col++) {
                energyT[col][row] = energy(col, row);
            }
        }
        return energyT;
    }


    public static void main(String[] args) {
        Picture pic = SCUtility.randomPicture(
                Integer.parseInt(args[0]), Integer.parseInt(args[1]));
        SeamCarver seam = new SeamCarver(pic);
        StdOut.println("size of image received: "
                               + "(" + seam.width() + ", " + seam.height() + ")");
        StdOut.println("horizontal seam: ");
        int[] hori = seam.findHorizontalSeam();
        for (int i = 0; i < hori.length; i++) StdOut.print(hori[i] + " ");
        StdOut.println();

        StdOut.println("vertical seam: ");
        int[] vert = seam.findVerticalSeam();
        for (int i = 0; i < vert.length; i++) StdOut.print(vert[i] + " ");

        StdOut.println(seam.energy(0, 0));


        seam.removeHorizontalSeam(hori);
        StdOut.println("successful remove hori");
        seam.removeVerticalSeam(seam.findVerticalSeam());
        StdOut.println("successful remove verti");
        pic = seam.picture();
        pic.show();
    }
}
