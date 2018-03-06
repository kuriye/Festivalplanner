package maplogic;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;

/**
 * This method cuts the map in pieces and puts it in an array,
 * so the simulation can make difference between the different tiles.
 */
public class TiledTileMap {

    private BufferedImage[] tiles;

    public TiledTileMap() {
        try {
            BufferedImage image = ImageIO.read(getClass().getResource("terrain.png"));
            tiles = new BufferedImage[40000];
            for (int i = 0; i < 40000; i++)
                tiles[i] = image.getSubimage(32 * (i % 200), 32 * (i / 200), 32, 32);
            } catch (Exception e) {
            e.printStackTrace();
            }
    }

    /**
     * returns the whole array of buffered images
     * @return tiles
     */
    public BufferedImage[] getArray(){
        return tiles;
    }
}
