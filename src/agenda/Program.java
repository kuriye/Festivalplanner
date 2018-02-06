package agenda;

import java.util.ArrayList;

public class Program
{
    ArrayList <Act> AllActs = new ArrayList<>();

    public Program()
    {
    }

    /**
     *  In deze methode kan een Act object toegevoegd worden aan de AllActs ArrayList.
     *  @param
     */
    public void addAct()
    {
        // !! Parameters van classe Act moet nog toegevoegd worden.
        AllActs.add(new Act());
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
    public void save()
    {
        //Acts worden omgezet naar bijvoorbeeld json file
    }

    /**
     * De methode retuned een ArrayList AllActs uit het file bestand.
     */
    public void load()
    {
        //return Act file
    }

}
