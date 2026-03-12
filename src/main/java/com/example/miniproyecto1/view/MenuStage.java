package com.example.miniproyecto1.view;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuStage extends Stage {
    public MenuStage() throws IOException {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/com/example/miniproyecto1/menu-view.fxml")
        );
        Parent root = loader.load();
        Scene scene = new Scene(root);
        setResizable(false);
        getIcons().add(
                new Image(String.valueOf(getClass().getResource("/com/example/miniproyecto1/icons/undericon.png")))
        );
        setScene(scene);
        setTitle("Escritura Rápida");
        show();
    }
}
