package maplogic;

import java.awt.*;
import java.awt.geom.Rectangle2D;

public class DarknessControl {
    private float alphaValue = 1f;

    public DarknessControl(float percentage) {
        alphaValue = percentage/ 100.0f;
    }

    public void setAlphaValue(float percentage, Graphics2D g2d,int width,int height) {
        this.alphaValue = percentage/ 100.0f;
        if(percentage <= 60) {
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

