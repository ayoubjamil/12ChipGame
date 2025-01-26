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
    private Parent root;



    private int player1Total;
    private int player2Total;
    private int player3Total;
    private int player4Total;
    private int winenrOfGame;
    private int numberOfPlayers;
    private int resolutionXValue;
    private int resolutionYValue;

    public void initData(int player1total, int player2total, int player3total, int player4total, int numberOfPlayers, int winnerOfGame, int resolutionXValue, int resolutionYValue) {
        player4Label.setVisible(false);
        player4SumOfCoins.setVisible(false);

        this.player1Total = player1total;
        player1SumOfCoins.setText(String.valueOf(player1total));
        this.player2Total = player2total;
        player2SumOfCoins.setText(String.valueOf(player2total));
        this.player3Total = player3total;
        player3SumOFCoins.setText(String.valueOf(player3total));

        if (numberOfPlayers == 4){
            player4Label.setVisible(true);
            player4SumOfCoins.setVisible(true);
            this.player4Total = player4total;
            player4SumOfCoins.setText(String.valueOf(player4total));
        }

        this.winenrOfGame = winnerOfGame;
        this.gameWinner.setText(String.valueOf(winnerOfGame));
        this.numberOfPlayers = numberOfPlayers;
        this.resolutionXValue = resolutionXValue;
        this.resolutionYValue = resolutionYValue;

    }

    public void onReturnToMainMenuClicked(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/main-menu.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


       MainMenuController mainMenuController = loader.getController();
       mainMenuController.initData(resolutionXValue, resolutionYValue );

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();


    }



}
