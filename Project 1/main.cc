#include<iostream>

const int BOARD_SIZE = 8;

const int moveRow[] = {-2, -2, -1, -1, 1, 1, 2, 2};
const int moveCol[] = {1, -1, 2, -2, 2, -2, -1, 1};

bool isValidMove(int row, int col, int board[BOARD_SIZE][BOARD_SIZE]) {
    return (row >= 0 && row < BOARD_SIZE && col >= 0 && col < BOARD_SIZE && board[row][col] == -1);
}

bool solve(int board[BOARD_SIZE][BOARD_SIZE], int row, int col, int stepCount) {
    if(stepCount == BOARD_SIZE * BOARD_SIZE + 1) {
        return true;
    }

    for(int i = 0; i < BOARD_SIZE; i++) {
        int nextRow = row + moveRow[i];
        int nextCol = col + moveCol[i];

        if(isValidMove(nextRow, nextCol, board)) {
            board[nextRow][nextCol] = stepCount;
            if(solve(board, nextRow, nextCol, stepCount + 1)) {
                return true;
            }
            else {
                board[nextRow][nextCol] = -1;
            }
        }
    }
    return false;
}
int main(void) {
    int board[BOARD_SIZE][BOARD_SIZE];
    for(int i = 0; i < BOARD_SIZE; i++) {
        for(int j = 0; j < BOARD_SIZE; j++) {
            board[i][j] = -1;
        }
    }

    board[0][0] = 1;

    if (solve(board, 0, 0, 2)) {
           std::cout << "The knight's tour has been completed" << std::endl;
            for (int i = 0; i < BOARD_SIZE; i++) {
                for (int j = 0; j < BOARD_SIZE; j++) {
                   std::cout << board[i][j] << "\t";
                }
                std::cout << std::endl;
            }
        } else {
            std::cout << "Try again" << std::endl;
        }
    return 0;
}
/*
instead of using BOARD_SIZE in for loop

Calculate the number of rows
int numRows = sizeof(board) / sizeof(board[0]);

Calculate the number of columns
int numCols = sizeof(board[0]) / sizeof(board[0][0]);

*/