package pathfinding;

import maplogic.SpawnPoint;
import visitorInformation.RandomColour;
import visitorInformation.RandomNameGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

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
    private String name;
    private Point2D oldPosition;

   /**
     * Creates a visitor object which will walk around the festival.
     */
    public Visitor(ArrayList<PathFind> pathFinds, ArrayList<Point2D> spawnPoints)
    {
        this.pathFinds = pathFinds;
        setTargetPosition();

        //Spawnt buiten de map
        Random random = new Random();
        position = spawnPoints.get(random.nextInt(spawnPoints.size()));
        angle = Math.random() * 2 * Math.PI;
        speed = Math.pow(2,random.nextInt(3));
        try {
            image = ImageIO.read(getClass().getResource("/poppetje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        RandomColour color = new RandomColour();
        image = color.colorImage(image);
        RandomNameGenerator gen = new RandomNameGenerator();
        name = gen.nameGenerator();

        calculateSpawnTile();
        setNextTile();
    }

    public void setTargetPosition()
    {
        Random random = new Random();
        path = pathFinds.get(random.nextInt(pathFinds.size()));
        targetPosition = path.getStartingTile();
     }

    public void calculateSpawnTile()
    {
        Point2D tileVisitor = new Point2D.Double( Math.ceil(position.getX() / 32), Math.ceil(position.getY()/32));
        if(path.getVisited().containsKey(tileVisitor))
        {
            tilePosition = tileVisitor;
            currentVisited = path.getVisited();
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
                }
            }
            catch (Exception e)
            {
                //e.printStackTrace();
            }
        }
    }

    /**
     * The draw method draws a visitor on the graphical user interface.
     * @param g2d is gotten from the paintComponent
     * @param tx is an affinetransform which is needed to draw an image on the gui.
     */
    public void draw(Graphics2D g2d, AffineTransform tx) {
        tx.translate(position.getX() + 16 - image.getWidth()/2, position.getY() + 16 - image.getHeight()/2);
        tx.rotate(angle);
        g2d.drawImage(image, tx, null);
    }

    /**
     * Updates the position of the visitors.
     */
    public void update() {
        Point2D nextTilePosition = new Point2D.Double(nextTile.getX() * 32, nextTile.getY()* 32);
        int[] offset = new int[2];

        if(position.getX() < nextTilePosition.getX()){
            offset[0] = (int)speed;
        }
        else if(position.getX() > nextTilePosition.getX()){
            offset[0] = -(int)speed;
        }
        else{
            offset[0] = 0;
        }

        if(position.getY() < nextTilePosition.getY()){
            offset[1] = (int)speed;
        }
        else if(position.getY() > nextTilePosition.getY()){
            offset[1] = -(int)speed;
        }
        else{
            offset[1] = 0;
        }

        if(position.distance(nextTilePosition) <= 1){
            tilePosition = nextTile;
            setNextTile();

        }
        else{
            oldPosition = position;
            position = new Point2D.Double(position.getX() + offset[0], position.getY() + offset[1]);
        }
//        if (position.distance(oldPosition) == 0)
//        {
//            System.out.println("test");
//            angle += 1;
//        }

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
