package Gui;

import javax.swing.*;
import java.awt.*;

public class Mainframe extends JFrame {



    private JPanel masterPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

    public Mainframe(){
        super("Simulatie");
        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        addAgendaPanel();
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(masterPanel);
        setVisible(true);
    }

    public void addAgendaPanel(){
        masterPanel.add(new AgendaPanel());
    }



}
