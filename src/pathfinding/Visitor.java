package pathfinding;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The class Visitor represents one visitor which is going to visit the festival.
 */
public class Visitor {
    /**
     * the position artibute is the position of the visitor;
     */
    private Point2D position;

    /**
     * The angle artibute is the angle where the visitor is going to look at.
     */
    private double angle;

    /**
     * The image artibute is the sprite of the visitor.
     */
    private BufferedImage image;

    /**
     * The speed artibute is the speed of the visitor.
     */
    private double speed;

    /**
     * The targetPosition is the position where the visitor needs to go.
     */
    private Point2D targetPosition;

    private ArrayList<PathFind> pathFinds;

    private Point2D tilePosition;

    private PathFind path;

    private Point2D nextTile;

    private HashMap<Point2D, Integer> currentVisited;

    /**
     * Creates a visitor object wich will walk around the festival.
     */
    public Visitor(ArrayList<PathFind> pathFinds)
    {
        this.pathFinds = pathFinds;
        setTargetPosition();

        //Spawnt buiten de map
        position = new Point2D.Double(796,98);
        angle = Math.random() * 2 * Math.PI;
        speed = 3 + 4 * Math.random();
        try {
            image = ImageIO.read(getClass().getResource("/poppetje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        //calculateSpawnTile();
        //setNextTile();
    }

    public void setTargetPosition()
    {
            path = pathFinds.get(0);
            targetPosition = path.getStartingTile();
     }

    public void calculateSpawnTile()
    {
        Point2D tileVisitor = new Point2D.Double( Math.ceil(position.getX() / 32), Math.ceil(position.getY()/32));
        System.out.println(position);
        System.out.println(tileVisitor);
        if(path.getVisited().containsKey(tileVisitor))
        {
            tilePosition = tileVisitor;
            currentVisited = path.getVisited();
            System.out.println("true");
        }
    }

    public void setNextTile()
    {
        int distance = currentVisited.get(tilePosition);

        for (Integer[] offset: PathFind.offsets)
        {
            try
            {
                int nextDistance = currentVisited.get(new Point2D.Double(tilePosition.getX() + offset[0], tilePosition.getY() + offset[1]));
                if (nextDistance < distance)
                {
                    nextTile = new Point2D.Double(tilePosition.getX() + offset[0], tilePosition.getY() + offset[1]);
                    System.out.println();
                }
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }

        }
    }

    /**
     * The draw method draws a visitor on the graphical user interface.
     * @param g2d is gotten from the paintComponent
     * @param tx is an affinetransform which is needed to draw an image on the gui.
     */
    public void draw(Graphics2D g2d, AffineTransform tx) {
        tx.translate(position.getX() - image.getWidth()/2, position.getY() - image.getHeight()/2);
        g2d.drawImage(image, tx, null);
    }

    /**
     * Updates the position of the visitors.
     * @param visitors checks if visitors don't collide with other visitors.
     */
    public void update(ArrayList<Visitor> visitors) {

        Point2D diff = new Point2D.Double(
                targetPosition.getX() - position.getX(),
                targetPosition.getY() - position.getY()
        );

        double targetAngle = Math.atan2(diff.getY(), diff.getX());
        double angleDiff = angle - targetAngle;


        while(angleDiff < -Math.PI)
            angleDiff += Math.PI/2;
        while(angleDiff > Math.PI)
            angleDiff -= Math.PI/2;

        if(angleDiff < 0)
            angle += 0.1;
        else if(angleDiff > 0)
            angle -= 0.1;


        Point2D lastPosition = position;
        position = new Point2D.Double(
                position.getX() + speed * Math.cos(angle),
                position.getY() + speed * Math.sin(angle));
    }

    /**
     * sets targetPosition where the visitor will be going.
     * @param targetPosition
     */
    public void setTarget(Point2D targetPosition) {
        this.targetPosition = targetPosition;
    }

    public Point2D getPosition() {
        return position;
    }
}
