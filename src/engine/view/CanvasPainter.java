package engine.view;

import java.awt.*;

/**
 * A painter takes an object, and uses its data to draw something on a Graphics2D object.
 * A canvas has a list of painters. The canvas calls each painter's paint method once per tick.
 *
 * The painter does not need to know which canvas it is that calls him. It only needs to paint a visual
 * representation of the object on the Graphics2D that gets passed in the paint method.
 *
 * Created by thomas on 4-2-17.
 */
public interface CanvasPainter {

    /**
     * Paints some image on the canvas to which the Graphics2D object belongs.
     * @param g the graphics object to paint on
     */
    void paint(Graphics2D g);
}
