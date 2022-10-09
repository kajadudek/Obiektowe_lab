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
                case b -> System.out.println("Zwierzak idzie do tyłu");
                case r -> System.out.println("Zwierzak skręca w prawo");
                case l -> System.out.println("Zwierzak skręca w lewo");
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
        Direction[] moves = convert(args);
        System.out.println("system wystartował");
        run(moves);
        System.out.print("system zakończył działanie");
    }
}
