package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class GameFinishedController {

    public Button ReturnToMainMenuButton;
    private Stage stage;
    private Scene scene;
    private Parent root;



    private int player1Total;
    private int player2Total;
    private int player3Total;
    private int player4Total;
    private int numberOfPlayers;

    public void initData(int player1total, int player2total, int player3total, int player4total, int numberOfPlayers) {
        this.player1Total = player1total;
        this.player2Total = player2total;
        this.player3Total = player3total;
        this.player4Total = player4total;
        this.numberOfPlayers = numberOfPlayers;

    }

    public void onReturnToMainMenuClicked(ActionEvent event) throws IOException {

        //        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/main-menu.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


//        MainMenuController mainMenuController = loader.getController();
//        mainMenuController.initData(numberOfPlayers, "hard");

        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();


    }

}
