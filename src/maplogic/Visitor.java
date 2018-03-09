package maplogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 */
public class Visitor {
    private Point2D position;
    private double angle;
    private BufferedImage image;
    private double speed;
    private Point2D targetPosition = new Point2D.Double(100,100);

    /**
     * Creates a visitor object wich will walk around the festival.
     */
    public Visitor()
    {
        position = new Point2D.Double(1000,500);
        speed = 2;
        angle = 0;
        try {
            image = ImageIO.read(getClass().getResource("/poppetje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * draws the visitor on the panel.
     * @param g2d is contained from a paintcomponent from a panel.
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
        int directionX = 0;
        int directionY = 0;
        Point2D diff = new Point2D.Double(
                targetPosition.getX() - position.getX(),
                targetPosition.getY() - position.getY()
        );

        //calculates wich way the visitor needs to go. First the x-coordinate then the y.
        if (position.getX() != targetPosition.getX()){
            if (diff.getX() <= 0){
                directionX = -1;
            }
            else{
                directionX = 1;
            }
        }
        else if(position.getY() != targetPosition.getY()) {
            if (diff.getY() <= 0){
                directionY = -1;
            }
            else{
                directionY = 1;
            }
        }
        Point2D lastPosition = position;
        position = new Point2D.Double(
                position.getX() + speed * directionX,
                position.getY() + speed * directionY);


        boolean hasCollision = hasCollision(visitors);

        if(hasCollision)
        {
            position = lastPosition;
        }
    }

    /**
     * checks if some visitor collides
     * @param visitors contains all the visitors including itself.
     * @return boolean if collision is true or not.
     */
    public boolean hasCollision(ArrayList<Visitor> visitors) {
        boolean hasCollision = false;
        for(Visitor visitor : visitors)
        {
            if(visitor == this)
                continue;
            double distance = position.distance(visitor.position);
            if(distance < 64)
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
}
