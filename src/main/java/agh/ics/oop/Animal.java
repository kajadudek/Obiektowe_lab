package agh.ics.oop;

public class Animal {
    private MapDirection orientation = MapDirection.NORTH;
    private Vector2d position = new Vector2d(2,2);

    @Override
    public String toString() {
        return "(" + position.x + ", " + position.y + ") " + orientation;
//        String.valueOf(position.x) + String.valueOf(position.y) + String.valueOf(orientation);
    }

    boolean isAt(Vector2d position) {
        if (position.equals(this.position)) {
            return true;
        }
        return false;
    }

    public Vector2d getPosition(){
        return position;
    }

    public void move(MoveDirection direction){
        switch (direction) {
            case LEFT -> orientation = orientation.previous();
            case RIGHT -> orientation = orientation.next();
            case FORWARD -> {
                switch (orientation) {
                    case NORTH -> position.y += 1; //FINAL??
                    case SOUTH -> position.y -= 1;
                    case EAST -> position.x += 1;
                    case WEST -> position.x -= 1;
                }
            }
            case BACKWARD -> {
                switch (orientation){
                    case NORTH -> position.y -= 1;
                    case SOUTH -> position.y += 1;
                    case EAST -> position.x -= 1;
                    case WEST -> position.x += 1;
                }
            }
        }

        if (position.x >= 5) {
            position.x = 4;
        }
        if (position.y >= 5) {
            position.y = 4;
        }
        if (position.x <= -5) {
            position.x = -4;
        }
        if (position.y <= -5) {
            position.y = -4;
        }
    }
}
