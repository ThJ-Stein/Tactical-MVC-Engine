package engine.view;

import engine.view.CanvasPainter;

import javax.swing.*;
import java.awt.*;

/**
 * Created by thomas on 4-2-17.
 */
public class Canvas extends JComponent {
    java.util.List<CanvasPainter> painters;

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);
    }
}
