package maplogic;

import javax.json.JsonArray;
import javax.json.JsonObject;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class TiledTarget
{
    private int height;
    private int width;
    private Point2D position;
    private int tileNumberX;
    private int tileNumberY;
    private double tilesWidth;
    private int tilesHeight;

    public TiledTarget(JsonObject target)
    {
        height = target.getInt("height");
        width = target.getInt("width");
        int x = target.getInt("x");
        int y = target.getInt("y");

        position = new Point2D.Double(x,y);

        tileNumberX = x/32;
        tileNumberY = y/32;
        tilesWidth = width/32;
        tilesHeight = height/32;
    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        g2d.setColor(Color.green);
        Rectangle2D rectangle2D = new Rectangle2D.Double(position.getX(), position.getY(), tilesWidth * 32, tilesHeight * 32);
        g2d.draw(tx.createTransformedShape(rectangle2D));
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

    public int getTileNumberX() {
        return tileNumberX;
    }

    public void setTileNumberX(int tileNumberX) {
        this.tileNumberX = tileNumberX;
    }

    public int getTileNumberY() {
        return tileNumberY;
    }

    public void setTileNumberY(int tileNumberY) {
        this.tileNumberY = tileNumberY;
    }

    public double getTilesWidth() {
        return tilesWidth;
    }

    public void setTilesWidth(double tilesWidth) {
        this.tilesWidth = tilesWidth;
    }

    public int getTilesHeight() {
        return tilesHeight;
    }

    public void setTilesHeight(int tilesHeight) {
        this.tilesHeight = tilesHeight;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }
}
