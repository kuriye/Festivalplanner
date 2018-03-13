package maplogic;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
    Point2D centerPoint = new Point2D.Double(-1200,-200);
    double zoom = 1;
    float rotation = 0;

    Point2D lastMousePos;
    JPanel panel;

    Camera(JPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
        panel.requestFocus();
    }

    public AffineTransform getTransform(int windowWidth,int windowHeight) {
        AffineTransform tx = new AffineTransform();
        tx.translate(windowWidth/4, windowHeight/4);
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
        zoom *= (1 - e.getWheelRotation()/25.0f);
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
