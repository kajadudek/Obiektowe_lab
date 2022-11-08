package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    MoveDirection[] moves;
    List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsPositions) {
        for (int i = 0; i < animalsPositions.length; i++) {
            if (!map.isOccupied(animalsPositions[i])) {
                Animal animal = new Animal(map, animalsPositions[i]);
                map.place(animal);
                this.animals.add(animal);
            }
        }

        this.moves = moves;
    }


    @Override
    public void run() {
        for(int i = 0; i < moves.length; i++){
            animals.get(i % animals.size()).move(moves[i]);
        }
    }
}