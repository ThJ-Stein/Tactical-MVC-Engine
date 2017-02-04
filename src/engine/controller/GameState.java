package engine.controller;

import engine.command.InputCommand;
import engine.command.InputCommandHandler;

import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class GameState {

    private Runnable onActive;
    private Runnable onInactive;

    private HashMap<InputCommand.Type, InputCommandHandler> commandMap;

    public GameState() {
        commandMap = new HashMap<>();
    }

    public void mapCommand(InputCommand.Type commandType, InputCommandHandler handler) {
        commandMap.put(commandType, handler);
    }

    public void setOnActive(Runnable action) {
        onActive = action;
    }

    public void setOnInactive(Runnable action) {
        onInactive = action;
    }

    public void setActive() {
        if (onActive != null) {
            onActive.run();
        }
    }

    public void setInactive() {
        if (onInactive != null) {
            onInactive.run();
        }
    }

    public boolean canTakeInput(InputCommand command) {
        return commandMap.containsKey(command.getType());
    }

    public InputCommandHandler getCommandHandler(InputCommand command) {
        return commandMap.get(command.getType());
    }
}
