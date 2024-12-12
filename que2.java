class Solution {
    public int snakesAndLadders(int[][] arr) {
        int n = arr.length;
        int[] board = new int[n * n];
        int index = 0;

        // Flatten the 2D board to 1D, with proper row traversal direction
        for (int i = n - 1; i >= 0; i--) {
            if ((n - i) % 2 == 1) { // odd row (from left to right)
                for (int j = 0; j < n; j++) {
                    if (arr[i][j] != -1) {
                        board[index] = arr[i][j] - 1; // adjust for 0-indexed board
                    } else {
                        board[index] = -1; // no ladder/snake
                    }
                    index++;
                }
            } else { // even row (from right to left)
                for (int j = n - 1; j >= 0; j--) {
                    if (arr[i][j] != -1) {
                        board[index] = arr[i][j] - 1;
                    } else {
                        board[index] = -1;
                    }
                    index++;
                }
            }
        }

        // BFS to find the shortest path to the last cell
        int steps = 0;
        Queue<Integer> queue = new LinkedList<>();
        HashSet<Integer> visited = new HashSet<>();
        queue.add(0); // Start from the first cell
        visited.add(0);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                int currIdx = queue.poll();
                if (currIdx >= n * n - 1) {
                    return steps; // Reached the final cell
                }

                // Explore the next 6 possible moves (dice rolls)
                for (int dice = 1; dice <= 6; dice++) {
                    int newIdx = currIdx + dice;
                    if (newIdx >= n * n) break; // Stop if index exceeds the board

                    if (!visited.contains(newIdx)) {
                        visited.add(newIdx);

                        // If there's a ladder or snake, move to that destination
                        if (board[newIdx] != -1) {
                            queue.add(board[newIdx]);
                        } else {
                            queue.add(newIdx); // Regular move
                        }
                    }
                }
            }
            steps++;
        }

        return -1; // If no solution found
    }
}
