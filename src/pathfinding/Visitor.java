package pathfinding;

import agenda.Act;
import visitorInformation.RandomColour;
import visitorInformation.RandomNameGenerator;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.ExecutionException;

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
    private AffineTransform tx;
    private ArrayList<Act> currentActs;
    private Point2D preTile;
    private ArrayList<Point2D> spawnPoints;
    private PathFind spawnPath;
    private int time;
    private boolean spawnIsActivited = false;

   /**
     * Creates a visitor object which will walk around the festival.
     */
    public Visitor(ArrayList<PathFind> pathFinds, ArrayList<Point2D> spawnPoints, ArrayList<Act> currentActs, PathFind spawnPath, int time)
    {

        this.currentActs = currentActs;
        this.pathFinds = pathFinds;
        this.spawnPoints = spawnPoints;
        this.spawnPath = spawnPath;
        this.time = time;
        //Spawnt buiten de map
        Random random = new Random();
        position = spawnPoints.get(random.nextInt(spawnPoints.size()));
        angle = Math.random() * 2 * Math.PI;
        speed = Math.pow(2,random.nextInt(5));
        while(speed == Math.pow(2,0) || speed == Math.pow(2,1) || speed == Math.pow(2,2) || speed == Math.pow(2,3)){
            speed = Math.pow(2,random.nextInt(6));
        }

        try {
            image = ImageIO.read(getClass().getResource("/poppetje.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        RandomColour color = new RandomColour();
        image = color.colorImage(image);
        RandomNameGenerator gen = new RandomNameGenerator();
        name = gen.nameGenerator();
        setTargetPosition();
        calculateSpawnTile();
        setNextTile();
    }

    public void setTargetPosition()
    {
            if(currentActs.size() == 0){
                path = pathFinds.get(0);
                tilePosition = new Point2D.Double(Math.ceil(position.getX() / 32), Math.ceil(position.getY()/32));
            }
            else{
                try{
                    int currentRange = 1;
                    HashMap<int[],Act> ranges = new HashMap<>();
                    for(Act currentAct : currentActs){
                        int[] range = new int[2];
                        range[0] = currentRange;
                        range[1] = currentAct.getPopularity() + currentRange;
                        currentRange = currentRange + currentAct.getPopularity();
                        ranges.put(range, currentAct);
                    }
                    Random random = new Random();
                    int value = random.nextInt(currentRange);
                    int[] keyRange = new int[2];
                    keyRange[0] = 0;
                    keyRange[1] = 1;
                    for(int[] range : ranges.keySet()){
                        int begin = range[0];
                        int end = range[1];
                        if(value >= begin && value <= end){
                            keyRange = range;
                            break;
                        }
                    }
                    Act followAct = ranges.get(keyRange);
                    PathFind rightPath = pathFinds.get(0);
                    for(PathFind pathFind : pathFinds){
                        if(pathFind.getStage().getName().hashCode() == followAct.getStage().getName().hashCode()) {
                            rightPath = pathFind;
                            break;
                        }
                    }
                    path = rightPath;
                    targetPosition = path.getStartingTile();
                    calculateSpawnTile();
                    speed = Math.pow(2,random.nextInt(5));
                    while(speed == Math.pow(2,0) || speed == Math.pow(2,1) || speed == Math.pow(2,2) || speed == Math.pow(2,3)){
                        speed = Math.pow(2,random.nextInt(6));
                    }
                }
                catch (Exception e){
                    path = pathFinds.get(0);
                    calculateSpawnTile();
                }
            }
     }

    private void calculateSpawnTile()
    {
        Point2D tileVisitor = new Point2D.Double(Math.ceil(position.getX() / 32), Math.ceil(position.getY()/32));
        if(path.getVisited().containsKey(tileVisitor))
        {
            tilePosition = tileVisitor;
            currentVisited = path.getVisited();
        }
    }

    private void setNextTile()
    {
        if(currentActs.size() == 0 && !spawnIsActivited){
            ArrayList<Point2D> values = new ArrayList<>();
            Set<Point2D> points = currentVisited.keySet();
            for (Integer[] offset: PathFind.offsets)
            {
                try
                {
                   Point2D next = new Point2D.Double(tilePosition.getX() + offset[0], tilePosition.getY() + offset[1]);
                    if(points.contains(next)){
                        values.add(next);
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                }

                if(values.size() == 0);

                else{
                    Random random = new Random();
                    if(nextTile == null){
                        nextTile = values.get(random.nextInt(values.size()));
                    }
                    else{
                        if(preTile != null){
                            Iterator<Point2D> iterator = values.iterator();
                            while (iterator.hasNext()){
                                if(iterator.next().equals(preTile)){
                                    iterator.remove();
                                }
                            }
                        }
                        if(values.size() != 0){
                            nextTile = values.get(random.nextInt(values.size()));
                        }
                    }
                }

            }
        }
        else{
            int distance;
            try{
                distance = currentVisited.get(tilePosition);
            }
            catch (Exception e){
                distance = 1;
            }
            ArrayList<Point2D> values = new ArrayList<>();
            for (Integer[] offset: PathFind.offsets)
            {
                try
                {
                    int nextDistance = currentVisited.get(new Point2D.Double(tilePosition.getX() + offset[0], tilePosition.getY() + offset[1]));
                    if (nextDistance < distance)
                    {
                        values.add(new Point2D.Double(tilePosition.getX() + offset[0], tilePosition.getY() + offset[1]));
                    }
                }
                catch (Exception e)
                {
                    //e.printStackTrace();
                }
            }

            if(values.size() == 0);

            else{
                Random random = new Random();
                nextTile = values.get(random.nextInt(values.size()));
            }
        }
    }

    /**
     * The draw method draws a visitor on the graphical user interface.
     * @param g2d is gotten from the paintComponent
     * @param tx is an affinetransform which is needed to draw an image on the gui.
     */
    public void draw(Graphics2D g2d, AffineTransform tx) {
        tx.translate(position.getX() + 16 - image.getWidth()/2, position.getY() + 12 - image.getHeight()/2);
        tx.rotate(angle);

        this.tx = tx;
        g2d.drawImage(image, tx, null);
    }

    /**
     * Updates the position of the visitors.
     */
    public void update(ArrayList<Act> currentActs, int time) {
        this.time = time;
        if(time >= 2400 && !spawnIsActivited){
            path = spawnPath;
            tilePosition = new Point2D.Double(Math.ceil(position.getX() / 32), Math.ceil(position.getY()/32));
            calculateSpawnTile();
            spawnIsActivited = true;
            setTargetPosition();
        }

        this.currentActs = currentActs;
        Point2D nextTilePosition = new Point2D.Double(nextTile.getX() * 32, nextTile.getY()* 32);
        int[] offset = new int[2];
        offset[0] = 0;
        offset[1] = 0;

        if(position.getX() < nextTilePosition.getX())
            offset[0] = (int)speed;

        else if(position.getX() > nextTilePosition.getX())
            offset[0] = -(int)speed;

        if(position.getY() < nextTilePosition.getY())
            offset[1] = (int)speed;

        else if(position.getY() > nextTilePosition.getY())
            offset[1] = -(int)speed;


        if(position.distance(nextTilePosition) <= 1){
            tilePosition = nextTile;
            setNextTile();
        }
        else{
            oldPosition = position;
            position = new Point2D.Double(position.getX() + offset[0], position.getY() + offset[1]);
        }
    }


    public Point2D getPosition() {
        return position;
    }

    public String getName() {
        return name;
    }

    public AffineTransform getTransform() { return tx;}


}
