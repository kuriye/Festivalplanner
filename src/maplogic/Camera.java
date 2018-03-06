package maplogic;

import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;

public class Camera {
    private Point2D target;
    private float zoom;

    public Camera(Point2D target, float zoom) {
        this.target = target;
        this.zoom = zoom;
    }

    public Point2D getTarget() {
        return target;
    }

    public float getZoom() {
        return zoom;
    }

    public void setTarget(Point2D newTarget) {
        target = newTarget;
    }

    public void setZoom(float newZoom) {
        if(newZoom >= 0 && newZoom <= 1) {
            zoom = newZoom;
        }
    }

    public AffineTransform getTransform() {
        AffineTransform tx = new AffineTransform();
        tx.translate(target.getX(), target.getY());
        tx.scale(zoom, zoom);

        return tx;
    }
}