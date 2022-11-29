package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseTest() {
        String[] direction = {"forward", "backward", "right", "left", "f", "b", "r", "l"};
        MoveDirection[] result = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT};
        assertArrayEquals(result, OptionsParser.parse(direction));
    }

    @Test
    void parseTestNullId() {
        String[] direction = {};
        assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(direction));
    }

    @Test
    void exceptionThrow() {
        String[] direction = {"forward", "b", "right", "rght", "x"};

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {OptionsParser.parse(direction); });
        assertEquals(exception.getMessage(), "rght" + " is not legal move specification");
    }
}