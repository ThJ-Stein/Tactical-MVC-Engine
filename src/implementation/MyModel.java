package implementation;

import engine.Model;
import implementation.model.*;

import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by thomas on 4-2-17.
 */
public class MyModel extends Model {
    private ArrayList<Unit> unitList = new ArrayList<>();

    private Battle activeBattle = null;

    public void startBattle() {
        try {
            activeBattle = new Battle(GridMap.readGridmap("map0.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Battle getBattle() {
        return activeBattle;
    }
}
