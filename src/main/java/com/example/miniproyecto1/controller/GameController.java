package com.example.miniproyecto1.controller;

import com.example.miniproyecto1.controller.adapter.GameKeyHandler;
import com.example.miniproyecto1.model.game.Game;
import com.example.miniproyecto1.model.game.IGame;
import com.example.miniproyecto1.util.AudioManager;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

/**
 * Controller for the main game screen of Escritura Rapida.
 * Manages the game timer, word display, player input validation,
 * level progression, and navigation to the game over screen.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class GameController {

    /** Label that displays the current word the player must type. */
    @FXML private Label wordLabel;

    /** Label that displays the remaining time in seconds. */
    @FXML private Label timerLabel;

    /** Label that displays the current player level. */
    @FXML private Label playerLevel;

    /** Progress bar that visually represents the remaining time. */
    @FXML private ProgressBar timeBar;

    /** Text field where the player types their answer. */
    @FXML private TextField wordTextField;

    /** The game model instance managing game logic. */
    private IGame game;

    /** Timeline used as the countdown timer for each level. */
    private Timeline timer;

    /** Remaining time in seconds for the current level. */
    private int timeLeft;

    /** MediaPlayer preloaded with the validation sound effect. */
    private MediaPlayer validateSound;

    /**
     * Initializes the game controller.
     * Sets up the game model, preloads the validation sound,
     * starts background music, initializes the timer,
     * and configures the keyboard event handler.
     */
    @FXML
    public void initialize() {
        game = new Game();
        game.startGame();
        wordLabel.setText(game.generateWord());
        playerLevel.setText("LV " + game.getCurrentLevel());

        validateSound = new MediaPlayer(new Media(getClass().getResource(
                "/com/example/miniproyecto1/sounds/undertale-sound-effect.mp3").toExternalForm()));

        String path = getClass().getResource(
                "/com/example/miniproyecto1/sounds/Home.mp3").toExternalForm();
        AudioManager.play(path, true);

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

    /**
     * Stops, rewinds, and plays the preloaded validation sound effect.
     */
    private void playValidateSound() {
        validateSound.stop();
        validateSound.seek(validateSound.getStartTime());
        validateSound.play();
    }

    /**
     * Starts or restarts the countdown timer for the current level.
     * Updates the progress bar and timer label every second.
     * Triggers {@link #onTimeUp()} when time reaches zero.
     */
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

    /**
     * Handles the event triggered when the countdown timer reaches zero.
     * Validates the typed word if any, and navigates to game over or next level accordingly.
     */
    public void onTimeUp() {
        timer.stop();
        String text = wordTextField.getText();
        wordTextField.clear();

        if (!text.isEmpty() && game.validateWord(text)) {
            game.nextLevel();
            playerLevel.setText("LV " + game.getCurrentLevel());
            wordLabel.setText("Correcto!");

            if (game.isMaxLevel()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(Su -> goToGameOver("Completaste todos los niveles!"));
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
            wordLabel.setText("No escribiste nada!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Su -> goToGameOver("No escribiste nada!"));
            pause.play();

        } else {
            wordLabel.setText("Incorrecto!");
            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Su -> goToGameOver("Tiempo agotado!"));
            pause.play();
        }
    }

    /**
     * Navigates to the game over screen, passing the final stats to the controller.
     * Stops all audio before switching scenes.
     *
     * @param reason the message describing why the game ended.
     */
    private void goToGameOver(String reason) {
        AudioManager.stopCurrent();
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

    /**
     * Handles the validate button action and Enter key press.
     * Plays the validation sound, checks the typed word,
     * and advances the level or navigates to game over accordingly.
     *
     * @param Su the action event triggered by the button or Enter key.
     */
    @FXML
    public void onHandleValidate(ActionEvent Su) {
        playValidateSound();
        String text = wordTextField.getText();
        wordTextField.clear();

        if (game.validateWord(text)) {
            game.nextLevel();
            playerLevel.setText("LV " + game.getCurrentLevel());
            wordLabel.setText("Correcto!");
            timer.stop();

            if (game.isMaxLevel()) {
                PauseTransition pause = new PauseTransition(Duration.seconds(1));
                pause.setOnFinished(Sua -> goToGameOver("Has Ganado el Juego!"));
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
            wordLabel.setText("Incorrecto!");
            timer.stop();

            PauseTransition pause = new PauseTransition(Duration.seconds(1));
            pause.setOnFinished(Sua -> goToGameOver("Palabra incorrecta!"));
            pause.play();
        }
    }
}