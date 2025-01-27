package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class GameFinishedController {

    public Button ReturnToMainMenuButton;
    public Label gameWinner;
    public Label player4Label;
    public Label player1SumOfCoins;
    public Label player2SumOfCoins;
    public Label player3SumOFCoins;
    public Label player4SumOfCoins;
    private Stage stage;
    private Scene scene;


    private int resolutionXValue;
    private int resolutionYValue;

    public void initData(int player1total, int player2total, int player3total, int player4total, int numberOfPlayers, int winnerOfGame, int resolutionXValue, int resolutionYValue) {

        // Toggles the visibility for Player4 labels off
        player4Label.setVisible(false);
        player4SumOfCoins.setVisible(false);

        // initializes corresponding variables and sets the text of the labels
        player1SumOfCoins.setText(String.valueOf(player1total));
        player2SumOfCoins.setText(String.valueOf(player2total));
        player3SumOFCoins.setText(String.valueOf(player3total));

        // Toggles visibility of Player4 labels on and initializes and sets the text of the label corresponding to Player 4
        if (numberOfPlayers == 4){
            player4Label.setVisible(true);
            player4SumOfCoins.setVisible(true);
            player4SumOfCoins.setText(String.valueOf(player4total));
        }

        // initializes corresponding variables ands sets the corresponding label to the winner of the game
        this.gameWinner.setText(String.valueOf(winnerOfGame));
        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

    }

    public void onReturnToMainMenuClicked(ActionEvent event) throws IOException {

        // Sets the stage
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // Loads the mein-menu FXML file
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/main-menu.fxml"));
        Parent root = loader.load();

        // Gets the MainMenuController
        MainMenuController mainMenuController = loader.getController();
        // Passes window resolution to the Main Menu window
        mainMenuController.initData(resolutionXValue, resolutionYValue );

        // Creates the Main Menu window
        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();

    }
}
