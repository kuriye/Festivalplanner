package Gui;

import agenda.Artist;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ActObject {
    private RoundRectangle2D.Double rectangle;
    private Color color;
    private String actor;
    private String beginTime;
    private String endTIme;

    private Artist artist;
    private int startTime;
    private int endTime;
    private int popularity;


    /*
    Creates a rectangle that with the right color.
     */
    public ActObject(int x, int y, int width, int height, String actor, String beginTime, String endTime){
    this.rectangle = new RoundRectangle2D.Double(x, y ,  width, height , 10 , 10);
    this.color = new Color(153, 204 ,255);
    this.actor = actor;
    this. beginTime = beginTime;
    this.endTIme = endTime;
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
        g.setColor(this.color);
        g.fillRoundRect(this.getX(), this.getY() ,  this.getWidth(), this.getHeight() , 10 , 10);
        g.setColor(new Color(51, 51, 51));
        g.drawString("" + actor, (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight())-5);
        g.drawString("" + beginTime, (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight())-10);
        g.drawString("" + endTIme, (this.getX() + this.getWidth())/2, (this.getY() + this.getHeight())-15);
    }



}
