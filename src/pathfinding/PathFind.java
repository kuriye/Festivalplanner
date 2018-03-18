package pathfinding;

import maplogic.CollisionTile;
import maplogic.TiledTarget;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

public class PathFind {

    private HashMap<Point2D, Integer> visited = new HashMap<>();
    private HashMap<Point2D,Integer> unvisited = new HashMap<>();
    private Queue<Point2D> neighbours;
    private Point2D startingTile;
    private ArrayList<Point2D> currentTiles = new ArrayList<>();
    private Integer[][] offsets = {{1,0}, {0,1},{-1,0},{0,-1}};
    private ArrayList<CollisionTile> collisionTiles;
    private int index = 0;
    public PathFind(TiledTarget tiledTarget, ArrayList<CollisionTile> collisionTiles){
        for(int x = 0; x < 128; x++ ){
            for(int y = 0; y < 122; y++){
                unvisited.put(new Point2D.Double(x,y),-1);
            }
        }
        this.collisionTiles = collisionTiles;
        startingTile = new Point2D.Double(tiledTarget.getTileNumberX() + 1,tiledTarget.getTileNumberY() + 1 );
        currentTiles.add(startingTile);
        neighbours = new LinkedList<>();
        addNewNeighbours(); // haalt de eerste buren op.
        findPath();
    }

    public void findPath(){
       while(index < 20){
           System.out.println(index);
           for(Point2D currentTile : currentTiles ){
               visited.put(currentTile, index);
           }
           currentTiles.clear();

           for(Point2D neighBour : neighbours)
               currentTiles.add(neighBour);
           addNewNeighbours();
       }
    }

    //werkt is getest, alleen if statement !visited.containsKey is nieuwe toevoeging.
    public void addNewNeighbours(){
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

    //werkt, is getest.
    public boolean isCollisionTile(Point2D nextTile){
        boolean isCollision = false;
        for(CollisionTile collisionTile : collisionTiles){
            if(collisionTile.getTilePosition().equals(nextTile)){
                isCollision = true;
            }
        }
        return isCollision;
    }

    public void debugDraw(Graphics2D g2d, AffineTransform tx){
        g2d.setColor(Color.black);
        g2d.setTransform(tx);
        for(Point2D key : visited.keySet()){
            g2d.drawString(String.valueOf(visited.get(key)), (int)key.getX() * 32,(int)key.getY() * 32);
        }
    }
}
