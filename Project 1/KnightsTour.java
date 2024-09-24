public class KnightsTour {
    private static final int N = 8; // Size of the chessboard

    // Possible moves of a knight
    private static final int[] xMoves = {2, 1, -1, -2, -2, -1, 1, 2};
    private static final int[] yMoves = {1, 2, 2, 1, -1, -2, -2, -1};

    // Function to check if a move is valid
    private static boolean isValidMove(int x, int y, int[][] board) {
        return (x >= 0 && x < N && y >= 0 && y < N && board[x][y] == -1);
    }

    // Recursive function to solve the Knight's Tour problem
    private static boolean solveKTUtil(int x, int y, int moveCount, int[][] board) {
        if (moveCount == N * N) {
            return true;
        }

        for (int i = 0; i < 8; i++) {
            int nextX = x + xMoves[i];
            int nextY = y + yMoves[i];

            if (isValidMove(nextX, nextY, board)) {
                board[nextX][nextY] = moveCount;
                if (solveKTUtil(nextX, nextY, moveCount + 1, board)) {
                    return true;
                } else {
                    // Backtracking
                    board[nextX][nextY] = -1;
                }
            }
        }

        return false;
    }

    // Function to solve the Knight's Tour problem
    public static boolean solveKT() {
        int[][] board = new int[N][N];

        // Initialize the board with -1
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = -1;
            }
        }

        // Start from the first cell
        board[0][0] = 0;

        // Solve the Knight's Tour problem using solveKTUtil()
        if (!solveKTUtil(0, 0, 1, board)) {
            System.out.println("Solution does not exist");
            return false;
        } else {
            printSolution(board);
        }

        return true;
    }

    // Function to print the solution
    private static void printSolution(int[][] board) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print(board[i][j] + "\t");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        solveKT();
    }
}
