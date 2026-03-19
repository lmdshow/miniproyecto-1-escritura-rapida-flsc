package com.example.miniproyecto1.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

/**
 * Represents the game window of Escritura Rapida.
 * Loads and displays the game view from the FXML file.
 * Extends {@link Stage} to function as a standalone JavaFX window.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class GameStage extends Stage {

    /**
     * Constructs the GameStage by loading the game FXML layout
     * and displaying the stage.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public GameStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1/game-view.fxml")
        );
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        setScene(scene);
        show();
    }
}