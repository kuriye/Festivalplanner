package maplogic;

import javax.swing.*;
import java.awt.event.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera implements MouseListener, MouseMotionListener, MouseWheelListener, KeyListener{
    private Point2D centerPoint;
    private double zoom = 1;
    private float rotation = 0f;
    private Point2D currentPosScreen;
    private int mouseClickX;
    private int mouseClickY;

    private Point2D lastMousePos = new Point2D.Double(-1400,-1000);
    private JPanel panel;

    Camera(JPanel panel) {
        this.panel = panel;
        panel.addKeyListener(this);
        panel.addMouseListener(this);
        panel.addMouseMotionListener(this);
        panel.addMouseWheelListener(this);
        panel.requestFocus();
        centerPoint = new Point2D.Double(-1400,-1000);
        currentPosScreen = panel.getMousePosition();
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(panel.getWidth()/2, panel.getHeight()/2);
        tx.scale(zoom, zoom);
        tx.translate(centerPoint.getX(), centerPoint.getY());
        System.out.println(tx);
        tx.rotate(rotation);
        return tx;

    }


    @Override
    public void mouseClicked(MouseEvent e) {
        if(SwingUtilities.isLeftMouseButton(e) && e.isControlDown()) {
            mouseClickX = e.getX();
            mouseClickY = e.getY();
        }
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
//        if (SwingUtilities.isLeftMouseButton(e) && !e.isControlDown()) {
//            if (centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom <= -2500 || centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom >= -700) {
//                centerPoint = new Point2D.Double(
//                        (centerPoint.getX()),
//                        (centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom)
//                );
//            }
//            if (centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom <= -2500 || centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom >= -700) {
//                centerPoint = new Point2D.Double(
//                        (centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom),
//                        (centerPoint.getY())
//                );
//                lastMousePos = e.getPoint();
//                panel.repaint();
//            } else {
                centerPoint = new Point2D.Double(
                        (centerPoint.getX() - (lastMousePos.getX() - e.getX()) / zoom),
                        (centerPoint.getY() - (lastMousePos.getY() - e.getY()) / zoom)
                );
                lastMousePos = e.getPoint();
                panel.repaint();
           // }
           // System.out.println(centerPoint);
        }


    @Override
    public void mouseMoved(MouseEvent e) {

    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        if (0.24 < (zoom* (1 - e.getPreciseWheelRotation()/25.0f))) {
            zoom *= (1 - e.getPreciseWheelRotation() / 25.0f);
        }
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

    public int getMouseClickX() {
        return mouseClickX;
    }

    public int getMouseClickY() {
        return mouseClickY;
    }


    public double getZoom() {
        return zoom;
    }
}
