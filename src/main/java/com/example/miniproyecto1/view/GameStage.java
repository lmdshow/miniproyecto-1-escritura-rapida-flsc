package com.example.miniproyecto1.view;

import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.fxml.FXMLLoader;
import java.io.IOException;

public class GameStage extends Stage {
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
