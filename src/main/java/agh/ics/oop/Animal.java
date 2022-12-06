package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal implements IMapElement{

    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private final List<IPositionChangeObserver> observers;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        this.position = initialPosition;
        this.orientation = MapDirection.NORTH;
        this.observers = new ArrayList<>();
        addObserver((IPositionChangeObserver) map);
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

    @Override
    public String toImage() {
        return switch (orientation) {
            case NORTH -> "src/main/resources/up.png";
            case SOUTH -> "src/main/resources/down.png";
            case WEST -> "src/main/resources/left.png";
            case EAST -> "src/main/resources/right.png";
            case OTHER -> null;
        };
    }

    boolean isAt(Vector2d position) {
        if (position.equals(this.position)) {
            return true;
        }
        return false;
    }

    void addObserver(IPositionChangeObserver observer){
        this.observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.observers.remove(observer);
    }

    public void positionUpdate(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observe: observers){
            observe.positionChanged(oldPosition,newPosition);
        }
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public String toLabel() {
        return this.getPosition().toString();
    }

    public MapDirection getOrientation() {
        return orientation;
    }

    public void move(MoveDirection direction){
        Vector2d newVector = new Vector2d(-1,-1);

        switch (direction) {
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> newVector = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newVector = this.position.subtract(this.orientation.toUnitVector());
        }

        if(map.canMoveTo(newVector)) {
            positionUpdate(this.position, newVector);
            this.position = newVector;
        }
//        System.out.println(map);
    }
}
