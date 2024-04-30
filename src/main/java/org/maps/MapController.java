package org.maps;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class MapController extends Application {
    @Override
    public void start(Stage stage) {
        MapPane map = new MapPane("uscoords.txt");
        Scene scene = new Scene(map, 1200, 800);
        scene.setFill(Color.BLACK);
        stage.setTitle("Maps");
        stage.setScene(scene);
        stage.show();

        map.setOnKeyPressed(e -> {
            if(e.getCode() == KeyCode.UP) {
                map.enlarge();
            }
            else if(e.getCode() == KeyCode.DOWN) {
                map.shrink();
            }
        });

        map.requestFocus();
    }

}
