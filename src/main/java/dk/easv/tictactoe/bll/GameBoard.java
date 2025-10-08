
package dk.easv.tictactoe.bll;

import dk.easv.tictactoe.gui.controller.TicTacViewController;

/**
 *
 * @author Group 2 --> Jonas, Jakob, Frederik, Ayuub
 */
public class GameBoard implements IGameBoard
{
    // Instance Variables
    private int winnerID = 0;
    private int[][] ABoard = new int[3][3];

    private int[][] winningCoords; // Gemmer koordinaterne for vinderlinjen





    public int getABoardValue(int col, int row){
        return ABoard[col][row];
    }

    public void setABoardValue(int col, int row, int value){
        ABoard[col][row] = value;
    }



    /**
     * Returns 0 for player 0, 1 for player 1.
     *
     * @return int Id of the next player.
     */
    private int player = 0;

    public int getNextPlayer()
    {
        if(player == 0) {
            player = 1;

        } else if (player == 1){
            player = 0;
        }

        return player;
    }

    /*
      Returns the value of player
     */
    public int getPlayer(){
        return player;
    }

    /**
     * Attempts to let the current player play at the given coordinates. It the
     * attempt is succesfull the current player has ended his turn and it is the
     * next players turn.
     *
     * @param col column to place a marker in.
     * @param row row to place a marker in.
     * @return true if the move is accepted, otherwise false. If gameOver == true
     * this method will always return false.
     */
    public boolean play(int col, int row)
    {
        if(ABoard[row][col] == 0){
            return true;
        }
        else {return false;}
    }

    /**
     * Tells us if the game has ended either by draw or by meeting the winning
     * condition.
     *
     * @return true if the game is over, else it will return false.
     */
    public boolean isGameOver()
    {
        if(checkForWin(ABoard)){
            System.out.println(getWinner() + " has won!");
            return true;
        } else if (checkForDraw(ABoard)){
            System.out.println(getWinner() + " is a draw");
            return true;
        }
        else {return false;}

    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        return winnerID;
    }

    /**
     * Resets the game to a new game state.
     */
    public void newGame()
    {
        // This nested for-loop is for assigning all index's of ABoard to the value 0
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                ABoard[row][col] = 0;
            }
        }
    }

    private boolean checkForWin(int[][] ABoard){
        ABoard = this.ABoard;

        // Checks for wins horizontally
        for (int i = 0; i < 3; i++){
            if (ABoard[i][0] == ABoard[i][1] && ABoard[i][1] == ABoard[i][2] && ABoard[i][0]!= 0) {
                winnerID = ABoard[i][0];
                winningCoords = new int[][] { {i, 0}, {i, 1}, {i, 2} };
                return true;
            }
        }

        // Checks for wins vertically
        for (int i = 0; i < 3; i++){
            if (ABoard[0][i] == ABoard[1][i] && ABoard[1][i] == ABoard[2][i] && ABoard[0][i] != 0){
                winnerID = ABoard[0][i];
                winningCoords = new int[][] { {0, i}, {1, i}, {2, i} };
                return true;
            }
        }

        // Checks for wins diagonally
        if (ABoard[0][0] == ABoard[1][1] && ABoard[1][1] == ABoard[2][2] && ABoard[0][0] != 0){
            winnerID = ABoard[0][0];
            winningCoords = new int[][] { {0, 0}, {1, 1}, {2, 2} };
            return true;}

        if (ABoard[0][2] == ABoard[1][1] && ABoard[1][1] == ABoard[2][0] && ABoard[0][2] != 0){
            winnerID = ABoard[0][2];
            winningCoords = new int[][] { {0, 2}, {1, 1}, {2, 0} };
            return true;}

        return false;
    }

    private boolean checkForDraw(int[][] ABoard){
        ABoard = this.ABoard;

        // Checks for draw
        for (int row = 0; row < 3; row++){
            for (int col = 0; col < 3; col++){
                if(ABoard[row][col] == 0) {
                    return false; // Not a draw
                }
            }
        }

        winnerID = -1;
        return true;
    }

    public int[][] getWinningCoords() {
        return winningCoords;
    }

}