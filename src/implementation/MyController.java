package implementation;

import engine.command.InputCommand;
import engine.controller.Controller;
import engine.controller.engine.controller.GameState;

/**
 * Created by thomas on 4-2-17.
 */
public class MyController extends Controller {
    GameState debugState = new GameState();

    GameState exitState = new GameState();

    {
        debugState.mapCommand(InputCommand.Type.CONFIRM, command -> {
            System.out.println("Pressed Confirm!");
        });

        debugState.mapCommand(InputCommand.Type.CANCEL, command -> {
            setState(exitState);
        });
    }

    {
        exitState.mapCommand(InputCommand.Type.CONFIRM, command -> {
            System.exit(0);
        });

        exitState.mapCommand(InputCommand.Type.CANCEL, command -> {
            removeState();
        });
    }

    public MyController() {
        setState(debugState);
    }
}
