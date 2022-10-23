package agh.ics.oop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class OptionsParserTest {

    @Test
    void parseTest() {
        String[] direction = {"forward", "backward", "right", "left", "xyz", "f", "b", "r", "l", "forwad"};
        MoveDirection[] result = new MoveDirection[]{MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.OTHER, MoveDirection.FORWARD, MoveDirection.BACKWARD, MoveDirection.RIGHT, MoveDirection.LEFT, MoveDirection.OTHER};
        assertArrayEquals(result, OptionsParser.parse(direction));
    }

    @Test
    void parseTestNullId() {
        String[] direction = {};
        assertArrayEquals(new MoveDirection[]{}, OptionsParser.parse(direction));
    }
}