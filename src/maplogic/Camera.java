package maplogic;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
    private Point2D centerPoint;
    private double zoom = 1;
    private float rotation = 0f;

    private Point2D lastMousePos;
    private JPanel panel;

    Camera(JPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
        panel.requestFocus();
        centerPoint = new Point2D.Double(-1400,-1000);
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(panel.getWidth()/2, panel.getHeight()/2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        tx.rotate(rotation);
        return tx;

    }


    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        lastMousePos = e.getPoint();
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
        if(SwingUtilities.isLeftMouseButton(e))
        {
            centerPoint = new Point2D.Double(
                    (centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom),
                    (centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom)
            );
            lastMousePos = e.getPoint();
            panel.repaint();
        }
    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        zoom *= (1 - e.getPreciseWheelRotation()/25.0f);
        panel.repaint();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        switch( keyCode ) {
            case KeyEvent.VK_LEFT:
                rotation-=0.01f;
                break;
            case KeyEvent.VK_RIGHT :
                rotation+=0.01f;
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
