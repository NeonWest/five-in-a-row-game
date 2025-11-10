package fiarg;

public class Player {
    private char symbol;
    private String name;

    /**
     * Constructor
     * @param symbol The player's symbol ('X' or 'O')
     */
    public Player(char symbol) {
        this.symbol = symbol;
        this.name = "Player " + symbol;
    }

    /**
     * Gets the player's symbol
     * @return 'X' or 'O'
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Gets the player's name
     * @return Player name
     */
    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}