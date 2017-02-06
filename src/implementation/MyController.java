package implementation;

import engine.GameLoop;
import engine.command.InputCommand;
import engine.controller.Controller;
import engine.controller.GameState;
import engine.view.gui.DebugView;
import implementation.model.painters.BattlePainter;

/**
 * Created by thomas on 4-2-17.
 */
public class MyController extends Controller {
    public GameState debugState = new GameState();
    public GameState exitState = new GameState();

    public GameState startBattleState = new GameState();

    {
        debugState.setOnActive(() -> {
            //println("Press start to load model.");
        });

        debugState.mapCommand(InputCommand.getTypeObject("START"), command -> {
            println("Going to enter battle state.");
            setState(startBattleState);
        });

        debugState.mapCommand(InputCommand.getTypeObject("CANCEL"), command -> {
            setState(exitState);
        });
    }

    {
        startBattleState.setOnActive(() -> {
            setModel(new MyModel());
            model().startBattle();
            getView().addPainter(new BattlePainter(model()));
        });
    }

    {
        exitState.setOnActive(() -> {
            println("Do you really want to exit the game?");
        });

        exitState.mapCommand(InputCommand.getTypeObject("CONFIRM"), command -> {
            System.exit(0);
        });

        exitState.mapCommand(InputCommand.getTypeObject("CANCEL"), command -> {
            removeState();
        });
    }

    GameState initialState = debugState;

    public void println(String s) {
        ((DebugView) getView()).println(s);
    }

    public MyModel model() {
        return (MyModel) getModel();
    }

    public MyController() {
        setState(getInitialState());
    }

    public GameState getInitialState() {
        return initialState;
    }
}
