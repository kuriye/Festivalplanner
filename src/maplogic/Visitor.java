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
    private Point2D targetPosition = new Point2D.Double(500,500);

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

    public void draw(Graphics2D g2d, AffineTransform tx) {

        tx.translate(position.getX() - image.getWidth()/2, position.getY() - image.getHeight()/2);
        tx.rotate(angle, image.getWidth()/2, image.getHeight()/2);
        g2d.drawImage(image, tx, null);
    }

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


        boolean hasCollision = hasCollision(visitors);

        if(hasCollision)
        {
            position = lastPosition;
            angle += 0.2;
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
