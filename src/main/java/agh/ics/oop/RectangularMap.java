package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap implements IWorldMap {
    private final int width;
    private final int height;

    private List<Animal> animals = new ArrayList<>();

    RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public int getWidth() {
        return this.width;
    }

    public int getHeight(){
        return this.height;
    }

    public List<Animal> getAnimals() {
        return animals;
    }
    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d start = new Vector2d(0,0);
        Vector2d end = new Vector2d(width, height);

        if(!isOccupied(position) && end.precedes(position) && position.precedes(start)){
            return true;
        }

        return false;
    }

    @Override
    public boolean place(Animal animal) {
        if (!isOccupied(animal.getPosition())) {
            animals.add(animal);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (int i = 0; i < animals.size(); i++) {
            if (animals.get(i).getPosition().equals(position)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        if (isOccupied(position)) {
            for (int i = 0; i < animals.size(); i++) {
                if (animals.get(i).getPosition().equals(position)) {
                    return animals.get(i);
                }
            }
        }
        return null;
    }

    @Override
    public String toString(){
        MapVisualizer visualizer = new MapVisualizer(this);
        Vector2d start = new Vector2d(0,0);
        Vector2d end = new Vector2d(width, height);

        return visualizer.draw(start, end);
    }
}
