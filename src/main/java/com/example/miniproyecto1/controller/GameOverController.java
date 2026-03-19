package com.example.miniproyecto1.controller;

import com.example.miniproyecto1.util.AudioManager;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.IOException;

/**
 * Controller for the game over screen of Escritura Rapida.
 * Manages the game over animation, displays final stats,
 * plays background music, and handles the play again action.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class GameOverController {

    /** Label that displays the "GAME OVER" title with a fade animation. */
    @FXML
    private Label gameOverLabel;

    /** Label that displays the number of levels completed. */
    @FXML
    private Label levelsLabel;

    /** Label that displays the reason the game ended. */
    @FXML
    private Label reasonLabel;

    /** Label that displays the remaining time when the game ended. */
    @FXML
    private Label timeLabel;

    /** Button that allows the player to restart the game. */
    @FXML
    private Button playAgainButton;

    /** Number of levels completed by the player. */
    private int levelsCompleted;

    /** Message describing why the game ended. */
    private String reason;

    /** Remaining time in seconds when the game ended. */
    private int timeLeft;

    /**
     * Initializes the game over controller.
     * Starts the game over music and hides all UI elements
     * to prepare them for the entrance animation.
     */
    @FXML
    public void initialize() {
        String pathFirst = getClass().getResource(
                "/com/example/miniproyecto1/sounds/gameover.mp3").toExternalForm();
        String pathLoop = getClass().getResource(
                "/com/example/miniproyecto1/sounds/undertale.mp3").toExternalForm();

        AudioManager.play(pathFirst, false);
        MediaPlayer first = AudioManager.getCurrent();
        first.setOnEndOfMedia(() -> {
            AudioManager.play(pathLoop, true);
        });

        gameOverLabel.setOpacity(0);
        levelsLabel.setOpacity(0);
        reasonLabel.setOpacity(0);
        playAgainButton.setOpacity(0);
        timeLabel.setOpacity(0);
        levelsLabel.setText("");
        reasonLabel.setText("");
        timeLabel.setText("");
    }

    /**
     * Receives the final game data and starts the game over animation.
     *
     * @param levelsCompleted the number of levels the player completed.
     * @param reason the message describing why the game ended.
     * @param timeLeft the remaining time in seconds when the game ended.
     */
    public void setData(int levelsCompleted, String reason, int timeLeft) {
        this.levelsCompleted = levelsCompleted;
        this.reason = reason;
        this.timeLeft = timeLeft;
        startAnimation();
    }

    /**
     * Starts the game over animation sequence.
     * Fades in the "GAME OVER" label, then writes each stat letter by letter.
     */
    private void startAnimation() {
        gameOverLabel.setText("GAME OVER");
        FadeTransition fadeGameOver = new FadeTransition(Duration.seconds(2), gameOverLabel);
        fadeGameOver.setFromValue(0.0);
        fadeGameOver.setToValue(1.0);

        fadeGameOver.setOnFinished(event -> writeLetterByLetter(
                reason, reasonLabel, () ->
                        writeLetterByLetter(
                                "Niveles completados: " + levelsCompleted, levelsLabel, () ->
                                        writeLetterByLetter(
                                                "Tiempo restante: " + timeLeft + "s", timeLabel, () ->
                                                        showButton()
                                        )
                        )
        ));

        fadeGameOver.play();
    }

    /**
     * Animates text appearing letter by letter on the given label.
     * Executes the provided callback when the animation finishes.
     *
     * @param text the text to animate.
     * @param label the label where the text will appear.
     * @param onFinished the callback to execute when the animation completes.
     */
    private void writeLetterByLetter(String text, Label label, Runnable onFinished) {
        label.setOpacity(1);
        label.setText("");

        Timeline timeline = new Timeline();
        for (int i = 1; i <= text.length(); i++) {
            final String current = text.substring(0, i);
            KeyFrame kf = new KeyFrame(Duration.millis(i * 150), event -> {
                label.setText(current);
            });
            timeline.getKeyFrames().add(kf);
        }

        timeline.setOnFinished(Su -> {
            PauseTransition pause = new PauseTransition(Duration.seconds(0.5));
            pause.setOnFinished(ev -> onFinished.run());
            pause.play();
        });

        timeline.play();
    }

    /**
     * Reveals the play again button with a fade in animation.
     */
    private void showButton() {
        FadeTransition fadeButton = new FadeTransition(Duration.seconds(1), playAgainButton);
        fadeButton.setFromValue(0.0);
        fadeButton.setToValue(1.0);
        fadeButton.play();
    }

    /**
     * Handles the play again button action.
     * Stops the current music and navigates back to the game screen.
     *
     * @param event the action event triggered by the button click.
     * @throws IOException if the game FXML file cannot be loaded.
     */
    @FXML
    public void onHandlePlayAgain(ActionEvent event) throws IOException {
        playAgainButton.setDisable(true);
        AudioManager.stopCurrent();

        FXMLLoader loader = new FXMLLoader(getClass().getResource(
                "/com/example/miniproyecto1/game-view.fxml"));
        Parent root = loader.load();
        Stage stage = (Stage) playAgainButton.getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
    }
}