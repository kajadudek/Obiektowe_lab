package agh.ics.oop;

import agh.ics.oop.gui.App;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimulationEngineTest {

    @Test
    public void runTest() {
        //given
        App app = new App();
        MoveDirection[] directions = new OptionsParser().parse(new String[]{"f", "b", "r", "l", "f", "f", "r", "r", "f", "f", "f", "f", "f", "f", "f", "f"});
        IWorldMap map = new RectangularMap(10, 5);
        Vector2d[] positions = { new Vector2d(2,2), new Vector2d(3,4) };
        IEngine engine = new SimulationEngine(directions, map, positions, app, 500);

        //when
        engine.run();

        //then
        assertTrue(map.isOccupied(new Vector2d(2,0)));
        assertTrue(map.isOccupied(new Vector2d(3,5)));
    }
}