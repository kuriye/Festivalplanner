package pathfinding;

import maplogic.CollisionTile;
import maplogic.TiledTarget;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.*;

/**
 * The PathFind class calculates the pathfinding from a target. It keeps up the distances from the start tile to every tile the visitor can cross.
 */
public class PathFind {

    /**
     * The visited attribute keeps track of the tiles wich are already visited.
     * The position of the tile is the key, and the value of the key is the distance from the target.
     */
    private final HashMap<Point2D, Integer> visited = new HashMap<>();

    /**
     * The neighbours attribute keeps track of the "neighbours"  of the current tile.
     */
    private final Queue<Point2D> neighbours;

    /**
     * The currentTiles attribute keeps track of the current tiles where the calculation is.
     */
    private final HashSet<Point2D> currentTiles = new HashSet<>();

    /**
     * The offsets attribute is an Array with directions the current tile can go.
     */
    public static final Integer[][] offsets = {{1,0}, {0,1},{-1,0},{0,-1}};

    /**
     * The collisionTiles attribute are the tiles which contains collision. These tiles can't be passed.
     * @see CollisionTile for more info.
     */
    private final ArrayList<CollisionTile> collisionTiles;

    /**
     * The index attribute keeps track of the distance per tile from the target.
     */
    private int index = 0;

    private Point2D startingTile;

    /**
     * The PathFind constructor calculates the path from a target.
     * @param tiledTarget is the target where the calculations starts.
     *                   @see TiledTarget
     * @param collisionTiles are the tiles which contains collision.
     *                   @see CollisionTile
     */
    public PathFind(TiledTarget tiledTarget, ArrayList<CollisionTile> collisionTiles){
        for(int x = 0; x < 128; x++ ){
            for(int y = 0; y < 122; y++){
                HashMap<Point2D, Integer> unvisited = new HashMap<>();
                unvisited.put(new Point2D.Double(x,y),-1);
            }
        }
        this.collisionTiles = collisionTiles;
        startingTile = new Point2D.Double(tiledTarget.getTileNumberX() + 1, tiledTarget.getTileNumberY() + 1);
        currentTiles.add(startingTile);
        neighbours = new LinkedList<>();
        addNewNeighbours();
        findPath();
    }

    /**
     * The findPath method searches for a path. The method ends if the neighbours attribute is empty. That is when all possible tiles has been read.
     */
    private void findPath(){
       while(!neighbours.isEmpty()){
           for(Point2D currentTile : currentTiles ){
               visited.put(currentTile, index);
           }

           currentTiles.clear();

           currentTiles.addAll(neighbours);
           addNewNeighbours();
       }
    }

    /**
     * Adds new Neighbours to the Neighbours queue, so that the findPath method can go on.
     */
    private void addNewNeighbours(){
        neighbours.clear();
        for(Point2D currentTile : currentTiles){
            for(Integer[] offset : offsets){
                Point2D nextPosition = new Point2D.Double(
                        currentTile.getX() + offset[0],
                        currentTile.getY() + offset[1]
                );

                if(!isCollisionTile(nextPosition) && !visited.containsKey(nextPosition)){
                    neighbours.add(nextPosition);
                }
            }
        }
        index++;
    }

    /**
     * Checks if the tile next to a current tile is a collision tile.
     * @param nextTile is the tile that can become a current tile.
     * @return if the next tile is a collision tile.
     */
    private boolean isCollisionTile(Point2D nextTile){
        boolean isCollision = false;
        for(CollisionTile collisionTile : collisionTiles){
            if(collisionTile.getTilePosition().equals(nextTile)){
                isCollision = true;
            }
        }
        return isCollision;
    }

    /**
     * The debugdraw method draws the distance of the tiles to the target on the panel.
     * @param g2d is gotten from the paintComponent.
     * @param tx is the affineTransform gotten from the camera, so the string will be written right on the panel.
     */
    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        g2d.setColor(Color.black);
        g2d.setTransform(tx);
        for(Point2D key : visited.keySet()){
            g2d.drawString(String.valueOf(visited.get(key)), (int)key.getX() * 32,(int)key.getY() * 32);
        }
    }

    public Point2D getStartingTile()
    {
        return startingTile;
    }

    public void setStartingTile(Point2D startingTile)
    {
        this.startingTile = startingTile;
    }

    public HashMap<Point2D, Integer> getVisited()
    {
        return visited;
    }
}
