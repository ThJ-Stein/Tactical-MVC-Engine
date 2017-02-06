package implementation.model;

/**
 * Created by thomas on 4-2-17.
 */
public class GridTile {
    private final int x;
    private final int y;
    private final double height;

    private BattleUnit unit = null;

    public GridTile(int x, int y, double height) {
        this.x = x;
        this.y = y;
        this.height = height;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public double height() {
        return height;
    }

    public BattleUnit getUnit() {
        return unit;
    }

    public void setUnit(BattleUnit unit) {
        this.unit = unit;
    }

    @Override
    public String toString() {
        return unit != null ? "@" : ".";
    }
}
