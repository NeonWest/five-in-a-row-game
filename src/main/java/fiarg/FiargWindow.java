package fiarg;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Manages the game's graphical user interface
 */
public class FiargWindow extends JFrame {
    private JButton[][] boardButtons;
    private JLabel turnLabel;
    private JPanel boardPanel;
    private JPanel topPanel;
    private int boardSize;

    /**
     * Constructor
     * @param boardSize Size of the game board
     */
    public FiargWindow(int boardSize) {
        this.boardSize = boardSize;

        setTitle("Tricky Five-in-a-Row");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        createTopPanel();
        createBoardPanel();
        adjustWindowSize();
    }

    /**
     * Creates the top panel with turn indicator label
     */
    private void createTopPanel() {
        topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());

        turnLabel = new JLabel("Current Player: X");
        turnLabel.setFont(new Font("Arial", Font.BOLD, 20));
        turnLabel.setForeground(Color.BLUE);

        topPanel.add(turnLabel);
        add(topPanel, BorderLayout.NORTH);
    }

    /**
     * Creates the game board panel with button grid
     */
    private void createBoardPanel() {
        boardPanel = new JPanel();
        boardPanel.setLayout(new GridLayout(boardSize, boardSize, 2, 2));
        boardPanel.setBackground(Color.GRAY);

        boardButtons = new JButton[boardSize][boardSize];

        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                JButton btn = new JButton("");
                btn.setFont(new Font("Arial", Font.BOLD, 24));
                btn.setFocusPainted(false);
                btn.setBackground(Color.WHITE);

                boardButtons[i][j] = btn;
                boardPanel.add(btn);
            }
        }

        add(boardPanel, BorderLayout.CENTER);
    }

    /**
     * Adds an action listener to a specific cell button
     * Event: Cell button click → GameController.handleCellClick()
     * @param row Row index
     * @param col Column index
     * @param listener ActionListener to handle clicks
     */
    public void addCellListener(int row, int col, ActionListener listener) {
        boardButtons[row][col].addActionListener(listener);
    }

    /**
     * Updates a cell's display with a symbol
     * @param row Row index
     * @param col Column index
     * @param symbol Symbol to display
     * @param enabled Whether the button should be enabled
     */
    public void updateCell(int row, int col, char symbol, boolean enabled) {
        boardButtons[row][col].setText(symbol == ' ' ? "" : String.valueOf(symbol));
        boardButtons[row][col].setEnabled(enabled);

        if (symbol == 'X') {
            boardButtons[row][col].setForeground(Color.BLUE);
        } else if (symbol == 'O') {
            boardButtons[row][col].setForeground(Color.RED);
        }
    }

    /**
     * Updates the turn indicator label
     * @param playerName Name of current player
     * @param symbol Current player's symbol
     */
    public void updateTurnLabel(String playerName, char symbol) {
        turnLabel.setText("Current Player: " + symbol);
        turnLabel.setForeground(symbol == 'X' ? Color.BLUE : Color.RED);
    }

    /**
     * Shows a game over message dialog
     * @param message Message to display
     */
    public void showGameOverDialog(String message) {
        JOptionPane.showMessageDialog(this, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
    }

    /**
     * Shows board size selection dialog
     * @return Selected board size (6, 10, or 14)
     */
    public int showBoardSizeDialog() {
        String[] options = {"6x6", "10x10", "14x14"};

        String choice = (String) JOptionPane.showInputDialog(
                this,
                "Select board size:",
                "New Game",
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]
        );

        if (choice == null) {
            System.exit(0);
        }

        switch (choice) {
            case "6x6": return 6;
            case "10x10": return 10;
            case "14x14": return 14;
            default: return 10;
        }
    }

    /**
     * Resets all cells on the board to empty
     */
    public void resetBoard() {
        for (int i = 0; i < boardSize; i++) {
            for (int j = 0; j < boardSize; j++) {
                updateCell(i, j, ' ', true);
            }
        }
    }

    /**
     * Adjusts window size based on board size
     */
    private void adjustWindowSize() {
        int cellSize = 60;
        int width = boardSize * cellSize + 50;
        int height = boardSize * cellSize + 100;

        setSize(width, height);
        setLocationRelativeTo(null);
    }
}

