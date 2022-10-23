package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    public void toStringTest() {
        assertEquals("(2, 2) Polnoc", new Animal().toString() );
    }

    @Test
    public void isAtTest() {
        Animal zwierze = new Animal();

        assertTrue(zwierze.isAt(new Vector2d(2,2)));
    }

    @Test
    public void getPositionTest() {
        assertEquals(new Vector2d(2,2), new Animal().getPosition());
    }

    @Test
    public void getOrientationTest() {
        assertEquals(MapDirection.NORTH, new Animal().getOrientation());
    }

    @Test
    public void moveTest() {
        Animal zwierze = new Animal();

        assertEquals(MapDirection.NORTH, zwierze.getOrientation());

        zwierze.move(MoveDirection.RIGHT);
        zwierze.move(MoveDirection.BACKWARD);

        assertEquals(new Vector2d(1,2), zwierze.getPosition());
        assertEquals(MapDirection.EAST, zwierze.getOrientation());

        zwierze.move(MoveDirection.LEFT);
        zwierze.move(MoveDirection.FORWARD);

        assertEquals(new Vector2d(1,3), zwierze.getPosition());
        assertEquals(MapDirection.NORTH, zwierze.getOrientation());
    }

    @Test
    public void isOutOfFieldTest_TopAndRightLimit() {
        //given
        Animal zwierze = new Animal();

        //when - top limit
        for (int i=0; i<=3; i++) {
            zwierze.move(MoveDirection.FORWARD);
        }

        //then
        assertEquals(new Vector2d(2,4), zwierze.getPosition());


        //when - right side limit
        zwierze.move(MoveDirection.RIGHT);
        for (int i=0; i<=4; i++) {
            zwierze.move(MoveDirection.FORWARD);
        }

        //then
        assertEquals(new Vector2d(4,4), zwierze.getPosition());
    }

    @Test
    public void isOutOfFieldTest_BottomAndLeftLimit() {
        //given
        Animal zwierze2 = new Animal();

        //when - bottom limit
        for (int i=0;i<=5;i++) {
            zwierze2.move(MoveDirection.BACKWARD);
        }

        //then
        assertEquals(new Vector2d(2,-4),zwierze2.getPosition());

        //when - left side limit
        zwierze2.move(MoveDirection.LEFT);
        for (int i=0; i<=6; i++) {
            zwierze2.move(MoveDirection.FORWARD);
        }

        //then
        assertEquals(new Vector2d(-4,-4),zwierze2.getPosition());
    }
}