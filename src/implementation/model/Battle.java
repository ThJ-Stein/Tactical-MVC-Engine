package implementation.model;

import java.util.ArrayList;

/**
 * Created by thomas on 4-2-17.
 */
public class Battle {
    private GridMap map;

    private ArrayList<BattleUnit> units;

    private Battle(GridMap map) {
        this.map = map;
        units = new ArrayList<>();
    }

    public void setUnitOnTile(BattleUnit unit, int x, int y) {
        units.add(unit);
        map.getTile(x, y).setUnit(unit);
        unit.setLocation(x, y);
    }

    @Override
    public String toString() {
        return "Battle{" +
                "map=" + "\n" + map + "\n" +
                ", units=" + units +
                '}';
    }

    public static void main(String[] args) {
        Battle battle = new Battle(GridMap.createGridMap());

        BattleUnit unit = new BattleUnit(Unit.createUnitWithJob(Job.getJob("MERCHANT")));
        battle.setUnitOnTile(unit, 2, 5);

        System.out.println(battle);
    }
}