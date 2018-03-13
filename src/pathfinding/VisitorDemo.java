package pathfinding;

import maplogic.TiledMap;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class VisitorDemo extends JPanel implements ActionListener {
    private static TiledMap tiledmap = null;
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Visitor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.setContentPane(new VisitorDemo());
        frame.setVisible(true);
    }

    ArrayList<Visitor> visitors = new ArrayList<>();

    public VisitorDemo() {

        while(visitors.size() < 1)
        {
            Visitor visitor = new Visitor();
            if(!visitor.hasCollision(visitors, tiledmap.getLayers()))
                visitors.add(visitor);
        }
        new Timer(1000/60, this).start();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        AffineTransform tx = new AffineTransform();

        for(Visitor visitor : visitors)
            visitor.draw(g2d , tx);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        for(Visitor visitor : visitors)
            visitor.update(visitors, tiledmap.getLayers());
        repaint();
    }
}
