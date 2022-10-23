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
//        Direction[] moves = convert(args);
//        System.out.println("system wystartowal");
//        run(moves);
//        System.out.println("system zakonczyl dzialanie");
//
//        Vector2d position1 = new Vector2d(1,2);
//        System.out.println(position1);
//        Vector2d position2 = new Vector2d(-2,1);
//        System.out.println(position2);
//        System.out.println(position1.add(position2));


        Animal animal = new Animal();
        System.out.println(animal);
        System.out.println(animal.getPosition());

        MoveDirection[] result = OptionsParser.parse(args); //statyczna metoda, da się tez tworząc nowy obiekt
        for (MoveDirection move : result) {
            animal.move(move);
        }
        System.out.println(animal);
    }
}
