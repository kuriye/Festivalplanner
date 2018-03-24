package agenda;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.ArrayList;

public class Program
{
    private ArrayList <Act> AllActs = new ArrayList<>();


    /**
     *  In deze methode kan een Act object toegevoegd worden aan de AllActs ArrayList.
     *  @param artist nn artist object parameter input
     *  @param stage a stage object parameter input
     *  @param startTime the startTime variable input in int
     *  @param endTime the endTime variable input in int
     *  @param popularity the popularity variable input in int
     */
    public void addAct(Artist artist, Stage stage, int startTime, int endTime, int popularity)
    {
        AllActs.add(new Act(artist, stage, startTime, endTime, popularity));
    }

    /**
     * De methode geeft alle Act opbjecten uit de ArrayList.
     * @return Alle Act objecten uit de AllActs arraylist
     */
    public ArrayList<Act> getAllActs()
    {
        return AllActs;
    }

    /**
     * De methode geeft Act objecten uit de AllActs ArrayList
     * @param index Een objecten in de arraylist
     * @return Act Objecten
     */
    public Act getActs(int index)
    {
        return AllActs.get(index);
    }

    /**
     * De methode verwiderd een Act Object uit de Arraylist AllActs
     * @param index Een object in de ArrayList
     */
    public void removeAct(int index)
    {
        AllActs.remove(index);
    }

    /**
     *  De methode verwijderd alle Acts objecten uit de ArrayList AllActs
     */
    public void removeAllActs()
    {
        AllActs.clear();
    }

    /**
     * De methode slaat de ArrayList AllActs op in een file bestand
     */
    public void save(Program program) throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.writeValue(new FileOutputStream("Agenda.json"), program);
    }

    /**
     * De methode retuned een ArrayList AllActs uit het file bestand.
     */
    public Program load() throws IOException
    {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(new File("Agenda.json"), Program.class);
    }

   public int takeGrootte()
   {
        return AllActs.size();
   }

    @Override
    public String toString()
    {
        return "Program{" +
                "AllActs=" + AllActs +
                '}';
    }
}
