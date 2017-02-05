package implementation.model;

/**
 * Created by thomas on 4-2-17.
 */
public class BattleUnit {
    private final Unit unit;
    private int x;
    private int y;

    public BattleUnit(Unit unit) {
        this.unit = unit;
    }

    public int x() {
        return x;
    }

    public int y() {
        return y;
    }

    public void setLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
