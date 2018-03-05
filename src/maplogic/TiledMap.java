package maplogic;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class TiledMap extends JPanel {
    public int height;
    public int width;

    public ArrayList<TiledTile> tiles = new ArrayList<>();
    public ArrayList<TiledLayer> layers = new ArrayList<>();

    public TiledMap(String filename, String terrainName) {
        JsonReader reader = null;
        JsonReader terrainReader= null;
        try {
            reader = Json.createReader(getClass().getResourceAsStream(filename));
            terrainReader = Json.createReader(getClass().getResourceAsStream(terrainName));

            JsonObject objectReader = (JsonObject) reader.read();

            height = objectReader.getInt("height");
            width = objectReader.getInt("width");

            JsonArray tilesets = objectReader.getJsonArray("tiles");

            for (int i = 0; i < tilesets.size(); i++) {
                JsonObject tileset = tilesets.getJsonObject(i);
                String tileFile = tileset.getString("image");
                tileFile = tileFile.replaceAll("\\.\\./", "/"); //regex

                BufferedImage img = ImageIO.read(getClass().getResource(tileFile));

                int tilesetWidth = tileset.getInt("imagewidth");
                int tilesetHeight = tileset.getInt("imageheight");
                int tileWidth = tileset.getInt("tilewidth");
                int tileHeight = tileset.getInt("tileheight");


                int index = tileset.getInt("firstgid");
                while (tiles.size() < index + tileset.getInt("tilecount"))
                    tiles.add(new TiledTile());


                for (int y = 0; y + tileHeight <= tilesetHeight; y += tileHeight) {
                    for (int x = 0; x + tileWidth <= tilesetWidth; x += tileWidth) {
                        tiles.get(index).tile = img.getSubimage(x, y, tileWidth, tileHeight);
                        index++;
                    }
                }

                index = tileset.getInt("firstgid");
                for (int ii = 0; ii < tileset.getInt("tilecount"); ii++) {
                    tiles.get(index).walkable = tileset.getJsonObject("tileproperties").getJsonObject(ii + "").getBoolean("walkable");
                    index++;
                }
            }


            JsonArray jsonLayers = objectReader.getJsonArray("layers");
            for (int i = 0; i < jsonLayers.size(); i++) {
                if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer")) {
                    layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
                } else {

                }
            }


        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }


    public void draw(Graphics2D g2) {
        for(TiledLayer l : layers) {
            if (l.visible)
                g2.drawImage(l.image, new AffineTransform(), null);
        }
    }
}
