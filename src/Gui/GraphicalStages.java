package Gui;

import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicalStages extends JPanel {

    private ArrayList<Stage> allStages = new ArrayList<>();
    public GraphicalStages(){
        setBorder(BorderFactory.createLineBorder(Color.black,3));
        allStages.add(new Stage("jordy", 5 ,5, 5));
        allStages.add(new Stage("jordyf", 5 ,5, 5));
        allStages.add(new Stage("jordyr", 5, 5 ,5));
        allStages.add(new Stage("jordyj", 5 ,5, 5));
        allStages.add(new Stage("jordyk", 5 ,5, 5));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        int increment = getWidth() / allStages.size();
        int index = 0;
        for (Stage stage : allStages){
            g2d.drawString(stage.getName(),increment *  index, 20);
            index++;
        }


    }
}
