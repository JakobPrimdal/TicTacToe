
package dk.easv.tictactoe.bll;

import dk.easv.tictactoe.gui.controller.TicTacViewController;

/**
 *
 * @author Group 2 --> Jonas, Jakob, Frederik, Ayuub
 */
public class GameBoard implements IGameBoard
{
    int[][] ABoard = new int[3][3];

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
     * @return true if the game is over, else it will retun false.
     */
    public boolean isGameOver()
    {
        //TODO Implement this method
        return false;
    }

    /**
     * Gets the id of the winner, -1 if its a draw.
     *
     * @return int id of winner, or -1 if draw.
     */
    public int getWinner()
    {
        //TODO Implement this method
        return -1;
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
        System.out.print(ABoard[0][0]+", ");
        System.out.print(ABoard[0][1]+", ");
        System.out.println(ABoard[0][2]+", ");

        System.out.print(ABoard[1][0]+", ");
        System.out.print(ABoard[1][1]+", ");
        System.out.println(ABoard[1][2]+", ");

        System.out.print(ABoard[2][0]+", ");
        System.out.print(ABoard[2][1]+", ");
        System.out.println(ABoard[2][2]+", ");
        System.out.println("");

    }

}