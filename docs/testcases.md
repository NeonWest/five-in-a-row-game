Test Cases

BLACK BOX TESTS

Test 1: Starting a New Game

    AS A player

    I WANT TO start a new game

    GIVEN the application is launched

    WHEN I select "10x10" from the board size dialog

    THEN a 10x10 game board appears with "Current Player: X" displayed at the top



Test 2: Placing Symbol on Empty Cell

    AS A player X

    I WANT TO place my symbol on an empty cell

    GIVEN the game board has empty cells

    WHEN I click on an empty cell

    THEN an "X" appears in that cell, the cell becomes disabled, and the turn changes to "Current Player: O"



Test 3: Preventing Invalid Moves on Occupied Cells

    AS A player

    I WANT TO be prevented from placing a symbol on an occupied cell

    GIVEN a cell already contains "X" or "O"

    WHEN I click on that occupied cell

    THEN nothing happens, no symbol changes, and the current player remains the same



Test 4: Winning with Exactly 5 in a Row

    AS A player X

    I WANT TO win the game by getting 5 symbols in a row

    GIVEN I have 4 X's in a horizontal row

    WHEN I place the 5th X to complete the row

    THEN a dialog appears saying "Player X wins!", no penalty is applied, and a new game starts automatically



Test 5: Penalty for Exactly 3 in a Row

    AS A player O

    I WANT TO understand the penalty for making 3 in a row

    GIVEN the board has 5 O symbols placed in various positions

    WHEN I place an O that creates exactly 3 O's in a row (horizontal, vertical, or diagonal)

    THEN one random O symbol disappears from anywhere on the board, that cell becomes clickable again, and the turn switches to player X



Test 6: Penalty for Exactly 4 in a Row

    AS A player X

    I WANT TO understand the severe penalty for making 4 in a row

    GIVEN the board has 6 X symbols placed in various positions

    WHEN I place an X that creates exactly 4 X's in a row (horizontal, vertical, or diagonal)

    THEN two random X symbols disappear from anywhere on the board, those cells become clickable again, and the turn switches to player O



Test 7: Draw Condition with Full Board

    AS A player

    I WANT TO know when the game ends in a draw

    GIVEN the board is almost completely filled with no player having 5 in a row

    WHEN the last empty cell is filled without creating 5 in a row

    THEN a dialog appears saying "It's a draw!" and a new game starts automatically



Test 8: Removed Cell Becomes Playable Again

    AS A player O

    I WANT TO place a symbol on a previously occupied cell that was removed by penalty

    GIVEN cell (2,3) previously had an X that was removed by penalty and is now empty

    WHEN I (player O) click on cell (2,3)

    THEN an "O" appears in cell (2,3), the cell becomes disabled, and the turn switches to player X



WHITE BOX TESTS

Test 9: Valid Symbol Placement on Empty Cell

    AS A developer

    I WANT TO verify symbol placement on empty cells works correctly

    GIVEN a GameBoard with size 10 is initialized and cell (2,3) is empty (board[2][3] == ' ')

    WHEN placeSymbol(2, 3, 'X') is called

    THEN the method returns true and board[2][3] equals 'X'



Test 10: Occupied Cell Rejection

    AS A developer

    I WANT TO verify that placing a symbol on an occupied cell is rejected

    GIVEN cell (2,3) already contains 'X' (board[2][3] == 'X')

    WHEN placeSymbol(2, 3, 'O') is called

    THEN the method returns false and board[2][3] still equals 'X' (unchanged)



Test 11: Horizontal Sequence Detection

    AS A developer

    I WANT TO verify horizontal sequence counting logic

    GIVEN X symbols are placed at positions (0,0), (0,1), (0,2), (0,3)

    WHEN checkSequence(0, 1, 0, 1) is called (horizontal direction: dx=0, dy=1)

    THEN the method returns 4 because it counts backward (0,1)→(0,0) and forward (0,1)→(0,2)→(0,3)



Test 12: Maximum Sequence Across Directions

    AS A developer

    I WANT TO verify that the maximum sequence across all directions is correctly identified

    GIVEN a board where placing X at (3,3) creates 3 horizontal, 2 vertical, and 4 diagonal sequences

    WHEN getMaxSequence(3, 3) is called

    THEN the method returns 4 (the maximum among all four direction checks)



Test 13: Insufficient Symbols for Penalty

    AS A developer

    I WANT TO verify safe handling when penalty exceeds available symbols

    GIVEN the board has only 1 O symbol at position (5,5)

    WHEN removeRandomSymbols('O', 3) is called (attempting to remove 3 symbols)

    THEN the method returns an ArrayList with 1 Point, only the 1 available O is removed via Math.min(3, 1), and no exception is thrown



Test 14: Board Full Detection with Empty Cells

    AS A developer

    I WANT TO verify correct detection of non-full boards

    GIVEN a 6x6 board with 35 cells filled and cell (5,5) empty

    WHEN isFull() is called

    THEN the method returns false because the nested loop finds board[5][5] == ' ' and exits early



Test 15: Win Condition Priority Over Penalty

    AS A developer

    I WANT TO ensure win condition is checked before penalty application in the control flow

    GIVEN player X has 4 X's in a row and currentPlayer is X

    WHEN handleCellClick(row, col) is called to place the 5th X creating exactly 5 in a row

    THEN maxSequence equals 5, the if (maxSequence >= 5) condition is true, endGame() is called, and the penalty code blocks are never executed due to the early return statement 