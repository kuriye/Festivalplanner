package maplogic;

import agenda.Act;
import agenda.Artist;
import agenda.Program;
import agenda.Stage;
import pathfinding.PathFind;
import pathfinding.Visitor;
import visitorInformation.VisitorLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.ArrayList;

public class Map extends JPanel implements ActionListener {
    private Camera camera;
    private static TiledMap test;
    private ArrayList<Visitor> visitors;
    private ArrayList<CollisionTile> collisionTiles;
    private ArrayList<TiledTarget> targets;
    private ArrayList<PathFind> pathFinds = new ArrayList<>();
    private ArrayList<Act> allActs = new ArrayList<>();
    private Program program = new Program();
    private VisitorLayer visitorLayer;
    private Timer t;

    public static void main(String[] args) {
        JFrame frame = new JFrame("Simulatie");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setMinimumSize(new Dimension(1920, 1080));
        frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
        frame.setContentPane(new Map());
        frame.setVisible(true);
    }

    public Map() {
        test = new TiledMap("/Map.json");
        collisionTiles = test.getCollisionTiles();
        visitors = new ArrayList<>();
        targets = test.getTargets();
        visitorLayer = new VisitorLayer();
        loadProgram();

        for(TiledTarget target : targets){
            pathFinds.add(new PathFind(target,collisionTiles));
        }
       while (visitors.size() < 1) {
           Visitor visitor = new Visitor(pathFinds, test.getSpawnPoint().getSpawnPoints());
               visitors.add(visitor);
        }

        t = new Timer(1000 / 60, this);
    }

    public void simulationTimer(int i) {
        if(i == 1){
            t.start();
        }
        else if( i == 2){
            t.stop();
        }

    }

    public void loadProgram()
    {
        try {
            program = program.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < program.getGrootte(); i++)
        {
            Artist artist = new Artist(program.getActs(i).getArtist().getName(), program.getActs(i).getArtist().getPopularity() , program.getActs(i).getArtist().getGenre());
            Stage stage = new Stage(program.getActs(i).getStage().getName(), program.getActs(i).getStage().getCapacity(), program.getActs(i).getStage().getLength(), program.getActs(i).getStage().getWidth());
            allActs.add(new Act(artist, stage, program.getActs(i).getStartTime(), program.getActs(i).getEndTime(), program.getActs(i).getPopularity()));
        }
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

        if(visitors.size() < 300)
            visitors.add(new Visitor(pathFinds, test.getSpawnPoint().getSpawnPoints()));

        repaint();
    }


}

