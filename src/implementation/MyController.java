package implementation.implementation;

import engine.command.InputCommand;
import engine.command.InputCommandHandler;
import engine.controller.Controller;
import engine.controller.engine.controller.GameState;

/**
 * Created by thomas on 4-2-17.
 */
public class MyController extends Controller {
    GameState debugState = new GameState();

    {
        debugState.mapCommand(InputCommand.Type.CONFIRM, command -> {
            System.out.println(command);
        });
    }

    public MyController() {
        enterState(debugState);
    }
}
