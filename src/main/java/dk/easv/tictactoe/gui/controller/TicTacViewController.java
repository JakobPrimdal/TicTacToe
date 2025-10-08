
package dk.easv.tictactoe.gui.controller;

// Java imports
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

// Project imports
import dk.easv.tictactoe.bll.GameBoard;
import dk.easv.tictactoe.bll.IGameBoard;

/**
 *
 * @author Group 2 --> Jonas, Jakob, Frederik, Ayuub
 */
public class TicTacViewController implements Initializable
{
    @FXML
    private Label lblPlayer;

    @FXML
    private Button btnNewGame;

    @FXML
    private GridPane gridPane;

    private IGameBoard game;

    /**
     * Event handler for the grid buttons
     *
     * @param event
     */
    @FXML
    private void handleButtonAction(ActionEvent event)
    {
        try
        {
            Integer row = GridPane.getRowIndex((Node) event.getSource());
            Integer col = GridPane.getColumnIndex((Node) event.getSource());
            int r = (row == null) ? 0 : row;
            int c = (col == null) ? 0 : col;

            if (game.play(c, r))
            {
                if (game.isGameOver())
                {
                    int winner = game.getWinner();
                    displayWinner(winner);
                    System.out.println("Highlight kaldes med coords: " + Arrays.deepToString(game.getWinningCoords()));
                    highlightWinningLine(game.getWinningCoords());
                }
                else
                {
                    int player = game.getNextPlayer();
                    Button btn = (Button) event.getSource();
                    String xOrO = player == 0 ? "X" : "O";
                    if(player == 0){
                        game.setABoardValue(r,c,1);
                    } else if (player == 1) {
                        game.setABoardValue(r,c,2);
                    }
                    btn.setText(xOrO);
                    setPlayer();

                    if (game.isGameOver()){
                        int winner = game.getWinner();
                        displayWinner(winner);
                        highlightWinningLine(game.getWinningCoords());
                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Event handler for starting a new game
     *
     * @param event
     */
    @FXML
    private void handleNewGame(ActionEvent event)
    {
        game.newGame();
        setPlayer();
        clearBoard();
    }

    /**
     * Initializes a new controller
     *
     * @param url
     * The location used to resolve relative paths for the root object, or
     * {@code null} if the location is not known.
     *
     * @param rb
     * The resources used to localize the root object, or {@code null} if
     * the root object was not localized.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb)
    {
        game = new GameBoard();
        setPlayer();
    }




    /**
     * Set the next player
     */
    private void setPlayer()
    {
        if (game.getPlayer() == 1){
            lblPlayer.setText("Player X' turn.");
            lblPlayer.setStyle("-fx-text-fill: black;");
        } if (game.getPlayer() == 0){
            lblPlayer.setText("Player O' turn.");
            lblPlayer.setStyle("-fx-text-fill: red;");
        }

    }


    /**
     * Finds a winner or a draw and displays a message based
     * @param winner
     */
    private void displayWinner(int winner)
    {
        String message = "";
        switch (winner)
        {
            case 1:
                message = "Player X wins!";
                break;
            case 2:
                message = "Player O wins!";
                break;
            case -1:
                message = "No one wins, it's a draw.";
                break;
        }
        lblPlayer.setText(message);
    }

    /**
     * Clears the game board in the GUI
     */
    private void clearBoard()
    {
        for(Node n : gridPane.getChildren())
        {
            Button btn = (Button) n;
            btn.setText("");
            btn.setStyle("");
        }
    }

    private void highlightWinningLine(int[][] winningCoords) {
        for (int[] coord : winningCoords) {
            int row = coord[0];
            int col = coord[1];

            String id = "btn" + row + col;
            Button button = (Button) gridPane.lookup("#" + id);

            if (button != null) {
                button.setStyle("-fx-background-color: lightgreen; -fx-text-fill: black;");
            }
        }
    }


}
