package agh.ics.oop;


public class World {
    public static void main(String[] args) {

        IWorldMap grass = new GrassField(10);
        MoveDirection[] directions = new OptionsParser().parse(args);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, grass, positions);
        engine.run();
        System.out.println(grass);
    }
}
