package agh.ics.oop;

import java.util.*;

import static java.lang.Math.max;
import static java.lang.Math.min;

abstract public class AbstractWorldMap implements IWorldMap, IPositionChangeObserver {
    protected List<Grass> grasses = new ArrayList<>();
    protected Map<Vector2d, Animal> animals = new HashMap<>();
    private MapVisualizer mapVisualizer = new MapVisualizer(this);

    abstract public boolean canMoveTo(Vector2d position);


    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition) {
        Animal animal = (Animal) objectAt(oldPosition);
        animals.remove(oldPosition);
        animals.put(newPosition, animal);
    }

    public String toString() {
        Vector2d start = lowerBound();
        Vector2d end = upperBound();
        return mapVisualizer.draw(start, end);
    }

    public Map<Vector2d, Animal> getAnimalHashMap() {
        return animals;
    }

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            animals.put(animal.getPosition(), animal);
            return true;
        } else {
            return false;
        }
    }

    public Vector2d upperBound() {
        int maxi_x = 0;
        int maxi_y = 0;

        for (int i = 0; i < grasses.size(); i++) {
            maxi_x = max(maxi_x, grasses.get(i).getPosition().x);
            maxi_y = max(maxi_y, grasses.get(i).getPosition().y);
        }

        for (Vector2d key : animals.keySet()) {
            maxi_x = max(maxi_x, key.x);
            maxi_y = max(maxi_y, key.y);
        }
        return new Vector2d(maxi_x, maxi_y);
    }

    public Vector2d lowerBound() {
        int mini_x = Integer.MAX_VALUE;
        int mini_y = Integer.MAX_VALUE;

        for (int i = 0; i < grasses.size(); i++) {
            mini_x = min(mini_x, grasses.get(i).getPosition().x);
            mini_y = min(mini_y, grasses.get(i).getPosition().y);
        }

        for (Vector2d key : animals.keySet()) {
            mini_x = min(mini_x, key.x);
            mini_y = min(mini_y, key.y);
        }
        return new Vector2d(mini_x, mini_y);
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (animals.containsKey(position)) {
            return animals.get(position);
        }

        for (int i = 0; i < grasses.size(); i++) {
            if (grasses.get(i).getPosition().equals(position)) {
                return grasses.get(i);
            }
        }
        return null;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }
}
