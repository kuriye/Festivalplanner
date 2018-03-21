package visitorInformation;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;

public class VisitorLayer {

    public void drawVisitorInformation(Graphics2D g2d, AffineTransform tx){
        g2d.setColor(Color.green);
        Rectangle2D rectangle2D = new Rectangle2D.Double(0,0,100,100);
        g2d.fill(rectangle2D);
    }
}
