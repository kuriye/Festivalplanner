package maplogic;

import agenda.Act;
import agenda.Artist;
import agenda.Program;
import agenda.Stage;
import gui.TimePanel;
import pathfinding.PathFind;
import pathfinding.Visitor;
import visitorInformation.VisitorLayer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Random;

public class Map extends JPanel implements ActionListener {
    private Camera camera;
    private static TiledMap tiledMap;
    private ArrayList<Visitor> visitors;
    private ArrayList<CollisionTile> collisionTiles;
    private ArrayList<TiledTarget> targets;
    private ArrayList<PathFind> pathFinds = new ArrayList<>();
    private ArrayList<Act> allActs = new ArrayList<>();
    private ArrayList<Act> currentActs = new ArrayList<>();
    private ArrayList<Act> preActs = new ArrayList<>();
    private Program program = new Program();
    private VisitorLayer visitorLayer;
    private Timer timer1;
    private Timer timer2;
    private DarknessControl darkness;
    private int darknessValue = 100;
    private int agendaFileLength;
    private int time = 0;
    private TimePanel timePanel;
    int current = 1;
    private PathFind spawnPathfind;

    public Map(TimePanel timePanel) {
        this.timePanel = timePanel;
        loadProgram();
        agendaFileLength = (int) new File("Agenda.json").length();
        tiledMap = new TiledMap("/Map.json", allActs);
        collisionTiles = tiledMap.getCollisionTiles();
        visitors = new ArrayList<>();
        targets = tiledMap.getTargets();
        visitorLayer = new VisitorLayer();
        darkness = new DarknessControl();
        for (TiledTarget target : targets) {
            pathFinds.add(new PathFind(target, collisionTiles));
        }
        spawnPathfind = new PathFind(tiledMap.getSpawnTarget(), collisionTiles);

        timer1 = new Timer(1000 / 60, this);
        timer2 = new Timer(2000, this);
        timer2.start();
    }

    public void simulationTimer(int i) {
        if (i == 1) {
            timer1.start();
            timer2.stop();
        } else if (i == 2) {
            timer1.stop();
        }
    }

    private void loadProgram() {
        try {
            program = program.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < program.takeGrootte(); i++) {
            Artist artist = new Artist(program.getActs(i).getArtist().getName(), program.getActs(i).getArtist().getPopularity(), program.getActs(i).getArtist().getGenre());
            Stage stage = new Stage(program.getActs(i).getStage().getName(), program.getActs(i).getStage().getCapacity(), program.getActs(i).getStage().getLength(), program.getActs(i).getStage().getWidth());
            allActs.add(new Act(artist, stage, program.getActs(i).getStartTime(), program.getActs(i).getEndTime(), program.getActs(i).getPopularity()));
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        setBackground(new Color(21, 108, 153));

        //Clipping voor anti-lag
        Shape screen = new Rectangle2D.Double(0, 0, getWidth(), getHeight());
        g2d.setClip(screen);

        //Camera movement registeren
        if (camera == null)
            camera = new Camera(this);
        tiledMap.debugDraw(g2d, camera.getTransform());
        for (Visitor visitor : visitors) {
            visitor.draw(g2d, camera.getTransform());
        }

        /*for (CollisionTile tile : collisionTiles) {
            tile.debugDraw(g2d, camera.getTransform());
        }*/

        /*for (TiledTarget target : targets) {
            target.debugDraw(g2d, camera.getTransform());
        }*/
        tiledMap.drawHouse(g2d, camera.getTransform());
        darknessController(g2d);
        visitorLayer.drawVisitorInformation(g2d, visitors, camera);


        pathFinds.get(0).debugDraw(g2d, camera.getTransform());
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(timer2.isRunning())
            reloadProgram();
        else{
            time = timePanel.getIntTime();
            for (Visitor visitor : visitors) {
                visitor.update(currentActs, time);
            }

            if (visitors.size() < 300)
                visitors.add(new Visitor(pathFinds, tiledMap.getSpawnPoint().getSpawnPoints(), currentActs, spawnPathfind, time));
            darknessValue += 0.1f;
            findCurrentActs();
            repaint();

        }

    }

    private void reloadProgram() {
        if (new File("Agenda.json").length() != agendaFileLength) {
            try {
                program = program.load();
            } catch (IOException e1) {
                e1.printStackTrace();
            } finally {
                allActs.clear();
                for (int i = 0; i < program.takeGrootte(); i++) {
                    Artist artist = new Artist(program.getActs(i).getArtist().getName(), program.getActs(i).getArtist().getPopularity(), program.getActs(i).getArtist().getGenre());
                    Stage stage = new Stage(program.getActs(i).getStage().getName(), program.getActs(i).getStage().getCapacity(), program.getActs(i).getStage().getLength(), program.getActs(i).getStage().getWidth());
                    allActs.add(new Act(artist, stage, program.getActs(i).getStartTime(), program.getActs(i).getEndTime(), program.getActs(i).getPopularity()));
                }
                agendaFileLength = (int) new File("Agenda.json").length();
                tiledMap = new TiledMap("/Map.json", allActs);
                targets = tiledMap.getTargets();
                pathFinds.clear();
                for (TiledTarget target : targets) {
                    pathFinds.add(new PathFind(target, collisionTiles));
                }
                findCurrentActs();
            }
        }
    }

    public void update(){
        if(!currentActs.contains(preActs)){
            for(Visitor visitor : visitors){
                visitor.setTargetPosition();
            }
        }
    }


    private void findCurrentActs(){
        current++;
        if(current % 600 == 0)
        {
            update();
        }
        preActs = currentActs;
        currentActs.clear();
        for(Act act : allActs){
            int startTime = act.getStartTime();
            int endTime = act.getEndTime();
            if(time >= startTime && time <= endTime ){
                currentActs.add(act);
            }
        }
    }

    private void darknessController(Graphics2D g2d)
    {
        int currentTime = timePanel.getIntTime();
        if(currentTime < 399)
        {
            darknessValue = 1000;
            darkness.setAlphaValue(darknessValue, g2d, getWidth(), getHeight());
        }
        if(currentTime >= 400 && currentTime < 900 && darknessValue > 1.0)
        {
            System.out.println(darknessValue);
            darknessValue -= 1;
            darkness.setAlphaValue(darknessValue, g2d, getWidth(), getHeight());
        }
        if(currentTime > 1800 && currentTime < 2400)
        {
            darknessValue += 1;
            darkness.setAlphaValue(darknessValue, g2d, getWidth(), getHeight());
        }
    }
}



