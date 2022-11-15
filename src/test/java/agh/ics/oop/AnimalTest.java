package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    IWorldMap map;
    Animal zwierze;

    @Test
    public void isAtTest() {
        //given
        map = new RectangularMap(5, 10);
        zwierze = new Animal(map, new Vector2d(2, 2));

        //when
        map.place(zwierze);
        assertTrue(zwierze.isAt(new Vector2d(2, 2)));

        //then
        zwierze.move(MoveDirection.FORWARD);
        assertTrue(zwierze.isAt(new Vector2d(2, 3)));
    }

    @Test
    public void moveTestForwardAndRight() {
        //given
        map = new RectangularMap(5, 10);
        zwierze = new Animal(map, new Vector2d(2, 2));

        //when
        map.place(zwierze);
        zwierze.move(MoveDirection.FORWARD);
        zwierze.move(MoveDirection.RIGHT);
        zwierze.move(MoveDirection.FORWARD);

        //then
        assertEquals(zwierze.getPosition(), new Vector2d(3, 3));
        assertEquals(zwierze.getOrientation(), MapDirection.EAST);
    }

    @Test
    public void moveTestOutOfMap() {
        //given
        map = new RectangularMap(5, 10);
        zwierze = new Animal(map, new Vector2d(3, 3));

        //when
        map.place(zwierze);
        zwierze.move(MoveDirection.RIGHT);
        zwierze.move(MoveDirection.FORWARD);
        zwierze.move(MoveDirection.FORWARD);
        zwierze.move(MoveDirection.FORWARD);
        zwierze.move(MoveDirection.LEFT);

        //then
        assertEquals(zwierze.getPosition(), new Vector2d(5, 3));
        assertEquals(zwierze.getOrientation(), MapDirection.NORTH);
    }

    @Test
    public void moveTestBackwardAndLeft() {
        //given
        map = new RectangularMap(5, 10);
        zwierze = new Animal(map, new Vector2d(5, 3));

        //when
        map.place(zwierze);
        zwierze.move(MoveDirection.BACKWARD);
        zwierze.move(MoveDirection.BACKWARD);
        zwierze.move(MoveDirection.LEFT);

        //then
        assertEquals(zwierze.getPosition(), new Vector2d(5, 1));
        assertEquals(zwierze.getOrientation(), MapDirection.WEST);
    }
}