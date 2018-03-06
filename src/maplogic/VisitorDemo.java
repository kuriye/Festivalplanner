package maplogic;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class VisitorDemo extends JPanel
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

    ArrayList<Visitor> vistors = new ArrayList<Visitor>();

    public VisitorDemo()
    {
        while (vistors.size() < 100)
        {
            Visitor visitor = new Visitor();
            if(!visitor.hasCollision(visitors))
            {
                vistors.add(visitor);
            }
        }

    }
}
