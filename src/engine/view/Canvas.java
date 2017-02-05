package engine.view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * This JComponent subclass is the canvas that will be repainted on every tick of the GameLoop. When the GameLoop
 * (or any other object) calls canvas.repaint(), this class will go through its own list of painters, and have
 * all of them draw their own content on the canvas.
 * Created by thomas on 4-2-17.
 */
public class Canvas extends JComponent {

    /**
     * List of painters that will draw upon the board, in order of index.
     * TODO: Design more elegant way to handle the order of painting
     */
    private ArrayList<CanvasPainter> painters = new ArrayList<>();

    /**
     * This method gets called when repaint() is called by another object (the GameLoop). It sets the screen to
     * its background color by calling super.paint(), then it calls the paint() method of all painters in the
     * painter array, by order of index.
     * @param graphics the graphics object that gets passed to the method when repaint() is called
     */
    @Override
    public void paint(Graphics graphics) {
        super.paint(graphics);

        for (CanvasPainter painter : painters) {
            painter.paint((Graphics2D) graphics);
        }
    }

    /**
     * Adds a painter to the end of the list.
     * @param painter the painter to add
     */
    public void addPainter(CanvasPainter painter) {
        painters.add(painter);
    }

    /**
     * Removes a painter from the list of.
     * @param painter the painter to remove
     */
    public void removePainter(CanvasPainter painter) {
        assert painters.contains(painter);
        painters.remove(painter);
    }
}
