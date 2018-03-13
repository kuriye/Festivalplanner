package maplogic;

import javax.json.JsonArray;
import javax.json.JsonObject;

public class TiledTarget
{
    private int height;
    private int width;
    private int x;
    private int y;

    public TiledTarget(JsonObject target)
    {
        JsonArray objects  = target.getJsonArray("objects");
        height = target.getInt("height");
        width = target.getInt("width");
        x = target.getInt("x");
        y = target.getInt("y");
    }
}
