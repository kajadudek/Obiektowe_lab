package agh.ics.oop;

public interface IMapElement {

    @Override
    String toString();
    String toImage();
    Vector2d getPosition();
    String toLabel();
}
