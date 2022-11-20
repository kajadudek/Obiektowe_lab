package agh.ics.oop;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine{

    MoveDirection[] moves;
    List<Animal> animals = new ArrayList<>();

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsPositions) {
        for (int i = 0; i < animalsPositions.length; i++) {
            Animal animal = new Animal(map, animalsPositions[i]);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }

        this.moves = moves;
        System.out.println(map);
    }

    @Override
    public void run() {
        for(int i = 0; i < moves.length; i++){
            System.out.println(moves[i] + " " + animals.get(0).getPosition() + animals.get(1).getPosition());
            animals.get(i % animals.size()).move(moves[i]);
        }
    }
}