package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

public class ActObject {
    private RoundRectangle2D.Double rectangle;
    private Color color;
    private String artistName;
    private int startTime;
    private int endTime;


    /*
    Creates a rectangle that with the right color.
     */
    public ActObject(int x, int y, int width, int height, String artistName, int beginTime, int  endTime){
        this.rectangle = new RoundRectangle2D.Double(x + width/10/2, y ,  width - width/10, height , 10 , 10);
        this.color = new Color(153, 204 ,255);
        this.artistName = artistName;
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
        g2d.drawRoundRect(this.getX(), this.getY() , this.getWidth(), this.getHeight() , 50 , 50);


        g2d.setColor(new Color(51, 51, 51));
        g2d.setFont(new JLabel().getFont().deriveFont(18f));

        g2d.drawString("Artist: " + artistName,this.getX() + getWidth()/10, this.getY() + getHeight()/10);
        paintTime(g2d);


    }


    private void paintTime(Graphics2D g2d){
        //draws the start time of the act
        int partStartTime = 0;
        int fullStartTime = startTime;
        if (startTime < 1000){
            if(startTime % 100 != 0){
                partStartTime = startTime;
                for(int i = 100; i < 1000; i+= 100){
                    if (partStartTime - i <= 60 && i > 0){
                        fullStartTime = i;
                        partStartTime = partStartTime - i;
                        break;
                    }
                }
                g2d.drawString("Start Time: 0" + fullStartTime/100 + ":" + partStartTime,this.getX() + getWidth()/10, this.getY() + 2 *  getHeight()/10);
            }
            else{
                g2d.drawString("Start Time: 0" + fullStartTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 2 *  getHeight()/10);
            }
        }
        else{
            if(startTime % 100 != 0) {
                partStartTime = startTime;
                for (int i = 1000; i < 2400; i += 100) {
                    if (partStartTime - i <= 60 && i > 0) {
                        fullStartTime = i;
                        partStartTime = partStartTime - i;
                        break;
                    }
                }
                g2d.drawString("Start Time: " + fullStartTime/100 + ":" + partStartTime,this.getX() + getWidth()/10, this.getY() + 2 *  getHeight()/10);
            }
            else{
                g2d.drawString("Start Time: " + fullStartTime/100 + ":" + partStartTime + "0",this.getX() + getWidth()/10, this.getY() + 2 *  getHeight()/10);
            }
        }

        //draws the end time of the act.
        int partEndTime = 0;
        int fullEndTime = endTime;
        if (endTime < 1000){
            if(endTime % 100 != 0){
                partEndTime = endTime;
                for(int i = 100; i < 1000; i+= 100){
                    if (partEndTime - i <= 60 && i > 0){
                        fullEndTime = i;
                        partEndTime = partEndTime - i;
                        break;
                    }
                }
                g2d.drawString("End Time: 0" + fullEndTime/100 + ":" + partEndTime,this.getX() + getWidth()/10, this.getY() + 3 *  getHeight()/10);
            }
            else{
                System.out.println(partEndTime);
                g2d.drawString("End Time: 0" + fullEndTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 3 *  getHeight()/10);
            }
        }
        else{
            if(endTime % 100 != 0) {
                partEndTime = endTime;
                for (int i = 1000; i < 2400; i += 100) {
                    if (partEndTime - i <= 60 && i > 0) {
                        fullEndTime = i;
                        partEndTime = partEndTime - i;
                        break;
                    }
                }
                g2d.drawString("Start Time: " + fullEndTime/100 + ":" + partEndTime,this.getX() + getWidth()/10, this.getY() + 3 *  getHeight()/10);
            }
            else{
                g2d.drawString("Start Time: " + fullEndTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 3 *  getHeight()/10);
            }
        }
    }


}
