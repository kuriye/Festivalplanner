package gui;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {
    private TimePanel timePanel = new TimePanel();;


    private JPanel masterPanel = new JPanel(new BorderLayout());

    public Mainframe(){
        super("Simulatie");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        addAgendaPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(masterPanel);
        setVisible(true);
    }

    private void addAgendaPanel(){
        masterPanel.add(new AgendaPanel(timePanel), BorderLayout.CENTER);
        masterPanel.add(timePanel, BorderLayout.SOUTH);
    }



}
