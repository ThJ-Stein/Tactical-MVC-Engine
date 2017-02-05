package implementation.model;

import java.util.Arrays;

/**
 * Created by thomas on 4-2-17.
 */
public class GridMap {
    private final GridTile[][] tiles;

    private GridMap(GridTile[][] tiles) {
        this.tiles = tiles;
    }

    public int getHeight() {
        return tiles.length;
    }

    public int getWidth() {
        assert tiles.length > 0;
        return tiles[0].length;
    }

    public GridTile getTile(int x, int y) {
        assert getWidth() > x;
        assert getHeight() > y;

        return tiles[y][x];
    }

    @Override
    public String toString() {
        String value = "";
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                value += getTile(x, y) + " ";
            }
            value += "\n";
        }
        return value;
    }

    //TODO this method is veeeery unfinished
    public static GridMap createGridMap() {
        GridTile[][] tiles = new GridTile[10][10];

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                tiles[j][i] = new GridTile(i, j, 1);
            }
        }
        GridMap map = new GridMap(tiles);
        return map;
    }
}
