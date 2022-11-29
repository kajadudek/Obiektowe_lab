package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import javafx.animation.KeyFrame;

import static java.lang.System.out;
import static javafx.scene.paint.Color.color;

public class App extends Application {
    AbstractWorldMap map;
    Timeline timeline = new Timeline();


    @Override
    public void init() {

        Parameters parameters = getParameters();
        String[] args = parameters.getRaw().toArray(new String[0]);
        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            IEngine engine = new SimulationEngine(directions, map, positions);
            engine.run();
        }catch (IllegalArgumentException exception) {
            out.println(exception);
        }
    }

    public void start(Stage primaryStage) {

        timeline.getKeyFrames().add(new KeyFrame(Duration.millis(500)));
        timeline.setCycleCount(Timeline.INDEFINITE);

        Label label = new Label("y\\x");
        GridPane grid = new GridPane();
        grid.setGridLinesVisible(true);

        GridPane.setHalignment(label, HPos.CENTER);

        grid.add(label, 0, 0);
        grid.getRowConstraints().add(new RowConstraints(40));
        grid.getColumnConstraints().add(new ColumnConstraints(40));

        int lowerX = map.lowerBound().x;
        int lowerY = map.lowerBound().y;
        int upperX = map.upperBound().x;
        int upperY = map.upperBound().y;

        int index = 1;

        //Columns label
        for(int i = lowerX; i <= upperX; ++i){
            label = new Label(Integer.toString(i));
            grid.add(label, index, 0);
            grid.getColumnConstraints().add(new ColumnConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index += 1;
        }

        //Rows label
        index = 1;
        for(int i = upperY; i >= lowerY; --i){
            label = new Label(Integer.toString(i));
            grid.add(label, 0, index);
            grid.getRowConstraints().add(new RowConstraints(40));
            GridPane.setHalignment(label, HPos.CENTER);
            index += 1;
        }

        //Place object on map
        for(int i = lowerX; i <= upperX; ++i){
            for(int j = upperY; j >= lowerY; --j){
                Vector2d pos = new Vector2d(i, j);
                if (map.isOccupied(pos)){
                    var object = map.objectAt(pos);
                    label = new Label(object.toString());

                    if (object instanceof Grass){
                        label.setTextFill(color(0, 0.7, 0));
                        label.setFont(new Font(25));
                    }

                    grid.add(label, i - lowerX + 1, upperY - j + 1);
                    GridPane.setHalignment(label, HPos.CENTER);
                }
            }
        }

        int height = grid.getRowConstraints().size() * 40;
        int width = grid.getColumnConstraints().size() * 40;
        Scene scene = new Scene(grid, width, height);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
