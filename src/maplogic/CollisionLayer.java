package maplogic;


import javax.json.JsonArray;
import javax.json.JsonObject;
public class CollisionLayer {

    private JsonArray data = null;
    private int height;
    private int width;
    private TiledMap map;
    public int[][] indices;

    public CollisionLayer(JsonObject layer, TiledMap tiledMap) {
        this.map = tiledMap;
        data = layer.getJsonArray("data");
        height = layer.getInt("height");
        width = layer.getInt("width");

        indices = new int[height][width];

        int i = 0;
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                indices[y][x] = data.getInt(i);
                i++;
            }
        }
        System.out.println();
    }
}
