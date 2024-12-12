class Solution {
    // Count the number of mines ('M') in the 8 neighboring cells
    private int calculateMines(char[][] board, int[][] dirs, int row, int col) {
        int count = 0; // Initialize mine count
        int n = board.length; // Number of rows
        int m = board[0].length; // Number of columns

        // Check all 8 possible directions for mines
        for (int[] dir : dirs) {
            int newRow = dir[0] + row;
            int newCol = dir[1] + col;
            // Ensure the new position is within the board and has a mine ('M')
            if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < m && board[newRow][newCol] == 'M') {
                count++; // Increment mine count
            }
        }
        return count; // Return total count of mines
    }

    private void dfs(char[][] board, int[][] dirs, int row, int col) {
        int mines = calculateMines(board, dirs, row, col); // Count mines around the cell

        // If no mines around, explore all neighboring cells
        if (mines == 0) {
            board[row][col] = 'B'; // Mark as blank

            for (int[] dir : dirs) {
                int newRow = dir[0] + row;
                int newCol = dir[1] + col;
                // Check bounds and if cell is not already blank ('B')
                if (newRow >= 0 && newRow < board.length && newCol >= 0 && newCol < board[0].length
                        && board[newRow][newCol] != 'B') {
                    dfs(board, dirs, newRow, newCol); // Continue DFS
                }
            }
        } else {
            // Update the current cell with the number of surrounding mines
            board[row][col] = (char) (mines + '0');
        }
    }

    // Update the board after a click
    public char[][] updateBoard(char[][] board, int[] click) {
        // All 8 possible moves (up, down, left, right, and 4 diagonals)
        int[][] dirs = { { 0, -1 }, { -1, -1 }, { -1, 0 }, { -1, 1 }, { 0, 1 }, { 1, 1 }, { 1, 0 }, { 1, -1 } };

        // If clicked cell is a mine ('M'), change it to 'X'
        if (board[click[0]][click[1]] == 'M') {
            board[click[0]][click[1]] = 'X';
            return board;
        }

        // Start DFS from clicked cell
        dfs(board, dirs, click[0], click[1]);

        return board; // Return the updated board
    }
}
