package maplogic;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Renderable {
    private Shape shape;
    private Point2D position;
    private float rotation;
    private float scale;

    Renderable(Shape shape, Point2D position, float rotation, float scale) {
        this.shape = shape;
        this.position = position;
        this.rotation = rotation;
        this.scale = scale;
    }

    public Point2D getPosition() {
        return position;
    }

    public void setPosition(Point2D position) {
        this.position = position;
    }

    public void draw(Graphics2D g2d) {
        g2d.draw(getTransformedShape());
    }

    public Shape getTransformedShape() {
        return getTransform().createTransformedShape(shape);
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(position.getX(), position.getY());
        tx.rotate(rotation);
        tx.scale(scale,scale);
        return tx;
    }
}
