package com.example.miniproyecto1.controller;

import com.example.miniproyecto1.util.AudioManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;

import java.io.IOException;

public class MenuController {

    @FXML
    private Label hintLabel;

    @FXML
    private Button startButton;

    private MediaPlayer menuMusic;

    @FXML
    public void initialize(){
        String path = getClass().getResource("/com/example/miniproyecto1/sounds/start-menu.mp3").toExternalForm();
        Media media = new Media(path);
        menuMusic = new MediaPlayer(media);
        menuMusic.setCycleCount(MediaPlayer.INDEFINITE);
        menuMusic.play();

        FadeTransition fadeTransition = new FadeTransition(Duration.seconds(1), hintLabel);

        fadeTransition.setFromValue(0.0);
        fadeTransition.setToValue(1.0);
        fadeTransition.setCycleCount(FadeTransition.INDEFINITE);
        fadeTransition.setAutoReverse(true);
        fadeTransition.play();
    }

    @FXML
    public void onHandleStart(ActionEvent event) throws IOException{
        startButton.setDisable(true);
        menuMusic.stop();

        String pathSound = getClass().getResource("/com/example/miniproyecto1/sounds/undertale-battle-start.mp3").toExternalForm();
        MediaPlayer buttonSound = new MediaPlayer(new Media(pathSound));
        buttonSound.setCycleCount(1);
        buttonSound.play();
        AudioManager.stopCurrent();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/miniproyecto1/game-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) startButton.getScene().getWindow();
        stage.setTitle("Escritura Rápida - Juego");
        stage.setScene(new Scene(root));
        stage.show();
            }
    }
