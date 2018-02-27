package gui;
import javax.swing.*;
import java.awt.*;


public class AgendaPanel extends JPanel {

    AgendaPanel(){
        addTabbedPane();
    }

    private void addTabbedPane(){
        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Graphical Agenda", new GraphicPanel());
        tabbedPane.addTab("Table Agenda", new TablePanel());
        tabbedPane.setPreferredSize(new Dimension((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth(),900));
        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
        add(tabbedPane);
    }
}