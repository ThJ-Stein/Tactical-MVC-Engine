package engine.controller.engine.controller;

import engine.command.InputCommand;
import engine.command.InputCommandHandler;

import java.util.HashMap;

/**
 * Created by thomas on 4-2-17.
 */
public class GameState {
    private HashMap<InputCommand.Type, InputCommandHandler> commandMap;

    public GameState() {
        commandMap = new HashMap<>();
    }

    public void mapCommand(InputCommand.Type commandType, InputCommandHandler handler) {
        commandMap.put(commandType, handler);
    }

    public boolean canTakeInput(InputCommand command) {
        return commandMap.containsKey(command.getType());
    }

    public InputCommandHandler getCommandHandler(InputCommand command) {
        return commandMap.get(command.getType());
    }
}
