package Gui;

import javax.swing.*;
import java.awt.*;

public class GraphicalTime extends JPanel {

    public GraphicalTime() {

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int increment = getHeight()/24;
        for (int time = 0; time < 25; time++){
            if (time < 10){
                g2d.drawString("0" + time + ":00", 20, increment * time + 15);
            }
            else{
                g2d.drawString(time + ":00", 20, increment * time + 15);
            }

        }
    }
}
