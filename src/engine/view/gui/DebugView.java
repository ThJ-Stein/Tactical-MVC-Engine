package engine.view.gui;

import engine.view.*;
import engine.view.Canvas;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thomas on 4-2-17.
 */
public class DebugView extends View {

    private JPanel rootPanel;
    private JTextField textField1;
    private JButton enterButton;

    private Canvas canvas;

    public DebugView(String s) {
        super(s);
        super.canvas = canvas;

        //only works with super.add somehow
        //regular add doesn't do anything unless I use a System.out.println
        super.add(rootPanel);
    }

    public static void main(String[] args) {
        View view = new DebugView("Debug");
    }

}
