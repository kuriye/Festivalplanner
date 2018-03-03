package maplogic;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.util.ArrayList;

public class TiledLayer {
    private ArrayList<Integer> data = new ArrayList<>();
    private int height;
    private int width;
    private boolean visible;

    public TiledLayer(String fileName, int layer){
        try {
            JsonReader reader = Json.createReader(getClass().getResourceAsStream(fileName));
            JsonObject root = reader.readObject();

            height = root.getJsonArray("layers").getJsonObject(0).getInt("height");
            width = root.getJsonArray("layers").getJsonObject(0).getInt("width");
            visible = root.getJsonArray("layers").getJsonObject(0).getBoolean("visible");

            for(int y = 0; y < getHeight(); y++) {
                for(int x = 0; x < getWidth(); x++) {
                    data.add(root.getJsonArray("layers").getJsonObject(layer).getJsonArray("data").getInt(x));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Integer> getData() {
        return data;
    }

    public void setData(ArrayList<Integer> data) {
        this.data = data;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

}
