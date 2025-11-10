Tricky Five-in-a-Row Game
A Java Swing-based implementation of a strategic variant of the classic five-in-a-row (Gomoku) game with unique penalty mechanics.

Overview
Tricky Five-in-a-Row is a two-player strategy game that adds an exciting twist to the traditional five-in-a-row game. Players must carefully plan their moves to avoid triggering penalties while working towards getting five symbols in a row.
The Twist: Creating 3 or 4 consecutive symbols triggers a penalty where random symbols are removed from the board, adding an extra layer of strategy and unpredictability!

Game Rules
Basic Rules

Two players take turns placing their symbols (X and O) on an n×n board
Players can only place symbols on empty cells
The first player to get 5 consecutive symbols (horizontal, vertical, or diagonal) wins
If the board fills up with no winner, the game ends in a draw

Penalty System (The Tricky Part!)

3 in a row: 1 random symbol of the current player is removed from the board
4 in a row: 2 random symbols of the current player are removed from the board
5 or more in a row: Player wins immediately (no penalty)

Important: Removed symbols can be from anywhere on the board, not just from the triggering sequence!
Board Sizes
Players can choose from three board sizes:

6×6 - Quick games
10×10 - Standard games
14×14 - Extended games

Features
✅ Three Board Sizes - Choose from 6×6, 10×10, or 14×14
✅ Unique Penalty System - Strategic gameplay with 3 and 4 in-a-row penalties
✅ Color-Coded Players - Blue for X, Red for O
✅ Turn Indicator - Always know whose turn it is
✅ Automatic Win Detection - All directions (horizontal, vertical, both diagonals)
✅ Draw Detection - Recognizes when the board is full
✅ Responsive UI - Window automatically adjusts to board size
✅ Instant Restart - Automatic new game after each match
✅ User-Friendly - Intuitive click-based gameplay

Requirements

Java Development Kit (JDK): Version 8 or higher
Java Swing: (Included in JDK - no additional installation needed)
Operating System: Windows, macOS, or Linux


Installation
Option 1: Clone from Repository

git clone https://github.com/NeonWest/five-in-a-row-game
cd five-in-a-row-game
```

### Option 2: Download Source Files
1. Download all `.java` files from the repository
2. Create a folder structure:
```
TrickyFiveInARow/
└── src/
└── game/
├── Main.java
├── Player.java
├── FiargBoard.java
├── FiargWindow.java
└── FiargController.java

Troubleshooting
Issue: "javac: command not found"
Solution: Make sure JDK is installed and added to your PATH
Issue: Window is too small/large
Solution: The window auto-adjusts based on board size. Try selecting a different board size.
Issue: Application won't start
Solution: Verify all 5 Java files are in the game package and properly compiled


Contributing
Contributions are welcome! Please follow these steps:

Fork the repository
Create a feature branch (git checkout -b feature/AmazingFeature)
Commit your changes (git commit -m 'Add some AmazingFeature')
Push to the branch (git push origin feature/AmazingFeature)
Open a Pull Request


License
This project is created as an academic assignment for Programming Technology course.

Author
Omar Atakishiyev
Programming Technology Course - 2nd Assignment
Eotvos Lorand University