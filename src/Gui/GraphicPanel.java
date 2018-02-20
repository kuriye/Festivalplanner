package Gui;
import javax.swing.*;
import java.awt.*;

public class GraphicPanel extends JPanel{
    public GraphicPanel(){
        setLayout(new BorderLayout());
        setPreferredSize(new Dimension(800,100));
        //add(new GraphicMiddle()), BorderLayout.WEST));
    }

}
