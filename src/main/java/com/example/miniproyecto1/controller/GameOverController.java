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

public class GameOverController {

    @FXML
    private Label gameOverLabel;
    @FXML
    private Label levelsLabel;
    @FXML
    private Label reasonLabel;
    @FXML
    private Button playAgainButton;
    @FXML
    private Label timeLabel;

    private int levelsCompleted;
    private String reason;
    private int timeLeft;

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

    public void setData(int levelsCompleted, String reason, int timeLeft) {
        this.timeLeft = timeLeft;
        this.levelsCompleted = levelsCompleted;
        this.reason = reason;
        startAnimation();
    }

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

    private void showButton() {
        FadeTransition fadeButton = new FadeTransition(Duration.seconds(1), playAgainButton);
        fadeButton.setFromValue(0.0);
        fadeButton.setToValue(1.0);
        fadeButton.play();
    }

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