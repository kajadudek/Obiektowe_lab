package agh.ics.oop;


public class World {
    static void run(Direction[] moves) {
        for (int i = 0; i < moves.length; i++) {
            if (i != moves.length - 1) {
                System.out.print(moves[i] + ", ");
            } else {
                System.out.println(moves[i]);
            }
        }

        System.out.println("Start");
        for (Direction move : moves) {
            switch (move) {
                case f -> System.out.println("Zwierzak idzie do przodu");
                case b -> System.out.println("Zwierzak idzie do tylu");
                case r -> System.out.println("Zwierzak skreca w prawo");
                case l -> System.out.println("Zwierzak skreca w lewo");
                default -> {
                }
            }
        }
        System.out.println("Stop");
    }

    static Direction[] convert(String[] args) {

        Direction[] result = new Direction[args.length];
        for (int i = 0; i < args.length; i++) {
            result[i] = switch (args[i]) {
                case "f" -> Direction.f;
                case "b" -> Direction.b;
                case "r" -> Direction.r;
                case "l" -> Direction.l;
                default -> Direction.other;
            } ;
        }
        return result;
    }

    public static void main(String[] args) {
        MoveDirection[] directions = new OptionsParser().parse(args);
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions);
        engine.run();
        System.out.println(map);
    }
}
