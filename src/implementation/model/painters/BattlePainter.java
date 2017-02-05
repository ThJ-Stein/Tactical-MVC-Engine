package implementation.model.painters;

import engine.view.CanvasPainter;
import implementation.MyModel;
import implementation.model.GridMap;

import java.awt.*;

/**
 * Created by thomas on 5-2-17.
 */
public class BattlePainter extends CanvasPainter {

    private final MyModel model;

    private final int DX = 20;
    private final int DY = 20;

    public BattlePainter(MyModel model) {
        this.model = model;
    }

    @Override
    public void paint(Graphics2D g) {
        GridMap map = model.getBattle().getMap();

        g.setColor(Color.BLACK);

        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                String height = ((int) map.getTile(x, y).height()) + "";

                g.drawString(height + "", x * DX, y * DY);
            }
        }
    }
}
