package com.example.miniproyecto1.controller;

import com.example.miniproyecto1.controller.adapter.GameKeyHandler;
import com.example.miniproyecto1.model.game.Game;
import com.example.miniproyecto1.model.game.IGame;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;

public class GameController {

    @FXML
    private Label wordLabel;

    @FXML
    private Label timerLabel;

    @FXML
    private Label playerLevel;

    @FXML
    private ProgressBar timeBar;

    @FXML
    private TextField wordTextField;

    private IGame game;
    private Timeline timer;
    private int timeLeft;

    @FXML
    public void initialize() {
        game = new Game();
        game.startGame();
        wordLabel.setText(game.generateWord());
        playerLevel.setText("LV " + game.getCurrentLevel());
        startTimer();

        wordTextField.setOnKeyPressed(new GameKeyHandler() {
            @Override
            public void onKeyPressed(KeyEvent event) {
                if (event.getCode() == KeyCode.ENTER) {
                    onHandleValidate(new ActionEvent());
                }
            }
        });
    }

    public void startTimer() {
        if (timer != null) {
            timer.stop();
        }
        timeLeft = game.getTimeForLevel();
        timeBar.setProgress(1.0);
        timerLabel.setText(timeLeft + " segundos");

        timer = new Timeline(new KeyFrame(Duration.seconds(1), Su -> {
            timeLeft--;
            timeBar.setProgress((double) timeLeft / game.getTimeForLevel());
            timerLabel.setText(timeLeft + " segundos");

            if (timeLeft == 0) {
                timer.stop();
                onTimeUp();
            }
        }));

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }

    public void onTimeUp() {
        timer.stop();
        String text = wordTextField.getText();
        wordTextField.clear();

        if (!text.isEmpty() && game.validateWord(text)) {
            game.nextLevel();
            playerLevel.setText("LV " + game.getCurrentLevel());
            wordLabel.setText("¡Correcto!");

            if (game.isMaxLevel()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(Su -> goToGameOver("¡Completaste todos los niveles!"));
                pause.play();
                return;
            }

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Su -> {
                wordLabel.setText(game.generateWord());
                startTimer();
            });
            pause.play();

        } else if (text.isEmpty()) {
            wordLabel.setText("¡No escribiste nada!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Su -> goToGameOver("¡No escribiste nada!"));
            pause.play();

        } else {
            wordLabel.setText("¡Incorrecto!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Su -> goToGameOver("¡Tiempo agotado!"));
            pause.play();
        }
    }

    private void goToGameOver(String reason) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(
                    "/com/example/miniproyecto1/game-over-view.fxml"));
            Parent root = loader.load();

            GameOverController gameOverController = loader.getController();
            gameOverController.setData(game.getCurrentLevel() - 1, reason, timeLeft);

            Stage stage = (Stage) wordLabel.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException Su) {
            Su.printStackTrace();
        }
    }

    @FXML
    public void onHandleValidate(ActionEvent Su) {
        String text = wordTextField.getText();
        wordTextField.clear();

        if (game.validateWord(text)) {
            game.nextLevel();
            playerLevel.setText("LV " + game.getCurrentLevel());
            wordLabel.setText("¡Correcto!");
            timer.stop();

            if (game.isMaxLevel()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(Sua -> goToGameOver("¡Has Ganado el Juego!"));
                pause.play();
                return;
            }

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Sua -> {
                wordLabel.setText(game.generateWord());
                startTimer();
            });
            pause.play();

        } else {
            wordLabel.setText("¡Incorrecto!");
            timer.stop();

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Sua -> goToGameOver("¡Palabra incorrecta!"));
            pause.play();
        }
    }
}