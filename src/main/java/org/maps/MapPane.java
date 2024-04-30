package org.maps;

import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Polygon;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

public class MapPane extends BorderPane {
    private final Group group = new Group();

    MapPane(String cFile) {
        ArrayList<ArrayList<Point2D>> points = getPoints(cFile);

        for (ArrayList<Point2D> point : points) {
            Polygon p = new Polygon();
            for (Point2D point2D : point) {
                p.getPoints().addAll(point2D.getX(),
                        -point2D.getY());
                p.setStroke(Color.WHITE);
                p.setStrokeWidth(1 / 14.0);
            }

            p.setOnMouseClicked(e -> {
                if (e.getButton() == MouseButton.PRIMARY) {
                    p.setFill(Color.LIGHTGRAY);
                } else if (e.getButton() == MouseButton.SECONDARY) {
                    p.setFill(Color.SLATEGRAY);
                } else {
                    p.setFill(null);
                }
            });

            group.getChildren().add(p);
        }
        group.setScaleX(14);
        group.setScaleY(14);
        this.setCenter(group);
    }

    public void enlarge() {
        group.setScaleX(1.1 * group.getScaleX());
        group.setScaleY(1.1 * group.getScaleY());
    }

    public void shrink() {
        group.setScaleX(0.9 * group.getScaleX());
        group.setScaleY(0.9 * group.getScaleY());
    }

    private ArrayList<ArrayList<Point2D>> getPoints(String cFile) {
        ArrayList<ArrayList<Point2D>> points = new ArrayList<>();
        InputStream is = MapPane.class.getResourceAsStream(cFile);

        try {
            assert is != null;
            try (Scanner input = new Scanner(is)){
                while(input.hasNext()) {
                    String s = input.nextLine();
                    if(Character.isAlphabetic(s.charAt(0))) {
                        points.add(new ArrayList<>());
                    }
                    else {
                        Scanner scanAString = new Scanner(s);
                        double y = scanAString.nextDouble();
                        double x = scanAString.nextDouble();
                        points.getLast().add(new Point2D(x, y));
                    }
                }
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return points;
    }
}
