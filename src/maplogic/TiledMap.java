package maplogic;

import javax.imageio.ImageIO;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Objects;

public class TiledMap extends JPanel {
    private int height;
    private int width;

    private ArrayList<TiledTile> tiles = new ArrayList<>();
    private ArrayList<TiledLayer> layers = new ArrayList<>();
    private ArrayList<TiledTarget> targets = new ArrayList<>();
    private CollisionLayer collisionLayer;

    public TiledMap(String filename) {
        JsonReader reader;
        try {
            reader = Json.createReader(getClass().getResourceAsStream(filename));
            JsonObject objectReader = (JsonObject) reader.read();
            JsonArray tilesets = objectReader.getJsonArray("tilesets");

            BufferedImage image = ImageIO.read(getClass().getResource("/terrain.png"));
            height = image.getHeight() /32;
            width = image.getWidth() / 32;

            for (int i = 0; i < tilesets.size(); i++) {
                JsonObject tileset = tilesets.getJsonObject(i);

                int tileWidth = objectReader.getInt("tilewidth");
                int tileHeight = objectReader.getInt("tileheight");
                int index = tileset.getInt("firstgid");

                while (tiles.size() < index + (height * width))
                    tiles.add(new TiledTile());

                for (int y = 0; y < image.getHeight(); y += tileHeight) {
                    for (int x = 0; x < image.getWidth(); x += tileWidth) {
                        tiles.get(index).tile = image.getSubimage(32 * (x / tileWidth), 32 * (y / tileHeight), 32, 32);
                        index++;
                    }
                }
            }

            JsonArray jsonLayers = objectReader.getJsonArray("layers");
            JsonArray jsontarget = null;
            for (int i = 0; i < jsonLayers.size(); i++)
            {
                if (jsonLayers.getJsonObject(i).getString("type").equals("tilelayer"))
                {
                    if(jsonLayers.getJsonObject(i).getString("name").equals("collision"))
                        collisionLayer = new CollisionLayer(jsonLayers.getJsonObject(i), this);
                    else{
                        layers.add(new TiledLayer(jsonLayers.getJsonObject(i), this));
                    }
                }
                else if (jsonLayers.getJsonObject(i).getString("type").equals("objectgroup"))
                {
                    jsontarget = jsonLayers.getJsonObject(i).getJsonArray("objects");
                }
            }

            //saves places of targets.
            for (int i = 0; i < Objects.requireNonNull(jsontarget).size() ; i++)
            {
                JsonObject targetObject = jsontarget.getJsonObject(i);
                if(targetObject.getString("type").equals("spawn")){

                }
                targets.add(new TiledTarget(targetObject));
            }
        }
        catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2,AffineTransform transform) {
        for(TiledLayer l : layers) {
            if (l.isVisible())
                g2.drawImage(l.getImage(), transform, null);
        }
    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        for(TiledLayer l : layers) {
            if (l.isVisible())
                g2d.drawImage(l.getImage(), tx,null);
        }

        g2d.setColor(Color.RED);
        for(int y = 0; y <= 112; y++){
            for(int x = 0; x <= 128; x++){
                Rectangle2D rectangle2D = new Rectangle2D.Double(x * 32, y * 32, 32,32);
                g2d.draw(tx.createTransformedShape(rectangle2D));
            }
        }
    }

    public ArrayList<CollisionTile> getCollisionTiles(){
        return collisionLayer.getCollisionTiles();
    }

    public ArrayList<TiledTarget> getTargets() {
        return targets;
    }

    public ArrayList<TiledTile> getTiledSize() {return tiles; }
}
