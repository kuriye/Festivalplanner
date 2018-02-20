package Gui;
import agenda.Stage;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseWheelEvent;
import java.awt.event.MouseWheelListener;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class GraphicPanel extends JPanel implements MouseWheelListener{
    private ArrayList<Stage> allStages = new ArrayList<>();

    private boolean scrolled = false;
    private int amountOfScrolled = 0;
    private int yPositionScroll = 0;

    public GraphicPanel(){
        allStages.add(new Stage("jordy", 5 ,5, 5));
        allStages.add(new Stage("jordyf", 5 ,5, 5));
        allStages.add(new Stage("jordyr", 5, 5 ,5));
        allStages.add(new Stage("jordyj", 5 ,5, 5));
        allStages.add(new Stage("jordyjyyy", 5 ,5, 5));
        allStages.add(new Stage("jordyjfff", 5 ,5, 5));

    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.addMouseWheelListener(this);
        Graphics2D g2d = (Graphics2D)g;

        ifScrolled(g2d);
        paintTimer(g2d);
        paintStages(g2d);
        paintLines(g2d);

    }

    protected void paintTimer(Graphics2D g2d){
        for (int time = 0; time < 25; time++){
            if (time < 10){
                g2d.setFont(new JLabel().getFont().deriveFont(16f));
                g2d.drawString("0" + time + ":00",20 ,getHeight()/10 + getHeight()/20 * time * 2 + 20);
            }
            else{
                g2d.drawString(time + ":00", 20, getHeight()/10 + getHeight()/20 * time * 2 + 20);
            }
        }
        g2d.setStroke(new BasicStroke(5));
        Rectangle2D rectangle2D = new Rectangle2D.Double(0,getHeight()/10,getWidth()/6, getHeight() + 1340 - getHeight()/10);
        g2d.draw(rectangle2D);
    }

    protected void paintStages(Graphics2D g2d){
        int index = 1;
        int increment = getWidth()/6;
        g2d.setStroke(new BasicStroke(5));
        for (Stage stage : allStages){
            g2d.drawString(stage.getName(),(increment* index) + increment/2, getHeight()/18);
            g2d.draw(new Line2D.Double(increment + increment* index, 0, increment + increment * index, getHeight()/10));
            index++;
        }
        Rectangle2D rectangle2D = new Rectangle2D.Double(increment,0,getWidth(),getHeight()/10);
        g2d.draw(rectangle2D);
    }

    protected void paintLines(Graphics2D g2d){
        int increment = getWidth()/6;
        for (int i = 0; i < 5; i++){
            g2d.draw(new Line2D.Double(increment + increment* (i + 1), getHeight()/10, increment + increment * (i + 1), getHeight() + 1340));
        }
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < 49; i++){
            if (i % 2 == 0){
                g2d.setStroke(new BasicStroke(5));
                g2d.draw(new Line2D.Double(0,getHeight()/10 + getHeight()/20 * i,getWidth(),getHeight()/10 + getHeight()/20 * i));
                g2d.setStroke(new BasicStroke(1));
            }
            else{
                g2d.draw(new Line2D.Double(0,getHeight()/10 + getHeight()/20 * i,getWidth(),getHeight()/10 + getHeight()/20 * i ));
            }
        }
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(increment,getHeight() + 1340, getWidth(), getHeight() + 1340));
    }

    protected void ifScrolled(Graphics2D g2d){
        if (scrolled){
            scrolled = false;
            if (amountOfScrolled < 0){
                if (yPositionScroll >= 0){

                }
                else{
                    yPositionScroll += 50;
                    g2d.translate(0,yPositionScroll);
                }
            }
            else if(amountOfScrolled > 0){
                if(yPositionScroll <= -1500){

                }
                else{
                    yPositionScroll -= 50;
                    g2d.translate(0,yPositionScroll);
                }
            }
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrolled = true;
        amountOfScrolled = e.getWheelRotation();
        repaint();
    }
}
