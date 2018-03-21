package gui;

import javax.swing.*;
import java.awt.*;


public class TimePanel extends JPanel  {
    private JButton start;
    private boolean pressed;

    public TimePanel(LayoutManager layout, boolean isDoubleBuffered) {
        super(layout, isDoubleBuffered);
    }

    public TimePanel(){
        pressed = false;
        start = new JButton("start");
        add(start);
        start.addActionListener(e -> {
            pressed = true;
        });
    }

    public boolean getPressed() {
        return pressed;
    }
}
