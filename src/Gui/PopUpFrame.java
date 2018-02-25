package Gui;

import agenda.Act;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;


public class PopUpFrame extends JFrame {

    private Act act;
    private JPanel masterPanel = new JPanel();
    private JPanel northPanel = new JPanel();
    private JPanel southPanel = new JPanel(new GridLayout(3,2));

    public PopUpFrame(Act act){
        super(act.getArtist().getName());
        this.act = act;
        masterPanel.setLayout(new BoxLayout(masterPanel, BoxLayout.Y_AXIS));

        try{
            createNorthPanel();
        }
        catch (Exception e){
            e.printStackTrace();
            reserveNorthPanel();
        }

        createSouthPanel();
        masterPanel.add(northPanel);
        masterPanel.add(southPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setContentPane(masterPanel);
        setSize(new Dimension(400,400));
        setVisible(true);
    }

    public void createNorthPanel()throws Exception{

        //Image image = act.getArtist().getImage();
        //ImageIcon imageOfArtist = new ImageIcon(image.getScaledInstance(350,250, 1));
        //JLabel labelImage = new JLabel(imageOfArtist);
        //northPanel.add(labelImage);
    }

    public void reserveNorthPanel(){
        try{
            ImageIcon imageOfArtist = new ImageIcon(ImageIO.read(new File("Resources\\OnlineImages\\no_image_found.jpg")));
            JLabel labelImage = new JLabel(imageOfArtist);
            northPanel.add(labelImage);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public void createSouthPanel(){
        JLabel name = new JLabel("Name: " + act.getArtist().getName());
        JLabel genre = new JLabel("Genre: " + act.getArtist().getGenre());
        JLabel startTime = new JLabel("Start time: " + act.getStartTime());
        JLabel endTime = new JLabel("End time: " + act.getEndTime());
        JLabel artistPopularity = new JLabel("Artist popularity " + act.getArtist().getPopularity());
        JLabel stagePopularity = new JLabel("Stage popularity" + act.getPopularity());

        southPanel.add(name);
        southPanel.add(genre);
        southPanel.add(startTime);
        southPanel.add(endTime);
        southPanel.add(artistPopularity);
        southPanel.add(stagePopularity);
    }
}
