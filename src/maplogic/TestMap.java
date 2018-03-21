package maplogic;

import pathfinding.PathFind;
import pathfinding.Visitor;
import visitorInformation.VisitorLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class TestMap extends JPanel implements ActionListener {
    private Camera camera;
    private static TiledMap test;
    private ArrayList<Visitor> visitors;
    private ArrayList<CollisionTile> collisionTiles;
    private ArrayList<TiledTarget> targets;
    private ArrayList<PathFind> pathFinds = new ArrayList<>();
    VisitorLayer visitorLayer;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulatie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1920, 1080));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new TestMap());
        frame.setVisible(true);
    }

    public TestMap() {
        test = new TiledMap("/Map.json");
        collisionTiles = test.getCollisionTiles();
        visitors = new ArrayList<>();
        targets = test.getTargets();
        visitorLayer = new VisitorLayer();

        for(TiledTarget target : targets){
            pathFinds.add(new PathFind(target,collisionTiles));
        }
       while (visitors.size() < 1) {
           Visitor visitor = new Visitor(pathFinds, test.getSpawnPoint().getSpawnPoints());
               visitors.add(visitor);
    }

        Timer t = new Timer(1000 / 60, this);
        t.start();

    }

    public void getJsonTarget()
    {

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(new Color(21,108,153));

        //Clipping voor anti-lag
        Shape screen = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setClip(screen);

        //Camera movement registeren
        if (camera == null)
            camera = new Camera(this);
        test.debugDraw(g2d, camera.getTransform());
        for (Visitor visitor : visitors) {
            visitor.draw(g2d, camera.getTransform());
        }

        for(CollisionTile tile : collisionTiles){
            tile.debugDraw(g2d, camera.getTransform());
        }

        for(TiledTarget target : targets){
            target.debugDraw(g2d, camera.getTransform());
        }
        test.drawHouse(g2d,camera.getTransform());

        visitorLayer.drawVisitorInformation(g2d,visitors, camera);


        pathFinds.get(0).debugDraw(g2d,camera.getTransform());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        for (Visitor visitor : visitors) {
            visitor.update();
        }

        if(visitors.size() < 500)
            visitors.add(new Visitor(pathFinds, test.getSpawnPoint().getSpawnPoints()));

        repaint();
    }
}

