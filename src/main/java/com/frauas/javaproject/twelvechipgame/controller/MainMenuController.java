package com.frauas.javaproject.twelvechipgame.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    public Button gameRulesButton;
    @FXML
    private Button threePlayerButton;
    @FXML
    private Button fourPlayerButton;
    @FXML
    private Button settingsButton;
    @FXML
    private Button exitButton;
    @FXML
    private TextArea debugField;

    int resolutionXValue = 1280;
    int resolutionYValue = 720;


    private Stage stage;
    private Scene scene;
    private Parent root;



    // Example: a small "game mode" logic holder
    private final GameModeController gameMode = new GameModeController();

    public static boolean isThreePlayer = false;

    /**
     * No-arg constructor.
     * JavaFX automatically uses this to create the controller
     * when loading the FXML (because of fx:controller).
     */
    public MainMenuController() {
        // Usually empty.
    }

    @FXML
    public void onThreePlayerButtonClicked(ActionEvent event) throws IOException {

//        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        DifficultyController difficultyController = loader.getController();
        difficultyController.initData(3, resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();



    }

    @FXML
    public void onFourPlayerButtonClicked(ActionEvent event) throws IOException {

//        gameMode.setThreePlayerMode(true);
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();


        DifficultyController difficultyController = loader.getController();
        difficultyController.initData(4, resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();



    }

    public void switchToSettings(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/settings.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();

        SettingsController settingsController = loader.getController();
        settingsController.initData(resolutionXValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }

    public void switchToGameRules(ActionEvent event) throws IOException {
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/frauas/javaproject/twelvechipgame/game-rules.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("/com/frauas/javaproject/twelvechipgame/choose-difficulty.fxml"));
        Parent root = loader.load();

        GameRulesController gameRulesController = loader.getController();
        gameRulesController.initData(resolutionXValue, resolutionYValue);

        scene = new Scene(root, resolutionXValue, resolutionYValue);
        stage.setScene(scene);
        stage.show();
    }


    public void exitProgram(ActionEvent event) throws IOException {
        System.exit(0);
    }

    public void initData(int resolutionXValue, int resolutionYValue) {
            this.resolutionXValue = resolutionXValue;
            this.resolutionYValue = resolutionYValue;
    }


}
