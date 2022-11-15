package agh.ics.oop;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GrassFieldTest {

    //given
    IWorldMap map = new GrassField(10);
    Vector2d vector1 = new Vector2d(3,4);
    Vector2d vector2 = new Vector2d(0,5);
    Vector2d vector3 = new Vector2d(9, 2);
    Animal kot = new Animal(map, vector1);
    Animal pies = new Animal(map, vector2);
    Animal mysz = new Animal(map, vector3);

    @Test
    public void canMoveToTest(){
        //when
        map.place(kot);
        map.place(pies);
        map.place(mysz);

        //then
        assertFalse(map.canMoveTo(vector1));
        assertFalse(map.canMoveTo(vector2));
        assertFalse(map.canMoveTo(vector3));
        assertTrue(map.canMoveTo(new Vector2d(3,5)));
        assertFalse(map.canMoveTo(new Vector2d(-4,0)));
    }

}