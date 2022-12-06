package agh.ics.oop;

import agh.ics.oop.gui.App;

import java.util.ArrayList;
import java.util.List;

public class SimulationEngine implements IEngine, Runnable{

    MoveDirection[] moves;
    List<Animal> animals = new ArrayList<>();
    private int moveDelay;
    private App app;

    public SimulationEngine(MoveDirection[] moves, IWorldMap map, Vector2d[] animalsPositions, App app, int moveDelay) {
        for (int i = 0; i < animalsPositions.length; i++) {
            Animal animal = new Animal(map, animalsPositions[i]);
            if (map.place(animal)) {
                this.animals.add(animal);
            }
        }

        this.moves = moves;
        this.moveDelay = moveDelay;
        this.app = app;
//        System.out.println(map);
    }

    @Override
    public void run() {
        try {
            for (int i = 0; i < moves.length; i++) {

                animals.get(i % animals.size()).move(moves[i]);
                this.app.animation();
                Thread.sleep(moveDelay);
            }
        }catch (InterruptedException exception){
            exception.getMessage();
        }


    }
}