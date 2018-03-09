package maplogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;


public class Visitor {
    private Point2D position;
    private double angle;
    private BufferedImage image;
    private double speed;
    private Point2D targetPosition = new Point2D.Double(100,100);

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

    public void draw(Graphics2D g2d, AffineTransform tx) {

        tx.translate(position.getX() - image.getWidth()/2, position.getY() - image.getHeight()/2);
        tx.rotate(angle, image.getWidth()/2, image.getHeight()/2);
        g2d.drawImage(image, tx, null);
    }

    public void update(ArrayList<Visitor> visitors) {
        int directionX = 0;
        int directionY = 0;
        Point2D diff = new Point2D.Double(
                targetPosition.getX() - position.getX(),
                targetPosition.getY() - position.getY()
        );

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

    public void setTarget(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }
}
