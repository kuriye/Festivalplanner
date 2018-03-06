package maplogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VisitorDemo extends JPanel implements ActionListener
{
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Visitor");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setExtendedState(frame.getExtendedState() | Frame.MAXIMIZED_BOTH);
        frame.setContentPane(new VisitorDemo());
        frame.setVisible(true);
    }

    ArrayList<Visitor> visitors = new ArrayList<Visitor>();

    public VisitorDemo()
    {
        while (visitors.size() < 100)
        {
            Visitor visitor = new Visitor();
            if(!visitor.hasCollision(visitors))
            {
                visitors.add(visitor);
            }
        }

        new javax.swing.Timer(1000/120, this).start();

    }

    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for(Visitor visitor : visitors)
        {
            visitor.draw(g2d);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        for (Visitor visitor : visitors)
        {
            visitor.update(visitors);
        }

        repaint();
    }
}
