package maplogic;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

public class Visitor {

    private Point2D position;
    private Point2D targetPosition = new Point2D.Double(500,500);

    private int speed = 2;
    private double angle;
    private BufferedImage image;

    public Visitor(){
        position = new Point2D.Double(Math.random()*1000, Math.random()*1000);
        angle = 0;
        try{
            image = ImageIO.read(getClass().getResource("visitor.png"));
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void update(){
        Point2D diff = new Point2D.Double(position.getX() - targetPosition.getX(), position.getY() - targetPosition.getY());
        double targetAngle = Math.atan2(diff.getY(), diff.getX());

        double angleDiff = angle - targetAngle;

        if(angleDiff < 0)
            angle += 0.1;
        else if(angleDiff > 0)
            angle -= 0.1;

        position = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));
    }

    public void draw(Graphics2D g2d){
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate(position.getX() - image.getWidth()/2, position.getY() -image.getHeight()/2);
        affineTransform.rotate(angle, image.getWidth()/2, image.getHeight()/2);
        g2d.drawImage(image, affineTransform, null);
    }
}
