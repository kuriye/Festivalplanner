package maplogic;

import javax.swing.*;
import java.awt.*;

public class Test extends JPanel{

    private static TiledMap test;

    public static void main(String[] args) {
        test = new TiledMap("/dikkefix.json");
        JFrame frame = new JFrame("Simulatie");
        frame.setPreferredSize(new Dimension(800,800));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new Test());
        frame.setVisible(true);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;
        test.draw(g2d);
    }
}
