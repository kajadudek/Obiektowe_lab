package agh.ics.oop;

public class OptionsParser {

    public static MoveDirection[] parse(String[] direction) {
        MoveDirection[] result = new MoveDirection[direction.length];

        for (int i = 0; i < direction.length; i++) {
            switch (direction[i]) {
                case "f", "forward" -> result[i] = MoveDirection.FORWARD;
                case "b", "backward" -> result[i] = MoveDirection.BACKWARD;
                case "r", "right" -> result[i] = MoveDirection.RIGHT;
                case "l", "left" -> result[i] = MoveDirection.LEFT;
                default -> {
                    result[i] = MoveDirection.OTHER;
                }
            }
        }
        return result;
    }
}
