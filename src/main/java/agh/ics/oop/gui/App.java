package agh.ics.oop.gui;

import agh.ics.oop.*;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.HPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import static java.lang.Math.abs;
import static java.lang.System.out;
import static javafx.scene.paint.Color.color;

public class App extends Application {
    AbstractWorldMap map;
    private int moveDelay;
    private GridPane grid = new GridPane();
    private IEngine engine;
    private Scene scene;
    private int lowerX;
    private int lowerY;
    private int upperX;
    private int upperY;


    @Override
    public void init() {

        moveDelay = 500;
        Parameters parameters = getParameters();
        String[] args = parameters.getRaw().toArray(new String[0]);
        try {
            MoveDirection[] directions = OptionsParser.parse(args);
            map = new GrassField(10);
            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
            this.engine = new SimulationEngine(directions, map, positions, this, moveDelay);

        }catch (IllegalArgumentException exception) {
            out.println(exception);
        }
    }

    public void start(Stage primaryStage) {
        this.draw();

        Button startButton = new Button("click");

        startButton.setOnAction(event -> {
            Thread thread = new Thread((Runnable) this.engine);
            thread.start();
        });

        VBox vbox = new VBox(startButton, this.grid);
        scene = new Scene(vbox, (abs(upperX-lowerX) + 2) * 40, (abs(upperY-lowerY) + 2) * 40 + 25);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public void draw() {
        Label label = new Label("y\\x");
        grid.setGridLinesVisible(true);

        GridPane.setHalignment(label, HPos.CENTER);

        grid.add(label, 0, 0);
        grid.getRowConstraints().add(new RowConstraints(40));
        grid.getColumnConstraints().add(new ColumnConstraints(40));

        lowerX = map.lowerBound().x;
        lowerY = map.lowerBound().y;
        upperX = map.upperBound().x;
        upperY = map.upperBound().y;

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
                    Object object = this.map.objectAt(pos);
                    GuiElementBox img = new GuiElementBox((IMapElement) object);
                    VBox objectOnMap = img.getVbox();

                    grid.add(objectOnMap, i - lowerX + 1, upperY - j + 1);
                    GridPane.setHalignment(objectOnMap, HPos.CENTER);
                }
            }
        }
    }

    public void animation() {
        Platform.runLater( () -> {
            grid.getChildren().clear();
            grid.getRowConstraints().clear();
            grid.getColumnConstraints().clear();
            grid.setGridLinesVisible(false);

            this.draw();
        });
    }
}
