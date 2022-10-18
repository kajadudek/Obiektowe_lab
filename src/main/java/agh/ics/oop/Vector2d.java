package agh.ics.oop;

import java.util.Objects;

public class Vector2d {
    public final int x;
    public final int y;

    public Vector2d (int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public String toString() {
        return "(" + this.x + ", " + this.y + ")";
    }

    boolean precedes(Vector2d other) {
        return this.x >= other.x && this.y >= other.y;
    }

    boolean follows(Vector2d other) {
        return other.x >= this.x && other.y >= this.y;
    }

    Vector2d add(Vector2d other) {
        return new Vector2d(this.x + other.x, this.y + other.y);
    }

    Vector2d subtract(Vector2d other) {
        return new Vector2d(this.x - other.x, this.y - other.y);
    }

    Vector2d upperRight(Vector2d other) {
        int tempX = this.x;
        int tempY = this.y;

        if (other.x > this.x) {
            tempX = other.x;
        }

        if (other.y > this.y) {
            tempY = other.y;
        }

        return new Vector2d(tempX, tempY);
    }

    Vector2d lowerLeft(Vector2d other) {
        int tempX = this.x;
        int tempY = this.y;

        if (other.x < this.x) {
            tempX = other.x;
        }

        if (other.y < this.y) {
            tempY = other.y;
        }

        return new Vector2d(tempX, tempY);
    }

    Vector2d opposite() {
        return new Vector2d(-this.x, -this.y);
    }

    @Override
    public boolean equals(Object other) {
        if ( this == other ) {
            return true;
        }
        if (!(other instanceof Vector2d)) {
            return false;
        }
        Vector2d that = (Vector2d) other;
        return that.x == this.x && that.y == this.y;
    }

    @Override
    public int hashCode(){
        return Objects.hash(this.x,this.y);
    }

//    equals - metoda uzywana to hashCode, Objects - klasa statyczna, w ktorej jest metoda hashCode(s) (przyjmuje parametry w postaci obiektu)

}
