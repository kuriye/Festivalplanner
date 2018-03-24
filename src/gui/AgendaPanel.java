package gui;
import maplogic.Map;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class AgendaPanel extends JPanel implements  ActionListener{
    private JTabbedPane tabbedPane;
    private Map map;
    private TimePanel panel;

    AgendaPanel(TimePanel panel){
        addTabbedPane();
        this.panel = panel;
    }

    private void addTabbedPane(){
        tabbedPane = new JTabbedPane();
        map = new Map();
        tabbedPane.addTab("Graphical Agenda", new GraphicPanel());
        tabbedPane.addTab("Table Agenda", new TablePanel());
        tabbedPane.addTab("Simulatie", map);
        tabbedPane.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),900));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane);
        Timer t = new Timer(1000 / 60, this);
        t.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(panel.getStartPressed()){
            map.simulationTimer(1);
            panel.setStartPressed(false);
        }
        else if(panel.getPausePressed()){
            map.simulationTimer(2);
            panel.setPausePressed(false);
        }

    }
}
