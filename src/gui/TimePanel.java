package gui;

import javax.swing.*;
import java.awt.*;


public class TimePanel extends JPanel  {
    private JButton start;
    private JButton pause;
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
        add(start);
        add(pause);
        start.addActionListener(e -> {
            startPressed = true;
        });
        pause.addActionListener(e -> {
            pausePressed = true;
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
}
