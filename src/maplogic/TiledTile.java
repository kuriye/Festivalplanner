package maplogic;

import java.awt.image.BufferedImage;

public class TiledTile {

    TiledLayer tiledLayer;
    TiledTileMap tiledTileMap;



    public TiledTile(TiledLayer tiledLayer) {

        this.tiledLayer = tiledLayer;
    }

    public BufferedImage getBufferdImage(int index)
    {
        BufferedImage[] images = tiledTileMap.getArray();
        if(index == 0)
        {
            return null;
        }
        else
        {
            return images[index -1];
        }

    }
}
