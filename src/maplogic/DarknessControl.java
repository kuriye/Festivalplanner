package maplogic;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DarknessControl {

    public void setAlphaValue(int percentage, Graphics2D g2d,int width,int height) {
        float alphaValue;
        alphaValue = percentage/ 1000.0f;
        if(percentage <= 600) {
            g2d.setColor(new Color(15, 38, 110));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, alphaValue));
            g2d.fill(new Rectangle2D.Double(0, 0, width, height));
        }
        else {
            g2d.setColor(new Color(15, 38, 110));
            g2d.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.6f));
            g2d.fill(new Rectangle2D.Double(0, 0, width, height));
        }
    }
}

