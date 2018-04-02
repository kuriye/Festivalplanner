package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalTime;
import java.util.Date;


public class TimePanel extends JPanel implements ActionListener
{
    private JButton start;
    private JButton pause;
    private JLabel timeLabel;
    public LocalTime time;

    private int intTime;

    private boolean startPressed;
    private boolean pausePressed;

    public TimePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public TimePanel(){
        startPressed = false;
        pausePressed = false;
        start = new JButton("start");
        pause = new JButton("pauze");
        time = LocalTime.parse("00:00:00");
        timeLabel = new JLabel(String.valueOf(time));



        add(start);
        add(pause);
        add(timeLabel);

        Timer timer = new Timer(75,this);
        timer.setInitialDelay(1);

        start.addActionListener(e -> {
            startPressed = true;
            timer.start();
        });
        pause.addActionListener(e -> {
            pausePressed = true;
            timer.stop();
        });
    }

    public boolean getStartPressed() {
        return startPressed;
    }

    public boolean getPausePressed() {
        return pausePressed;
    }

    public void setStartPressed(boolean startPressed) {
        this.startPressed = startPressed;
    }

    public void setPausePressed(boolean pausePressed) {
        this.pausePressed = pausePressed;
    }


    @Override
    public void actionPerformed(ActionEvent e)
    {
        if(getIntTime() >= 2400){
            intTime = 2400;
        }
        else{
            timeLabel.setText(String.valueOf(time = time.plusMinutes(1)));
            intTime++;
        }
    }

    public int getIntTime() {
        int temp = (int) (intTime * 1.66);
        return temp;
    }


}
