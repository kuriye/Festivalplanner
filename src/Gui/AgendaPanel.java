package Gui;
import javax.swing.*;
import java.awt.*;


public class AgendaPanel extends JPanel {

    public AgendaPanel(){
        addTabbedPane();
    }

    public void addTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Graphical Agenda", new GraphicPanel());
        //tabbedPane.addTab("Table Agenda", new TablePanel());
        tabbedPane.setPreferredSize(new Dimension(1900,900));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane);

        //tabbedPane.setVisible(true);
    }
}
