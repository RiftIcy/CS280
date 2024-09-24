public class Main {
    private static final int BOARD_SIZE = 8;
    //All the moves the knight can make
    private static final int[] moveRow = {-2, -2, -1, -1, 1, 1, 2, 2};
    private static final int[] moveCol = {1, -1, 2, -2, 2, -2, -1, 1};

    //If the square we are checking is unvisited and in the board
    private static boolean isValidMove(int row, int col, int[][] board) {
        return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == -1);
    }
    
    private static boolean solve(int[][] board, int row, int col, int stepCount) {
        //If we went on every square once we are done
        if(stepCount == BOARD_SIZE * BOARD_SIZE + 1) {
            return true;
        }

        //Does the recursion for all 8 moves
        for(int i = 0; i < 8; i++) {
            int nextRow = row + moveRow[i];
            int nextCol = col + moveCol[i];

            if(isValidMove(nextRow, nextCol, board)) {
                board[nextRow][nextCol] = stepCount;
                if(solve(board, nextRow, nextCol, stepCount + 1)) {
                    return true;
                }
                else {
                    //Back track
                    board[nextRow][nextCol] = -1;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[BOARD_SIZE][BOARD_SIZE];

        for(int i = 0; i < BOARD_SIZE; i++) {
            for(int j = 0; j < BOARD_SIZE; j++) {
                board[i][j] = -1;
            }
        }

        board[0][0] = 1;

        if (solve(board, 0, 0, 2)) { // Start with stepCount 0
            System.out.println("The knight's tour has been completed");
            for (int i = 0; i < board.length; i++) {
                for (int j = 0; j < board[0].length; j++) {
                    System.out.print(board[i][j] + "\t");
                }
                System.out.println();
            }
        } else {
            System.out.println("Try again");
        }
    }
}