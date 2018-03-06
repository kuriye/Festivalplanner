package maplogic;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class Test extends JPanel implements MouseListener, MouseMotionListener, MouseWheelListener, ActionListener {
    private Camera camera;
    private static TiledMap test;
    private boolean scrolled = false;
    private int amountScrolled = 0;
    private int yPositionScroll = 0;
    private ArrayList<Visitor> visitors;

    public static void main(String[] args) {
        test = new TiledMap("/dikkefix.json");
        JFrame frame = new JFrame("Simulatie");
        frame.setPreferredSize(new Dimension(800, 800));
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setContentPane(new Test());
        frame.setVisible(true);
    }

    public Test()
    {
        visitors = new ArrayList<>();

        while(visitors.size() < 20)
        {
            Visitor visitor = new Visitor();
            if(!visitor.hasCollision(visitors))
                visitors.add(visitor);
        }

        Timer t = new Timer(1000/60,this);
        t.start();

        addMouseListener(this);
        addMouseMotionListener(this);


        addMouseWheelListener(this);

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        //Shape shape = new Rectangle2D.Double(0,0,getWidth(),getHeight());
        //g2d.draw(shape);
        //g2d.setClip(shape);

        test.draw(g2d);

        //Clipping voor anti-lag
        Shape screen = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setClip(screen);

        //Camera movement registeren
        if (camera == null)
            camera = new Camera(new Point2D.Double(0, 0), 0.5f);
        test.draw(g2d, camera.getTransform());

//        for(Block block : blocks) {
//            block.draw(g2d);
//        }
        for(Visitor visitor: visitors)
        {
            visitor.draw(g2d);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for(Visitor visitor: visitors)
        {
            visitor.update(visitors);
        }

        repaint();

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if(SwingUtilities.isRightMouseButton(e)) {
            Point2D position = e.getPoint();
            camera.setTarget(position);
            repaint();
        }

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        for(Visitor visitor: visitors)
        {
            visitor.setTarget(e.getPoint());
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrolled = true;
        amountScrolled = e.getWheelRotation();
        repaint();
    }

    protected void ifScrolled(Graphics2D g2d){
        if (scrolled){
            scrolled = false;
            if (amountScrolled < 0){
                if (yPositionScroll >= 0){
                    g2d.translate(0,0);
                }
                else{
                    yPositionScroll += 25;
                    g2d.translate(0,yPositionScroll);
                }
            }
            else if(amountScrolled > 0){
                if(yPositionScroll < -1350){
                    g2d.translate(0,-1400);
                }
                else{
                    yPositionScroll -= 25;
                    g2d.translate(0,yPositionScroll);
                }
            }
        }
    }

}
