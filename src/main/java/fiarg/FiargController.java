package fiarg;

import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * Controls game flow and coordinates between model and view
 */
public class FiargController {
    private FiargBoard gameBoard;
    private FiargWindow gameWindow;
    private Player playerX;
    private Player playerO;
    private Player currentPlayer;

    /**
     * Constructor - initializes players and starts new game
     */
    public FiargController() {
        playerX = new Player('X');
        playerO = new Player('O');
        startNewGame();
    }

    /**
     * Starts a new game by selecting board size and initializing components
     * Event: Game start/restart → showBoardSizeDialog() → initializeGame()
     */
    public void startNewGame() {
        if (gameWindow == null) {
            gameWindow = new FiargWindow(10);
        }

        int boardSize = gameWindow.showBoardSizeDialog();

        if (gameWindow != null) {
            gameWindow.dispose();
        }

        gameWindow = new FiargWindow(boardSize);
        gameBoard = new FiargBoard(boardSize);
        currentPlayer = playerX;

        initializeGame();
        gameWindow.setVisible(true);
    }

    /**
     * Initializes game state and sets up event listeners for all cells
     */
    private void initializeGame() {
        gameWindow.updateTurnLabel(currentPlayer.getName(), currentPlayer.getSymbol());

        for (int i = 0; i < gameBoard.getSize(); i++) {
            for (int j = 0; j < gameBoard.getSize(); j++) {
                final int row = i;
                final int col = j;

                gameWindow.addCellListener(row, col, new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        handleCellClick(row, col);
                    }
                });
            }
        }
    }

    /**
     * Handles cell click event - main game logic
     * Event flow: Cell click → Place symbol → Check win/penalties → Switch player
     * @param row Row index of clicked cell
     * @param col Column index of clicked cell
     */
    private void handleCellClick(int row, int col) {
        if (!gameBoard.placeSymbol(row, col, currentPlayer.getSymbol())) {
            return;
        }

        gameWindow.updateCell(row, col, currentPlayer.getSymbol(), false);

        int maxSequence = gameBoard.getMaxSequence(row, col);

        if (maxSequence >= 5) {
            endGame(currentPlayer.getName() + " wins!");
            return;
        }

        if (maxSequence == 3) {
            applyPenalty(1);
        } else if (maxSequence == 4) {
            applyPenalty(2);
        }

        if (gameBoard.isFull()) {
            endGame("It's a draw!");
            return;
        }

        switchPlayer();
    }

    /**
     * Applies penalty by removing random symbols from current player
     * @param count Number of symbols to remove
     */
    private void applyPenalty(int count) {
        ArrayList<Point> removed = gameBoard.removeRandomSymbols(currentPlayer.getSymbol(), count);

        for (Point p : removed) {
            gameWindow.updateCell(p.x, p.y, ' ', true);
        }
    }

    /**
     * Switches to the other player and updates the turn indicator
     */
    private void switchPlayer() {
        currentPlayer = (currentPlayer == playerX) ? playerO : playerX;
        gameWindow.updateTurnLabel(currentPlayer.getName(), currentPlayer.getSymbol());
    }

    /**
     * Ends the game, shows result message, and starts a new game
     * @param message Message to display (winner or draw)
     */
    private void endGame(String message) {
        gameWindow.showGameOverDialog(message);
        startNewGame();
    }
}