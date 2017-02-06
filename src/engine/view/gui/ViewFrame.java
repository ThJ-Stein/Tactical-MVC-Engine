package engine.view.gui;

import engine.view.Canvas;

import javax.swing.*;

/**
 * Created by thomas on 6-2-17.
 */
//TODO: consider renaming Viewframe to something related to windows
public abstract class ViewFrame extends JFrame {
    //because of the method call in View, ViewFrame subclasses must only have one argument that is a String
    public ViewFrame(String windowTitle) {
        super(windowTitle);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setVisible(true);

        setFocusable(true);
    }

    public abstract void println(String debugOutput);

    public abstract Canvas getCanvas();
}
