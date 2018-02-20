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


        setExtendedState(getExtendedState() | JFrame.MAXIMIZED_BOTH);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setContentPane(new GraphicPanel());
        setVisible(true);

    }
}
