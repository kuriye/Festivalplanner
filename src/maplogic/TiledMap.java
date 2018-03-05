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

    private BufferedImage image;

    public ArrayList<TiledTile> tiles = new ArrayList<>();
    public ArrayList<TiledLayer> layers = new ArrayList<>();

    public TiledMap(String filename) {
        JsonReader reader = null;
        try {
            reader = Json.createReader(getClass().getResourceAsStream(filename));
            JsonObject objectReader = (JsonObject) reader.read();
            JsonArray tilesets = objectReader.getJsonArray("tilesets");

            image = ImageIO.read(getClass().getResource("/terrain.png"));

            height = image.getHeight() /32;
            width = image.getWidth() / 32;

            for (int i = 0; i < tilesets.size(); i++) {
                JsonObject tileset = tilesets.getJsonObject(i);

                int tilesetWidth = width*32;
                int tilesetHeight = height*32;
                int tileWidth = objectReader.getInt("tilewidth");
                int tileHeight = objectReader.getInt("tileheight");
                int index = tileset.getInt("firstgid");

                while (tiles.size() < index + (height + width))
                    tiles.add(new TiledTile());


                for (int y = 0; y + tileHeight <= tilesetHeight; y += tileHeight) {
                    for (int x = 0; x + tileWidth <= tilesetWidth; x += tileWidth) {
                        tiles.get(index).tile = image.getSubimage(32 * (i % 21), 32 * (i / 23), 32, 32);
                        index++;
                    }
                }
            }
            JsonArray jsonLayers = objectReader.getJsonArray("layers");
            for (int i = 0; i < jsonLayers.size(); i++) {
                if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer")) {
                    layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
                }
                else {}
            }
        }
        catch(Exception e) {
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
