package gui;
import agenda.Act;
import agenda.Artist;
import agenda.Program;
import agenda.Stage;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * The GraphicPanel class extends JPanel and implements a MouseWheelListener. The GraphicPanel regulates the schedule of the festival.
 * @author Jordy van Raalte
 * @author Tom Martens
 * @author Boudewijn Groeneboer
 */
public class GraphicPanel extends JPanel implements MouseWheelListener, ActionListener {

    private ArrayList<Act> allActs = new ArrayList<>();
    /**
     * The allStages attribute is a collection of all stages in the agenda.
     */
    //private ArrayList<Stage> allStages = new ArrayList<>();

    private HashSet<Stage> allStages = new HashSet();
    /**
     * The scrolled attribute will be set to true if the mousewheel is scrolled.
     */
    private boolean scrolled = false;

    /**
     * The allObjectActs ArrayList is a collection of all act object that are used in the agenda.
     */
    private ArrayList<ActObject> allObjectActs = new ArrayList<>();

    /**
     * The amountOfScrolled attribute regulates the amount scrolled.
     */
    private int amountOfScrolled = 0;

    /**
     * The yPositionScroll is the y-position which the world will be translated at.
     */
    private int yPositionScroll = 0;

    /**
     * The heightOfBox1 attribute is one of the two calculated values for the distance between the lines.
     */
    private int heightOfBox1;

    /**
     * The heightOfBox2 attribute is one of the two calculated values for the distance between the lines.
     */
    private int heightOfBox2;

    /**
     * The increment is the distance between de lines from above the below.
     */
    private int increment;

    /**
     * The heightOfString attribute is the height where the stage names gets placed at.
     */
    private int heightOfString;

    /**
     * The agendaFileLength attribute is the length of the file where all the data of the agenda is saved.
     */
    private int agendaFileLength;

    /**
     * The preStages attribute is the list of the stage that are allready drawn.
     */
    private ArrayList<Stage> preStages = new ArrayList<>();


    /**
     * The program attribute is an attribute which loads the json file.
     */
    private Program program = new Program();

    /**
     * The GraphicPanel constructor adds all stages to the arrayList all stages, so that this class can paint all stages on the frame.
     */
    public GraphicPanel() {

        try
        {
            program = program.load();
            agendaFileLength =(int) new File("Agenda.json").length();
        } catch (IOException e1)
        {
            e1.printStackTrace();
        }

        for (int i = 0; i < program.takeGrootte(); i++)
        {
            Artist artist = new Artist(program.getActs(i).getArtist().getName(), program.getActs(i).getArtist().getPopularity() , program.getActs(i).getArtist().getGenre());
            Stage stage = new Stage(program.getActs(i).getStage().getName(), program.getActs(i).getStage().getCapacity(), program.getActs(i).getStage().getLength(), program.getActs(i).getStage().getWidth());
            allActs.add(new Act(artist, stage, program.getActs(i).getStartTime(), program.getActs(i).getEndTime(), program.getActs(i).getPopularity()));
        }
        for (Act act : allActs){
            allStages.add(act.getStage());
        }
        //System.out.println(allStages);

        Timer timer = new Timer(3000,this);
        timer.start();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.addMouseWheelListener(this);
        Graphics2D g2d = (Graphics2D)g;

        preStages.clear();
        calculateAllValues();
        ifScrolled(g2d);
        paintTimer(g2d);
        paintStages(g2d);
        paintLines(g2d);
        paintActs(g2d);
    }

    /**
     * The paintTimer method paints all the times when a artist can preform an act. It also paints the rectangle around the times.
     * @param g2d is gotten from the paintComponent which paints on the panel.
     */
    protected void paintTimer(Graphics2D g2d){
        for (int time = 0; time < 25; time++){
            if (time < 10){
                g2d.setFont(new JLabel().getFont().deriveFont(16f));
                g2d.drawString("0" + time + ":00",20 ,getHeight()/10 + getHeight()/20 * time * 2 + 20);
            }
            else{
                g2d.drawString(time + ":00", 20, getHeight()/10 + getHeight()/20 * time * 2 + 20);
            }
        }
        g2d.setStroke(new BasicStroke(5));
        Rectangle2D rectangle2D = new Rectangle2D.Double(0,getHeight()/10,getWidth()/6, getHeight() + 1340 - getHeight()/10);
        g2d.draw(rectangle2D);
    }

    /**
     * The paintStages method paints all stage names and paints a rectangle around those names.
     * @param g2d is gotten from the paintComponent which paints on the panel.
     */
    protected void paintStages(Graphics2D g2d){
        boolean allReadyWritten = false;
        g2d.setStroke(new BasicStroke(5));
        int index = 1;
        for (Stage stage : allStages){
            if (index <= allStages.size())
                for (Stage preStage : preStages){
                    if (preStage.getName().equals(stage.getName())){
                        allReadyWritten = true;
                    }
                }
                if (!allReadyWritten){
                    preStages.add(stage);
                    g2d.drawString(stage.getName(),(increment* index) + increment/2 - stage.getName().length() * 4, heightOfString);
                    index++;
                }
            allReadyWritten = false;
            }
        Rectangle2D rectangle2D = new Rectangle2D.Double(increment,0,getWidth(),heightOfBox2);
        g2d.draw(rectangle2D);
    }

