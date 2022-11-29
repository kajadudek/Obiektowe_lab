package agh.ics.oop;

import java.util.*;


abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected Map<Vector2d, Grass> grasses = new HashMap<>();
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    private final MapVisualizer mapVisualizer = new MapVisualizer(this);
    protected MapBoundary mapBoundary = new MapBoundary();
    abstract public boolean canMoveTo(Vector2d position);


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = (Animal) objectAt(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
        mapBoundary.positionChanged(oldPosition,newPosition);
    }

    public Map<Vector2d, Animal> getAnimalHashMap() {
        return animals;
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            mapBoundary.add(animal.getPosition());
            return true;
        } else {
            throw new IllegalArgumentException(animal.getPosition().toString() + " is occupied");
        }
    }

    public String toString() {
        Vector2d start = lowerBound();
        Vector2d end = upperBound();
        return mapVisualizer.draw(start, end);
    }

    public Vector2d lowerBound() {
        return mapBoundary.getLowerBound();
    }

    public Vector2d upperBound() {
        return mapBoundary.getUpperBound();
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }

        if (grasses.containsKey(position)){
            return grasses.get(position);
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
}
