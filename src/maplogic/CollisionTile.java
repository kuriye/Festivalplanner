package maplogic;

import pathfinding.Visitor;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class CollisionTile {
    private Point2D position;
    private Point2D tilePosition;
    private static final int WIDTH = 32;
    private static final int HEIGHT = 32;

    CollisionTile(int yIndice, int xIndice){
        position = new Point2D.Double(xIndice * 32,yIndice * 32);
        tilePosition = new Point2D.Double(xIndice, yIndice);
    }

    public boolean hasCollisionWithVisitor(Visitor visitor) {
        Point2D visitorPosition = visitor.getPosition();
        Boolean collision = false;
        if (visitorPosition.getY() >= position.getY() && visitor.getPosition().getY() <= position.getY() + HEIGHT) {
            if (visitorPosition.getX() >= position.getX() && visitor.getPosition().getX() <= position.getX() + WIDTH) {
                collision = true;
            }
        }
        return collision;
    }


    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        Rectangle2D rectangle2D = new Rectangle2D.Double(position.getX(), position.getY(),WIDTH,HEIGHT);
        g2d.setColor(Color.blue);
        g2d.draw(tx.createTransformedShape(rectangle2D));
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public Point2D getTilePosition() {
        return tilePosition;
    }

    public void setTilePosition(Point2D tilePosition) {
        this.tilePosition = tilePosition;
    }
}
