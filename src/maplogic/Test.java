package maplogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Test extends JPanel implements ActionListener {
    private Camera camera;
    private static TiledMap test;
    private boolean scrolled = false;
    private int amountScrolled = 0;
    private int yPositionScroll = 0;
    private ArrayList<Visitor> visitors;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulatie");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new Test());
        frame.setVisible(true);
    }

    public Test() {
        test = new TiledMap("/dikkefix.json");
        visitors = new ArrayList<>();

        while (visitors.size() < 20) {
            Visitor visitor = new Visitor();
            if (!visitor.hasCollision(visitors))
                visitors.add(visitor);
        }

        Timer t = new Timer(1000 / 60, this);
        t.start();

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Clipping voor anti-lag
        Shape screen = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setClip(screen);

        //Camera movement registeren
        if (camera == null)
            camera = new Camera(this);
        test.draw(g2d, camera.getTransform(getHeight(),getWidth()));

        for (Visitor visitor : visitors) {
            visitor.draw(g2d, camera.getTransform(getHeight(),getWidth()));
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Visitor visitor : visitors) {
            visitor.update(visitors);
        }

        repaint();

    }
}

