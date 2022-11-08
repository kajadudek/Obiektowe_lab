package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {

    //given
    IWorldMap map = new RectangularMap(10,5);
    Vector2d vector1 = new Vector2d(3,4);
    Vector2d vector2 = new Vector2d(0,5);
    Vector2d vector3 = new Vector2d(9, 2);
    Animal kot = new Animal(map, vector1);
    Animal pies = new Animal(map, vector2);
    Animal mysz = new Animal(map, vector3);

    @Test
    public void canMoveToTest() {
        //when
        map.place(kot);

        //then
        assertTrue(map.canMoveTo(new Vector2d(0,2)));
        assertTrue(map.canMoveTo(new Vector2d(9,4)));
        assertFalse(map.canMoveTo(new Vector2d(11,4)));
        assertFalse(map.canMoveTo(new Vector2d(0,6)));
        assertFalse(map.canMoveTo(vector1));
    }

    @Test
    public void placeTest() {
        //when
        map.place(kot);
        map.place(pies);
        map.place(mysz);

        //then
        assertEquals(map.objectAt(vector1),kot);
        assertEquals(map.objectAt(vector2),pies);
        assertEquals(map.objectAt(vector3),mysz);
        assertFalse(map.isOccupied(new Vector2d(4,5)));
    }

    @Test
    public void isOccupiedTest() {
        //when
        map.place(kot);
        map.place(pies);
        map.place(mysz);

        //then
        assertTrue(map.isOccupied(vector1));
        assertTrue(map.isOccupied(vector2));
        assertTrue(map.isOccupied(vector3));
        assertFalse(map.isOccupied(new Vector2d(0,4)));
    }

    @Test
    public void objectAtTest() {
        //when
        map.place(kot);
        map.place(pies);
        map.place(mysz);

        //then
        assertEquals(map.objectAt(vector1), kot);
        assertEquals(map.objectAt(vector2), pies);
        assertEquals(map.objectAt(vector3), mysz);
        assertNull(map.objectAt(new Vector2d(5, 6)));
    }
}