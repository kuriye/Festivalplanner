package Gui;

import agenda.Artist;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ActObject {
    private RoundRectangle2D.Double rectangle;
    private Color color;
    private String actor;
    private int startTime;
    private int endTime;


    /*
    Creates a rectangle that with the right color.
     */
    public ActObject(int x, int y, int width, int height, String actor, int beginTime, int  endTime){
        this.rectangle = new RoundRectangle2D.Double(x, y ,  width, height , 10 , 10);
        this.color = new Color(153, 204 ,255);
        this.actor = actor;
        this.startTime = beginTime;
        this.endTime = endTime;
    }

    public int getX() {
        return (int)this.rectangle.getX();
    }

    public int getY() {
        return (int)this.rectangle.getY();
    }

    private int getWidth() {
        return (int)this.rectangle.getWidth();
    }

    private int getHeight() {
        return (int)this.rectangle.getHeight();
    }

    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.color);
        g2d.fillRoundRect(this.getX(), this.getY() ,  this.getWidth(), this.getHeight() , 50 , 50);


        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(new Color(100, 205 ,255));
        g2d.drawRoundRect(this.getX(), this.getY() ,  this.getWidth(), this.getHeight() , 50 , 50);


        g2d.setColor(new Color(51, 51, 51));
        g2d.drawString("" + actor,this.getX() + 50, this.getY() + 50);
        g2d.drawString("" + startTime, (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight())-10);
        g2d.drawString("" + endTime, (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight())-15);
    }



}
