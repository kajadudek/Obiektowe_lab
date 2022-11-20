package agh.ics.oop;

import java.util.List;
import java.util.Map;

public class RectangularMap extends AbstractWorldMap {
    private final int width;
    private final int height;

    RectangularMap(int width, int height) {
        this.width = width;
        this.height = height;
    }
//    @Override
//    public String toString(){
//        Vector2d start = new Vector2d(0,0);
//        Vector2d end = new Vector2d(width +1,height+1);
//        MapVisualizer mapVisualizer = new MapVisualizer(this);
//        return mapVisualizer.draw(start, end);
//    }
    public int getWidth() {
        return this.width;
    }
    public int getHeight(){
        return this.height;
    }

    public Map getAnimals(){
        return this.animals;
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        Vector2d start = new Vector2d(0,0);
        Vector2d end = new Vector2d(width, height);

        return !isOccupied(position) && end.precedes(position) && position.precedes(start);
    }
}
