package fiarg;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collections;

public class FiargBoard {
    private char[][] board;
    private int size;

    /**
     * Constructor
     * @param size Board size (6, 10, or 14)
     */
    public FiargBoard(int size) {
        this.size = size;
        this.board = new char[size][size];
        initialize();
    }

    /**
     * Initializes the board with empty spaces
     */
    public void initialize() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Places a symbol at the given position
     * @param row Row index
     * @param col Column index
     * @param symbol Symbol to place ('X' or 'O')
     * @return true if placement was successful, false if cell occupied
     */
    public boolean placeSymbol(int row, int col, char symbol) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return false;
        }
        if (board[row][col] != ' ') {
            return false;
        }
        board[row][col] = symbol;
        return true;
    }

    /**
     * Removes a symbol at the given position
     * @param row Row index
     * @param col Column index
     */
    public void removeSymbol(int row, int col) {
        if (row >= 0 && row < size && col >= 0 && col < size) {
            board[row][col] = ' ';
        }
    }

    /**
     * Gets the symbol at a given position
     * @param row Row index
     * @param col Column index
     * @return Symbol at position or ' ' if empty/invalid
     */
    public char getSymbol(int row, int col) {
        if (row < 0 || row >= size || col < 0 || col >= size) {
            return ' ';
        }
        return board[row][col];
    }

    /**
     * Checks for consecutive symbols in a given direction
     * @param row Starting row
     * @param col Starting column
     * @param dx Row direction increment
     * @param dy Column direction increment
     * @return Number of consecutive symbols
     */
    public int checkSequence(int row, int col, int dx, int dy) {
        char symbol = board[row][col];
        if (symbol == ' ') {
            return 0;
        }

        int count = 1;

        int r = row + dx;
        int c = col + dy;
        while (r >= 0 && r < size && c >= 0 && c < size && board[r][c] == symbol) {
            count++;
            r += dx;
            c += dy;
        }

        r = row - dx;
        c = col - dy;
        while (r >= 0 && r < size && c >= 0 && c < size && board[r][c] == symbol) {
            count++;
            r -= dx;
            c -= dy;
        }

        return count;
    }

    /**
     * Gets the maximum sequence length at a position in all directions
     * @param row Row index
     * @param col Column index
     * @return Maximum sequence length
     */
    public int getMaxSequence(int row, int col) {
        int[] dx = {0, 1, 1, 1};
        int[] dy = {1, 0, 1, -1};

        int maxSequence = 0;
        for (int i = 0; i < 4; i++) {
            int seq = checkSequence(row, col, dx[i], dy[i]);
            maxSequence = Math.max(maxSequence, seq);
        }
        return maxSequence;
    }

    /**
     * Collects all positions containing the given symbol
     * @param symbol Symbol to search for
     * @return ArrayList of Points containing the symbol
     */
    public ArrayList<Point> getSymbolPositions(char symbol) {
        ArrayList<Point> positions = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == symbol) {
                    positions.add(new Point(i, j));
                }
            }
        }
        return positions;
    }

    /**
     * Removes random symbols of the given player (penalty mechanism)
     * @param symbol Symbol to remove
     * @param count Number of symbols to remove
     * @return ArrayList of Points that were removed
     */
    public ArrayList<Point> removeRandomSymbols(char symbol, int count) {
        ArrayList<Point> positions = getSymbolPositions(symbol);
        int toRemove = Math.min(count, positions.size());

        Collections.shuffle(positions);
        ArrayList<Point> removed = new ArrayList<>();

        for (int i = 0; i < toRemove; i++) {
            Point p = positions.get(i);
            removeSymbol(p.x, p.y);
            removed.add(p);
        }

        return removed;
    }

    /**
     * Checks if the board is completely full
     * @return true if board is full, false otherwise
     */
    public boolean isFull() {
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Gets the board size
     * @return Board size
     */
    public int getSize() {
        return size;
    }
}
