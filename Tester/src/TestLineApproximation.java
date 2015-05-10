// File:    TestLineApproximation.java
// Created: 10/05/2015

/**
 * @author mahefa
 */
public class TestLineApproximation {

    public TestLineApproximation() {
    }

    public static void main(String[] args) {
        LineApproximation lineApproximation = new LineApproximation(50);
        int[][] lines = {
                {0, 100, 300, 100},
                {20, 105, 305, 100},
                {0, 100, 310, 100},
                {0, 90, 300, 110},
                {100, 300, 510, 300},
                {100, 295, 500, 300},
                {105, 300, 505, 290},
                {200, 200, 205, 390},
                {200, 200, 210, 400},
                {200, 210, 205, 394},
                {200, 207, 193, 400}
        };
        for (int[] line : lines) {
            lineApproximation.add(line[0], line[1], line[2], line[3]);
        }
        for (GroupLines group : lineApproximation.getGroupLines()) {
            System.out.println(group.getAverageLine());
        }
    }

}
