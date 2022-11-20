package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class Animal {
    private MapDirection orientation;
    private Vector2d position;
    private IWorldMap map;
    private final List<IPositionChangeObserver> Observers;

    public Animal(IWorldMap map, Vector2d initialPosition) {
        this.map = map;
        position = initialPosition;
        orientation = MapDirection.NORTH;
        this.Observers = new ArrayList<>();
    }

    void addObserver(IPositionChangeObserver observer){
        this.Observers.add(observer);
    }

    void removeObserver(IPositionChangeObserver observer){
        this.Observers.remove(observer);
    }

    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        for(IPositionChangeObserver observe: Observers){
            observe.positionChanged(oldPosition,newPosition);
        }
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
            case RIGHT -> this.orientation = this.orientation.next();
            case LEFT -> this.orientation = this.orientation.previous();
            case FORWARD -> newVector = this.position.add(this.orientation.toUnitVector());
            case BACKWARD -> newVector = this.position.subtract(this.orientation.toUnitVector());
            case OTHER -> newVector = this.position;
        }

        if(map.canMoveTo(newVector)) {
            System.out.println(this.position + "--->" + newVector);
            this.position = newVector;
            positionChanged(this.position, newVector);
        }
        System.out.println(map);
    }
}
