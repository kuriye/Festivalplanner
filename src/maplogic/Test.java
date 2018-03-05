package maplogic;

import gui.TablePanel;

import javax.swing.*;
import java.awt.*;

public class Test extends JFrame{
    public static void main(String[] args) {
        TiledMap test = new TiledMap("/Layers.json");
    }

    public Test(){
        super("Simulatie");
        JPanel masterPanel = new JPanel(new BorderLayout());
        setPreferredSize(new Dimension(800,800));
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(800, 600));
        masterPanel.add(new TablePanel(), BorderLayout.NORTH);
        setContentPane(masterPanel);
        setVisible(true);
    }
}
