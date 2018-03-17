package maplogic;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class TiledTarget
{
    private int height;
    private int width;
    private int x;
    private int y;

    private int tileNumberX;
    private int tileNumberY;
    private double tilesWidth;
    private int tilesHeight;

    public TiledTarget(JsonObject target)
    {
        height = target.getInt("height");
        width = target.getInt("width");
        x = target.getInt("x");
        y = target.getInt("y");

        tileNumberX = (int)x/32 + 1;
        tileNumberY = (int)y/32 + 1;
        tilesWidth = width/32;
        tilesHeight = height/32;
    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        g2d.setColor(Color.green);
        Rectangle2D rectangle2D = new Rectangle2D.Double(x, y, tilesWidth * 32, tilesHeight * 32);
        g2d.draw(tx.createTransformedShape(rectangle2D));
    }


}
