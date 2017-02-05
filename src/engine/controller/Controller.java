package engine.controller;

import engine.GameLoop;
import engine.Model;
import engine.command.InputCommand;
import engine.command.InputCommandHandler;
import engine.controller.GameState;
import engine.view.View;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * Created by thomas on 4-2-17.
 */
public abstract class Controller {
    private final int FPS = 60;

    private Queue<InputCommand> commandQueue;

    private Stack<GameState> gameStates;

    //private GameState gameState;

    private GameLoop gameLoop;
    private Model model;
    private View view;

    {
        gameStates = new Stack<>();

        commandQueue = new LinkedBlockingQueue<>();

        gameLoop = new GameLoop(FPS, this::run);
    }

    public Controller() {
        gameLoop.start();
    }

    public void run() {
        InputCommand command = commandQueue.peek();
        if (command != null && getGameState().canTakeInput(commandQueue.poll())) {
            InputCommandHandler handler = getGameState().getCommandHandler(command);
            handler.executeCommand(command);
        }
    }

    public void enqueueCommand(InputCommand command) {
        commandQueue.add(command);
    }

    public void setView(View view) {
        this.view = view;
    }

    public View getView() {
        return view;
    }

    public void setModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public void setState(GameState gameState) {
        gameStates.push(gameState);
        gameState.setActive();
    }

    public void removeState() {
        gameStates.pop().setInactive();
    }

    public GameState getGameState() {
        return gameStates.peek();
    }
}
