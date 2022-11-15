package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

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

    public List<Animal> getAnimals(){
        return this.animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d start = new Vector2d(0,0);
        Vector2d end = new Vector2d(width, height);

        return !isOccupied(position) && end.precedes(position) && position.precedes(start);
    }
}
