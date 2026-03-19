package com.example.miniproyecto1;

import com.example.miniproyecto1.view.MenuStage;
import javafx.application.Application;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Entry point of the Escritura Rapida application.
 * Launches the JavaFX application and initializes the main menu stage.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class Main extends Application {

    /**
     * Starts the JavaFX application by creating and displaying the main menu stage.
     *
     * @param primaryStage the primary stage provided by the JavaFX runtime (not used directly).
     * @throws IOException if the menu FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        new MenuStage();
    }
}