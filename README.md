# GUI-Swing-make-a-square-board-game
Simple GUI-based game using Java Swing, where there are two players and the player that clicks the board to create a square first wins.

The rules of the game are as follows:
- There are two players, Player 1 and Player 2.
- The players take turns making a move until either one of them wins the game, or else the
board is filled up without either player winning.
- On each turn, a player clicks on one of the available buttons. That button should change its
background colour to indicate that it has been selected, and should also change its text to
indicate which player has chosen it (either “1” or “2”)
- Once a button has been clicked by one of the players, it is unavailable to be clicked any
more.
- A player wins if they select all buttons in a 2x2 square.
Note that both Player 1 and Player 2 work on the same, shared game board: in a real game-playing
session, the players would need to pass the computer mouse back and forth between each other.
For example, the board might resemble the following after a few turns of play. Note that the red and
blue squares are not clickable; the only squares that respond to a click are the grey squares that
have not yet been claimed.

![image](https://user-images.githubusercontent.com/73764849/147889561-85fa0495-64f1-41c4-af3f-f1bac7cba3ff.png)

The “Reset” button at the bottom can be used at any time to restore the grid to its original state,
with no squares claimed.
