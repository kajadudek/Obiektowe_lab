package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import static java.lang.Math.max;
import static java.lang.Math.min;

abstract public class AbstractWorldMap implements IWorldMap {
    protected List<Animal> animals = new ArrayList<>();
    protected List<Grass> grasses = new ArrayList<>();

    abstract public boolean canMoveTo(Vector2d position);

    @Override
    public boolean place(Animal animal) {
        if (this.canMoveTo(animal.getPosition())) {
            animals.add(animal);
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

        for (int i = 0; i < animals.size(); i++) {
            maxi_x = max(maxi_x, animals.get(i).getPosition().x);
            maxi_y = max(maxi_y, animals.get(i).getPosition().y);
        }
        return new Vector2d(maxi_x, maxi_y);
    }

    public Vector2d lowerBound() {
        int mini_x = animals.get(0).getPosition().x;
        int mini_y = animals.get(0).getPosition().y;

        for (int i = 0; i < grasses.size(); i++) {
            mini_x = min(mini_x, grasses.get(i).getPosition().x);
            mini_y = min(mini_y, grasses.get(i).getPosition().y);
        }

        for (int i = 0; i < animals.size(); i++) {
            mini_x = min(mini_x, animals.get(i).getPosition().x);
            mini_y = min(mini_y, animals.get(i).getPosition().y);
        }
        return new Vector2d(mini_x, mini_y);
    }

    @Override
    public String toString(){
        Vector2d start = lowerBound();
        Vector2d end = upperBound();
        MapVisualizer mapVisualizer = new MapVisualizer(this);
        return mapVisualizer.draw(start, end);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return objectAt(position) != null;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getPosition().equals(position)) {
                return animals.get(i);
            }
        }
        for (int i = 0; i < grasses.size(); i++){
            if (grasses.get(i).getPosition().equals(position)){
                return grasses.get(i);
            }
        }
        return null;
    }
}
