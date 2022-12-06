package agh.ics.oop;

public class Grass implements IMapElement{

    private Vector2d position;

    Grass(Vector2d initialPosition) {
        this.position = initialPosition;
    }

    public Vector2d getPosition() {
        return this.position;
    }

    @Override
    public String toLabel() {
        return "grass";
    }

    @Override
    public String toString() {
        return "*";
    }

    @Override
    public String toImage() {
        return "src/main/resources/grass.png";
    }
}
