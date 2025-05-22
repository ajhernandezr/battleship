public class Grid {
    private final char[][] board;
    private final int size;

    public Grid(int size) {
        this.size = size;
        this.board = new char[size][size];
        initializeBoard();
    }

    private void initializeBoard() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '~'; // '~' represents water
            }
        }
    }

    public void displayBoard() {
        for (char[] row : board) {
            for (char cell : row) {
                System.out.print(cell + " ");
            }
            System.out.println();
        }
    }

    public boolean placeShip(int x, int y, int length, boolean horizontal) {
        if (horizontal) {
            if (x + length > size) return false;
            for (int i = 0; i < length; i++) {
                if (board[y][x + i] != '~') return false;
            }
            for (int i = 0; i < length; i++) {
                board[y][x + i] = 'S'; // 'S' represents a ship
            }
        } else {
            if (y + length > size) return false;
            for (int i = 0; i < length; i++) {
                if (board[y + i][x] != '~') return false;
            }
            for (int i = 0; i < length; i++) {
                board[y + i][x] = 'S';
            }
        }
        return true;
    }

    public boolean takeShot(int x, int y) {
        if (board[y][x] == 'S') {
            board[y][x] = 'X'; // 'X' represents a hit
            return true;
        } else if (board[y][x] == '~') {
            board[y][x] = 'O'; // 'O' represents a miss
            return false;
        }
        return false; // Already shot here
    }
}