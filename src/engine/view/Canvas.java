package engine.view;

import engine.view.CanvasPainter;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by thomas on 4-2-17.
 */
public class Canvas extends JComponent {
    ArrayList<CanvasPainter> painters = new ArrayList<CanvasPainter>();

    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (CanvasPainter painter : painters) {
            painter.paint((Graphics2D) graphics);
        }
    }

    public void addPainter(CanvasPainter painter) {
        painters.add(painter);
    }
}
