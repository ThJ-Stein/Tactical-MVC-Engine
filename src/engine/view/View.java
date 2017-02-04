package engine.view;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thomas on 4-2-17.
 */
public class View extends JFrame {
    protected Canvas canvas;

    public View(String s) {
        super(s);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
