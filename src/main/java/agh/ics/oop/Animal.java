package agh.ics.oop;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;

    Animal() {
        orientation = MapDirection.NORTH;
    }

    Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        position = initialPosition;
        orientation = MapDirection.NORTH;
    }

    Animal(IWorldMap map) {
        this.map = map;
        orientation = MapDirection.NORTH;
    }

    @Override
    public String toString() {
        return switch (orientation) {
            case NORTH -> "N";
            case SOUTH -> "S";
            case WEST -> "W";
            case EAST -> "E";
            case OTHER -> null;
        };
    }


    boolean isAt(Vector2d position) {
        if (position.equals(this.position)) {
            return true;
        }
        return false;
    }

    public Vector2d getPosition() {
        return position;
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction){
        Vector2d newVector = new Vector2d(0,0);
        switch (direction) {
            case RIGHT -> {
                this.orientation = this.orientation.next();
                return;
            }
            case LEFT -> {
                this.orientation = this.orientation.previous();
                return;
            }
            case FORWARD -> newVector = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newVector = this.position.subtract(this.orientation.toUnitVector());
            case OTHER -> newVector = this.position;
        }
        if(map.canMoveTo(newVector)){
            this.position = newVector;
        }
    }
}
