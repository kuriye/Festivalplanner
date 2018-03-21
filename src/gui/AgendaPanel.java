package gui;
import maplogic.TestMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.sql.Time;


public class AgendaPanel extends JPanel implements  ActionListener{
    private JTabbedPane tabbedPane;
    private TestMap testMap;
    private TimePanel panel;

    AgendaPanel(TimePanel panel){
        addTabbedPane();
        this.panel = panel;
    }

    private void addTabbedPane(){
        tabbedPane = new JTabbedPane();
        testMap = new TestMap();
        tabbedPane.addTab("Graphical Agenda", new GraphicPanel());
        tabbedPane.addTab("Table Agenda", new TablePanel());
        tabbedPane.addTab("simulatie", testMap);
        tabbedPane.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),900));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane);
        Timer t = new Timer(1000 / 600, this);
        t.start();
    }

    public void actionPerformed(ActionEvent e) {
        if(panel.getPressed()){
            testMap.startSimulation();
        }
    }
}
