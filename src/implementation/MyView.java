package implementation;

import engine.command.InputCommand;
import engine.view.View;
import engine.view.gui.DebugView;
import engine.view.gui.EclipseDebugWindow;

import java.awt.event.KeyEvent;

/**
 * Created by thomas on 4-2-17.
 */
public class MyView extends View {
    public MyView(String s) {
        super(new EclipseDebugWindow("MyApp"));

        mapKey('z', new InputCommand(InputCommand.getTypeObject("CONFIRM")));
        mapKey('x', new InputCommand(InputCommand.getTypeObject("CANCEL")));
        mapKey((char) KeyEvent.VK_ENTER, new InputCommand(InputCommand.getTypeObject("START")));
    }
}
