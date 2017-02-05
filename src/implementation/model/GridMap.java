package implementation.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by thomas on 4-2-17.
 */
public class GridMap {
    public static final String PATH = "data/maps/";

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

    public static GridMap readGridmap(String path) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(PATH + path));

        List<String[]> lines = new ArrayList<String[]>();

        String line = null;
        do {
            line = reader.readLine();
            if (line != null) {
                lines.add(line.split(" "));
            }
        } while (line != null);

        reader.close();

        GridTile[][] tiles = new GridTile[lines.size()][lines.get(0).length];

        for (int i = 0; i < tiles[0].length; i++) {
            for (int j = 0; j < tiles.length; j++) {

                String data = lines.get(j)[i];
                GridTile tile = null;

                switch (data) {
                    default:
                        tile = new GridTile(i, j, Integer.parseInt(data));
                        break;
                }

                tiles[j][i] = tile;
            }
        }
        GridMap map = new GridMap(tiles);
        return map;
    }

    public static void main(String[] args) {
        try {
            System.out.println(readGridmap("map0.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
