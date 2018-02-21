package Gui;

import agenda.Artist;
import javafx.scene.shape.Shape;

import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ActObject {
    private RoundRectangle2D.Double rectangle;
    private Color color;

    private Artist artist;
    private int startTime;
    private int endTime;
    private int popularity;


    /*
    Creates a rectangle that with the right color.
     */
    public ActObject(int x, int y, int width, int height, Artist artist, int startTime, int endTime, int popularity){
    this.rectangle = new RoundRectangle2D.Double(x, y ,  width, height , 10 , 10);
    this.color = new Color(153, 204 ,255);
    this.artist = artist;
    this.startTime = startTime;
    this.endTime = endTime;
    this.popularity = popularity;

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

    public void draw(Graphics2D g2d) {
        g2d.setColor(this.color);
        g2d.fillRoundRect(this.getX(), this.getY() ,  this.getWidth(), this.getHeight() , 10 , 10);
        g2d.setColor(new Color(51, 51, 51));
    }



}
