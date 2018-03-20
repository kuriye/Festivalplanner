package pathfinding;

import maplogic.CollisionTile;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 * The class Visitor represents one visitor which is going to visit the festival.
 */
public class Visitor {
    /**
     * the position artibute is the position of the visitor;
     */
    private Point2D position;

    /**
     * The angle artibute is the angle where the visitor is going to look at.
     */
    private double angle;

    /**
     * The image artibute is the sprite of the visitor.
     */
    private BufferedImage image;

    /**
     * The speed artibute is the speed of the visitor.
     */
    private double speed;

    /**
     * The targetPosition is the position where the visitor needs to go.
     */
    private Point2D targetPosition = new Point2D.Double(500,500);

    /**
     * Creates a visitor object wich will walk around the festival.
     */
    public Visitor()
    {
        position = new Point2D.Double(Math.random()*1000, Math.random()*1000);
        angle = Math.random() * 2 * Math.PI;
        speed = 3 + 4 * Math.random();
        try {
            image = ImageIO.read(getClass().getResource("/poppetje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The draw method draws a visitor on the graphical user interface.
     * @param g2d is gotten from the paintComponent
     * @param tx is an affinetransform which is needed to draw an image on the gui.
     */
    public void draw(Graphics2D g2d, AffineTransform tx) {
        tx.translate(position.getX() - image.getWidth()/2, position.getY() - image.getHeight()/2);
        g2d.drawImage(image, tx, null);
    }

    /**
     * Updates the position of the visitors.
     * @param visitors checks if visitors don't collide with other visitors.
     */
    public void update(ArrayList<Visitor> visitors) {

        Point2D diff = new Point2D.Double(
                targetPosition.getX() - position.getX(),
                targetPosition.getY() - position.getY()
        );

        double targetAngle = Math.atan2(diff.getY(), diff.getX());
        double angleDiff = angle - targetAngle;


        while(angleDiff < -Math.PI)
            angleDiff += Math.PI/2;
        while(angleDiff > Math.PI)
            angleDiff -= Math.PI/2;

        if(angleDiff < 0)
            angle += 0.1;
        else if(angleDiff > 0)
            angle -= 0.1;


        Point2D lastPosition = position;
        position = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));

    }

    /**
     * checks if some visitor collides
     * @param visitors contains all the visitors including itself.
     * @return boolean if collision is true or not.
     */
    public boolean hasCollisionWithVisitor(ArrayList<Visitor> visitors) {
        boolean hasCollision = false;
        for(Visitor visitor : visitors) {
            if (visitor == this)
                continue;
            double distance = position.distance(visitor.position);
            if (distance < 14)
                hasCollision = true;
        }
        return hasCollision;
    }

    /**
     * sets targetPosition where the visitor will be going.
     * @param targetPosition
     */
    public void setTarget(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Point2D getPosition() {
        return position;
    }
}
