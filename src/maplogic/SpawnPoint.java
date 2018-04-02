package maplogic;

import javax.json.JsonObject;
import java.awt.geom.Point2D;
import java.util.ArrayList;

public class SpawnPoint {

    private int width;
    private int height;
    private Point2D startPosition;
    private int tilesHorizontalFromStart;
    private int tilesVerticalFromStart;
    public SpawnPoint(JsonObject targetObject){
        width = targetObject.getInt("width");
        height = targetObject.getInt("height");
        startPosition = new Point2D.Double(targetObject.getInt("x"), targetObject.getInt("y"));
        tilesHorizontalFromStart = width/32;
        tilesVerticalFromStart = height/32;
    }

    public ArrayList<Point2D> getSpawnPoints(){
        ArrayList<Point2D> spawnPoints = new ArrayList<>();
        for(int horizontal = 0; horizontal < tilesHorizontalFromStart ; horizontal++)
            for(int vertical = 0; vertical< tilesVerticalFromStart; vertical++)
                spawnPoints.add(new Point2D.Double(horizontal * 32 + startPosition.getX(),
                        vertical * 32 + startPosition.getY()));

        return spawnPoints;
    }
}
