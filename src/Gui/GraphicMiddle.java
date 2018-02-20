package Gui;
import javax.swing.*;
import java.awt.*;

public class GraphicMiddle extends JPanel {
    public GraphicMiddle() {

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int increment = getHeight()/24;
        for (int time = 0; time < 25; time++){
            if (time < 10){
                g2d.drawString("0" + time + ":00", 20, increment * time + 10);
            }
            else{
                g2d.drawString(time + ":00", 20, increment * time + 10);
            }

        }
    }
}