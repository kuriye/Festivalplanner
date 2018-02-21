package Gui;

import javax.swing.*;
import java.awt.*;

public class TestPanel extends JFrame {

    private JPanel masterPanel = new JPanel(new BorderLayout());

    public static void main(String[] args) {
        TestPanel testPanel = new TestPanel();
    }
    public TestPanel(){
        super("Simulatie");

        setPreferredSize(new Dimension(800,800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        masterPanel.add(new TablePanel(), BorderLayout.NORTH);
        setContentPane(masterPanel);
        setVisible(true);

    }
}