    /**
     * The paintLines method paints all lines that is needed for the graphicPanel.
     * @param g2d is gotten from the paintComponent which paints on the panel.
     */
    protected void paintLines(Graphics2D g2d){
        for (int i = 0; i < 5; i++){
            g2d.draw(new Line2D.Double(increment + increment* (i + 1), heightOfBox2, increment + increment * (i + 1), getHeight() + 1340));
        }
        g2d.setStroke(new BasicStroke(1));
        for (int i = 0; i < 49; i++){
            if (i % 2 == 0){
                g2d.setStroke(new BasicStroke(5));
                g2d.draw(new Line2D.Double(0,heightOfBox2 + heightOfBox1 * i,getWidth(),heightOfBox2 + heightOfBox1 * i));
                g2d.setStroke(new BasicStroke(1));
            }
            else{
                g2d.draw(new Line2D.Double(0,heightOfBox2 + heightOfBox1 * i,getWidth(),heightOfBox2 + heightOfBox1 * i));
            }
        }
        g2d.setStroke(new BasicStroke(5));
        g2d.draw(new Line2D.Double(increment,getHeight() + 1340, getWidth(), getHeight() + 1340));


        for (int index = 0; index < 5; index++){
            g2d.draw(new Line2D.Double(increment + increment* index, 0, increment + increment * index, heightOfBox2));
        }
    }


    /**
     * The ifScrolled method controls if the mousewheel is scrolled and regulates the scrolling in the panel.
     * @param g2d is gotten from the paintComponent which paints on the panel.
     */
    protected void ifScrolled(Graphics2D g2d){
        if (scrolled){
            scrolled = false;
            if (amountOfScrolled < 0){
                if (yPositionScroll >= 0){
                    g2d.translate(0,0);
                }
                else{
                    yPositionScroll += 25;
                    g2d.translate(0,yPositionScroll);
                }
            }
            else if(amountOfScrolled > 0){
                if(yPositionScroll < -1350){
                    g2d.translate(0,-1400);
                }
                else{
                    yPositionScroll -= 25;
                    g2d.translate(0,yPositionScroll);
                }
            }
        }
    }


    /**
     * The paintsActs method paints all rectangles where the details of the act is shown off.
     * @param g2d is gotten from the paintComponent which paints on the panel.
     */
    protected void paintActs(Graphics2D g2d){
        for (Act act : allActs){
            int startTime = act.getStartTime(); //1000
            int endTime = act.getEndTime(); //1200

            double fullStartTime = startTime;
            double fullEndTime = endTime;
            double partStartTime = 0;
            double partEndTime = 0;


            //calculates the minutes if the start time got parts of hours.
            if(startTime % 100 != 0){
                partStartTime = startTime;
                for(int i = 100; i < 2500; i+= 100){
                    if (partStartTime - i <= 60){
                        fullStartTime = i;
                        partStartTime = partStartTime - i;
                        break;
                    }
                }
            }
             //calculates the minutes if the end time got parts of hours.

            if(endTime % 100 != 0){
                partEndTime = endTime;
                for(int i = 100; i < 2500; i+= 100){
                    if (partEndTime - i <= 60){
                        fullEndTime = i;
                        partEndTime = partEndTime - i;
                        break;
                    }
                }
            }

             // checks which stage the act must be placed
            int index = 0;

            //System.out.println("painted on index: ");
            for (Stage stage : allStages){
                if (act.getStage().getName().equals(stage.getName())){
                   // System.out.println(act.getStage());
                    //System.out.println(index);
                    break;
                }
                index++;
            }

            //Calculates in which stage the act belongs to.

            int beginX = increment + increment * index;


             //calculates the amount of begin

            double amountForBeginY = 2*((fullStartTime/100) + (partStartTime/60));
            double amountOfBoxesForHeight = (fullEndTime- fullStartTime - 100)/100 + (partEndTime - partStartTime)/60;

            double beginY = heightOfBox2 + heightOfBox1 * amountForBeginY;
            double height = heightOfBox2 + heightOfBox1 * 2 * amountOfBoxesForHeight;

            ActObject actObject = new ActObject(beginX, (int)beginY, increment, (int)height,
                    act.getArtist().getName(),act.getStartTime(),act.getEndTime());
            allObjectActs.add(actObject);
            actObject.draw(g2d);
        }
    }

    /**
     * The calculateAllValues method calculates some standard values for the paintComponent.
     */
    public void calculateAllValues(){
        heightOfBox1 = getHeight()/20;
        heightOfBox2 = getHeight()/10;
        increment = getWidth()/6;
        //System.out.println(increment);
        heightOfString = getHeight()/18;
    }


    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        scrolled = true;
        amountOfScrolled = e.getWheelRotation();
        repaint();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (new File("Agenda.json").length() != agendaFileLength){
            try
            {
                program = program.load();
            } catch (IOException e1)
            {
                e1.printStackTrace();
            }
            finally {
                allActs.clear();
                allStages.clear();
                for (int i = 0; i < program.takeGrootte(); i++)
                {
                    Artist artist = new Artist(program.getActs(i).getArtist().getName(), program.getActs(i).getArtist().getPopularity() , program.getActs(i).getArtist().getGenre());
                    Stage stage = new Stage(program.getActs(i).getStage().getName(), program.getActs(i).getStage().getCapacity(), program.getActs(i).getStage().getLength(), program.getActs(i).getStage().getWidth());
                    allActs.add(new Act(artist, stage, program.getActs(i).getStartTime(), program.getActs(i).getEndTime(), program.getActs(i).getPopularity()));
                }
                agendaFileLength =(int) new File("Agenda.json").length();

                for (Act act : allActs){
                    allStages.add(act.getStage());
                }
                repaint();
            }
        }
    }
}
