package agh.ics.oop;

import java.util.Comparator;
import java.util.TreeSet;

public class MapBoundary implements IPositionChangeObserver {
    private final TreeSet<Vector2d> arrX = new TreeSet<>(Comparator.<Vector2d>comparingInt(X -> X.x).thenComparingInt(X -> X.y));
    private final TreeSet<Vector2d> arrY = new TreeSet<>(Comparator.<Vector2d>comparingInt(Y -> Y.y).thenComparingInt(Y -> Y.x));

    public void remove(Vector2d position){
        arrX.remove(position);
        arrY.remove(position);
    }

    public void add(Vector2d position){
        arrX.add(position);
        arrY.add(position);
    }

    @Override
    public void positionChanged(Vector2d oldPosition, Vector2d newPosition){
        remove(oldPosition);
        add(newPosition);
    }

    public Vector2d getUpperBound(){
//        System.out.println('X' + arrX.toString());
//        System.out.println('Y'+ arrY.toString());
        return new Vector2d(arrX.last().x, arrY.last().y);
    }

    public Vector2d getLowerBound(){
        return new Vector2d(arrX.first().x, arrY.first().y);
    }
}

