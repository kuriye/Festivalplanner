package visitorInformation;

import maplogic.Camera;
import pathfinding.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.*;
import java.util.ArrayList;

public class VisitorLayer {

    private Point2D clickPosition;
    private Point2D prePosition;
    private Visitor currentVisitor;
    private Rectangle2D rectangle2D;
    public void drawVisitorInformation(Graphics2D g2d, ArrayList<Visitor> visitors, Camera camera){
        AffineTransform at = camera.getTransform();
        try {
            at.invert();
        } catch (NoninvertibleTransformException e) {
            e.printStackTrace();
        }
        Point2D p = at.transform(new Point2D.Double(camera.getMouseclickX(), camera.getMouseClickY()), null);
        clickPosition = new Point2D.Double(p.getX() ,p.getY());
        if(clickPosition == null || clickPosition.getX() == 0 || clickPosition.getY() == 0 ){

        }
        else{
            if (!clickPosition.equals(prePosition)) {
                currentVisitor = null;
                rectangle2D = new Rectangle2D.Double(10,10,256,48);
                double smallestDistance = 20;
                for(Visitor visitor : visitors){
                    if(visitor.getPosition().distance(clickPosition) < smallestDistance){
                        currentVisitor = visitor;
                        smallestDistance = visitor.getPosition().distance(clickPosition);
                    }
                }
            }
            if(currentVisitor == null) {

            } else{
                AffineTransform tb = currentVisitor.getTransform();
                g2d.setColor(Color.green);
                g2d.fill(rectangle2D);
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(10));
                g2d.draw(rectangle2D);
                g2d.setFont(new JLabel().getFont().deriveFont(20f));
                g2d.drawString("Name: " +currentVisitor.getName(), (int)rectangle2D.getX() + 10, (int)rectangle2D.getY() + 32);
                g2d.setFont(new JLabel().getFont());
                g2d.draw(new Rectangle2D.Double((int)tb.getTranslateX() -12,(int)tb.getTranslateY() - 16,10,10));
                prePosition = clickPosition;
            }
        }

    }
}
