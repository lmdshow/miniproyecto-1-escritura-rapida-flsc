package com.example.miniproyecto1.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * Represents the main menu window of Escritura Rapida.
 * Loads and displays the menu view from the FXML file and sets the application icon.
 * Extends {@link Stage} to function as a standalone JavaFX window.
 *
 * @author Frank Leonardo Silva Castillo
 * @version 1.0
 */
public class MenuStage extends Stage {

    /**
     * Constructs the MenuStage by loading the menu FXML layout,
     * setting the window icon, title, and displaying the stage.
     *
     * @throws IOException if the FXML file cannot be loaded.
     */
    public MenuStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1/menu-view.fxml")
        );
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource(
                        "/com/example/miniproyecto1/icons/undericon.png")))
        );
        setScene(scene);
        setTitle("Escritura Rapida");
        show();
    }
}