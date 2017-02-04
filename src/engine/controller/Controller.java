package engine.controller;

import engine.GameLoop;
import engine.command.InputCommand;
import engine.command.InputCommandHandler;

import java.util.HashMap;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;

/**
 * Created by thomas on 4-2-17.
 */
public abstract class Controller {
    private final int FPS = 60;

    private Queue<InputCommand> commandQueue;

    private HashMap<InputCommand.Type, InputCommandHandler> commandMap;

    private GameLoop gameLoop;

    {
        commandQueue = new LinkedBlockingQueue<>();

        commandMap = new HashMap<>();

        gameLoop = new GameLoop(FPS, this::run);
    }

    public Controller() {
        gameLoop.start();
    }

    public void run() {
        InputCommand command = commandQueue.peek();
        if (command != null && commandMap.containsKey(command.getType())) {
            commandQueue.poll();
            InputCommandHandler handler = commandMap.get(command.getType());
            System.out.println(handler);
        }
    }

    public void enqueueCommand(InputCommand command) {
        commandQueue.add(command);
    }
}
