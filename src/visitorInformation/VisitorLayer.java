package visitorInformation;

import maplogic.Camera;
import pathfinding.Visitor;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class VisitorLayer {

    public void drawVisitorInformation(Graphics2D g2d, ArrayList<Visitor> visitors, Camera camera){
        Point2D clickPosition = new Point2D.Double(camera.getMouseclickX() ,camera.getMouseClickY());
        if(clickPosition == null || clickPosition.getX() == 0 || clickPosition.getY() == 0 && !camera.isControlPressed()){

        }
        else{
            Visitor currentVisitor = null;
            g2d.setColor(Color.green);
            Rectangle2D rectangle2D = new Rectangle2D.Double(10,10,256,48);
            for(Visitor visitor : visitors){
                if(visitor.getPosition().distance(clickPosition) <= 15){
                    currentVisitor = visitor;
                }
            }

            if(currentVisitor == null)
                ;
            else{
                g2d.fill(rectangle2D);
                g2d.setColor(Color.WHITE);
                g2d.setStroke(new BasicStroke(10));
                g2d.draw(rectangle2D);
                g2d.setFont(new JLabel().getFont().deriveFont(20f));
                g2d.drawString("Name: " +currentVisitor.getName(), (int)rectangle2D.getX() + 10, (int)rectangle2D.getY() + 32);
                g2d.setFont(new JLabel().getFont());
            }
        }

    }
}
