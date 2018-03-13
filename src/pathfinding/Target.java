package pathfinding;

import maplogic.TiledLayer;
import maplogic.TiledMap;

import java.util.ArrayList;

public class Target {
    private ArrayList<TiledLayer> layers;
    private TiledLayer targetLayer;
    private TiledLayer collisionLayer;
    private int distance;

    public static void main(String[] args) {
        Target target = new Target();
    }

    public Target() {
    }

    public void calculateDistancesFromTarget(){

    }
}
