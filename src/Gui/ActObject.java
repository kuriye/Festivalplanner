package Gui;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.RoundRectangle2D;

/**
 * The ActObjects class is one act object on screen.
 * @author Jordy van Raalte
 * @author Boudewijn Groeneboer
 */
public class ActObject {
    /**
     * The rectangle attribute is a round rectangle shape that will be drawn.
     */
    private RoundRectangle2D.Double rectangle;

    /**
     * The color attribute is the color of the rectangle.
     */
    private Color color;

    /**
     * The artistName attribute is the name of the artist
     */
    private String artistName;

    /**
     * The startTime attribute is the start time of the program.
     */
    private int startTime;

    /**
     * The endTime attribute is the end time of the program.
     */
    private int endTime;


    /**
        Creates a rectangle that with the right color.
     */
    ActObject(int x, int y, int width, int height, String artistName, int beginTime, int  endTime){
        this.rectangle = new RoundRectangle2D.Double(x + width/20, y ,  width - width/10, height , 10 , 10);
        this.color = new Color(153, 204 ,255, 220);
        this.artistName = artistName;
        this.startTime = beginTime;
        this.endTime = endTime;
    }

    /**
     * The draw method is draws the ActObjects class on the GraphicPanel
     * @param g is gotten from the paintComponent from the GraphicPanel.
     */
    public void draw(Graphics g) {
        Graphics2D g2d = (Graphics2D)g;
        g2d.setColor(this.color);
        g2d.fillRoundRect(this.getX(), this.getY() ,  this.getWidth(), this.getHeight() , 50 , 50);


        g2d.setStroke(new BasicStroke(5));
        g2d.setColor(new Color(100, 205 ,255));
        g2d.drawRoundRect(this.getX(), this.getY() , this.getWidth(), this.getHeight() , 50 , 50);


        g2d.setColor(new Color(51, 51, 51));


        int spaceBetweenString;
        if (endTime - startTime <= 75 && endTime - startTime >= 30){
            g2d.setFont(new JLabel().getFont().deriveFont(12f));
            spaceBetweenString = getHeight()/4;
            g2d.drawString("Artist: " + artistName,this.getX() + getWidth()/10, this.getY() + spaceBetweenString);
            paintTime(g2d, spaceBetweenString);
        }
        else if(endTime - startTime < 30){
            g2d.setFont(new JLabel().getFont().deriveFont(11f));
            String information = "Click for more Information";
            g2d.drawString(information, this.getX() + getWidth()/2 - information.length()*3 , this.getY() + getHeight()/2);
        }
        else{
            g2d.setFont(new JLabel().getFont().deriveFont(18f));
            spaceBetweenString = getHeight()/4;
            g2d.drawString("Artist: " + artistName,this.getX() + getWidth()/10, this.getY() + spaceBetweenString);
            paintTime(g2d, spaceBetweenString);
        }
    }


    /**
     * Paints the start time and end time of act in the ActObject.
     * @param g2d is gotten from the paintComponent.
     * @param spaceBetweenString is the space between the strings so the strings can be seprated in heigt.
     */
    public void paintTime(Graphics2D g2d, int spaceBetweenString ){
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
                g2d.drawString("Start Time: 0" + fullStartTime/100 + ":" + partStartTime,this.getX() + getWidth()/10, this.getY() + 2 *  spaceBetweenString);
            }
            else{
                g2d.drawString("Start Time: 0" + fullStartTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 2 *  spaceBetweenString);
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
                g2d.drawString("Start Time: " + fullStartTime/100 + ":" + partStartTime,this.getX() + getWidth()/10, this.getY() + 2 *  spaceBetweenString);
            }
            else{
                g2d.drawString("Start Time: " + fullStartTime/100 + ":" + partStartTime + "0",this.getX() + getWidth()/10, this.getY() + 2 *  spaceBetweenString);
            }
        }

        //draws the end time of the act.
        int partEndTime;
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
                g2d.drawString("End Time: 0" + fullEndTime/100 + ":" + partEndTime,this.getX() + getWidth()/10, this.getY() + 3 *  spaceBetweenString);
            }
            else{
                g2d.drawString("End Time: 0" + fullEndTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 3 *  spaceBetweenString);
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
                g2d.drawString("End Time: " + fullEndTime/100 + ":" + partEndTime,this.getX() + getWidth()/10, this.getY() + 3 * spaceBetweenString);
            }
            else{
                g2d.drawString("End Time: " + fullEndTime/100 + ":" + "0" + "0",this.getX() + getWidth()/10, this.getY() + 3 *  spaceBetweenString);
            }
        }
    }

    /**
     * The getX method gets the x-position of the rectangle.
     * @return x-position of rectangle.
     */
    private int getX() {
        return (int)this.rectangle.getX();
    }

    /**
     * The getY method gets the Y-position of the rectangle.
     * @return Y-position of rectangle.
     */
    private int getY() {
        return (int)this.rectangle.getY();
    }

    /**
     * The getWidth method gets the width of the rectangle.
     * @return width of rectangle.
     */
    private int getWidth() {
        return (int)this.rectangle.getWidth();
    }

    /**
     * The getHeight method gets the heigt of the rectangle.
     * @return height of rectangle.
     */
    private int getHeight() {
        return (int)this.rectangle.getHeight();
    }


}
