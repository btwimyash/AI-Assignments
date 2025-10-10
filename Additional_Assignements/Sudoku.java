
public class Sudoku {

    public static void print(int array[][]) {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static boolean issafe(int array[][], int row, int col, int digit) {
        //vertically down
        for (int i = 0; i < 9; i++) {
            if (array[i][col] == digit) {
                return false;
            }
        }

        //horizontally
        for (int j = 0; j < 9; j++) {
            if (array[row][j] == digit) {
                return false;
            }
        }

        //grid
        int sr = (row / 3) * 3;
        int sc = (col / 3) * 3;
        for (int i = sr; i < sr + 3; i++) {
            for (int j = sc; j < sc + 3; j++) {
                if (array[i][j] == digit) {
                    return false;
                }
            }
        }
        return true;
    }

    public static boolean sudokusolver(int array[][], int row, int col) {
        //base case
        if (row == 9) {
            // print(array);
            return true;
        }

        int newrow = row, newcol = col + 1;
        if (col + 1 == 9) {
            newrow = row + 1;
            newcol = 0;
        }

        if (array[row][col] != 0) {
            return sudokusolver(array, newrow, newcol);
        }

        for (int digit = 1; digit <= 9; digit++) {
            if (issafe(array, row, col, digit)) {
                array[row][col] = digit;
                if (sudokusolver(array, newrow, newcol)) {
                    return true;
                }
                array[row][col] = 0;
            }
        }
        return false;
    }

    public static void main(String args[]) {
        int sudoku[][] = {{0, 0, 8, 0, 0, 0, 0, 0, 0},
        {4, 9, 0, 1, 5, 7, 0, 0, 2},
        {0, 0, 3, 0, 0, 4, 1, 9, 0},
        {1, 8, 5, 0, 6, 0, 0, 2, 0},
        {0, 0, 0, 0, 2, 0, 0, 6, 0},
        {9, 6, 0, 4, 0, 5, 3, 0, 0},
        {0, 3, 0, 0, 7, 2, 0, 0, 4},
        {0, 4, 9, 0, 3, 0, 0, 5, 7},
        {8, 2, 7, 0, 0, 9, 0, 1, 3}
        };

        if (sudokusolver(sudoku, 0, 0)) {
            System.out.println("Solution exists");
            print(sudoku);
        } else {
            System.out.println("Solution does not exists");
        }
    }
}
