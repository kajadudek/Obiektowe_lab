package agh.ics.oop;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap {

    public final int grassFields;
    public GrassField(int initialGrassFields) {
        this.grassFields = initialGrassFields;
        randomGrassFields(grassFields);
    }

    public void randomGrassFields(int grassFields) {
        int maxi = (int) (sqrt(this.grassFields * 10));

        for (int i = 0; i < grassFields; i++) {
            int x = (int) (Math.random() * maxi)+1;
            int y = (int) (Math.random() * maxi)+1;

            while (isOccupied(new Vector2d(x, y))) {
                x = (int) (Math.random() * maxi)+1;
                y = (int) (Math.random() * maxi)+1;
            }

            Vector2d vector = new Vector2d(x,y);
            grasses.put(vector, new Grass(vector));
            this.mapBoundary.add(vector);
        }
    }

    @Override
    public boolean canMoveTo(Vector2d position) {
        if(animals.containsKey(position)){
            return false;
        }
        if (position.precedes(new Vector2d(0, 0))) {
            if (isOccupied(position)) {
                if(grasses.containsKey(position)){
                    grasses.remove(position);
                    mapBoundary.remove(position);
                    randomGrassFields(1);
                }
            }return true;
        }return false;
    }
}

