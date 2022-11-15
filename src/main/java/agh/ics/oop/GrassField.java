package agh.ics.oop;

import java.util.*;

import static java.lang.Math.*;

public class GrassField extends AbstractWorldMap {

    public final int grassFields;
//    private List<Vector2d> freeFields = new ArrayList<>();

    GrassField(int initialGrassFields) {
        this.grassFields = initialGrassFields;
        randomGrassFields(grassFields);
    }

    public void randomGrassFields(int grassFields) {
        int maxi = (int) (sqrt(this.grassFields * 10));

        for (int i = 0; i < grassFields; i++) {
            int x = (int) (Math.random() * maxi);
            int y = (int) (Math.random() * maxi);

            while (isOccupied(new Vector2d(x, y))) {
                x = (int) (Math.random() * maxi);
                y = (int) (Math.random() * maxi);
            }
            grasses.add(new Grass(new Vector2d(x, y)));
        }

        // Randomowo z pomocą listy wolnych miejsc i shuffle
//        Vector2d end = new Vector2d((int) sqrt(this.grassFields * 10), (int) sqrt(this.grassFields * 10));
//
//        for (int i = 0; i < end.x; i++) {
//            for (int j = 0; j < end.y; j++) {
//                if (!isOccupied(new Vector2d(i, j))) {
//                    freeFields.add(new Vector2d(i, j));
//                }
//            }
//        }
//        Collections.shuffle(freeFields);
//
//        for (int i = 0; i < grassFields; i++) {
//            grasses.add(new Grass(freeFields.get(i)));
//        }
    }


    @Override
    public boolean canMoveTo(Vector2d position) {
        if (position.precedes(new Vector2d(0, 0))) {

            for (Animal animal : animals) {
                if (animal.getPosition().equals(position)) {
                    return false;
                }
            }

            if (isOccupied(position)) {
                for (int i = 0; i < grasses.size(); i++) {
                    if (grasses.get(i).getPosition().equals(position)) {
                        grasses.remove(grasses.get(i));
                        randomGrassFields(1);
                    }
                }
            }return true;
        }return false;
    }
}

