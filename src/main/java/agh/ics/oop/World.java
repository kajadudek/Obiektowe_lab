package agh.ics.oop;

import agh.ics.oop.gui.App;
import javafx.application.Application;

public class World {
    public static void main(String[] args) {

//        try {
//            AbstractWorldMap grass = new GrassField(10);
//            MoveDirection[] directions = new OptionsParser().parse(args);
//            Vector2d[] positions = {new Vector2d(2, 2), new Vector2d(3, 4)};
//            IEngine engine = new SimulationEngine(directions, grass, positions);
//            engine.run();
//            System.out.println(grass);
//        }catch (IllegalArgumentException exception){
//            System.out.println(exception);
//        }

        Application.launch(App.class, args);
    }
}
