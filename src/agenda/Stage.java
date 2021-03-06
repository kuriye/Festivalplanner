package agenda;

/**
 * The podium class represents one podium in the simulation.
 */
public class Stage {
    /**
     * The name attribute is the name of the podium.
     */
    private String name;

    /**
     * The capacity attribute is how many NPC's can stand around the podium.
     */
    private int capacity;

    /**
     * The length attribute is how long the podium is.
     */
    private int length;

    /**
     * The width attribute is how width the podium is.
     */
    private int width;

    public Stage() {}

    /**
     *
     * @param name is the name of the podium.
     * @param capacity is the capacity of the
     * @param length is the length of the podium.
     * @param width is the width of the podium.
     */

    public Stage(String name, int capacity, int length, int width) {
        this.name = name;
        this.capacity = capacity;
        this.length = length;
        this.width = width;
    }

    /**
     * The getName method gets the name of the podium.
     * @return name of the podium.
     */
    public String getName() {
        return name;
    }

    /**
     * The getCapacity method gets the capacity of the podium
     * @return capacity of the podium.
     */
    public int getCapacity() {
        return capacity;
    }

    /**
     * The getLength method gets the length of the podium
     * @return length of the podium
     */
    public int getLength() {
        return length;
    }

    /**
     * The getWidth method gets the width of the podium.
     * @return width of the podium.
     */
    public int getWidth() {
        return width;
    }

    /**
     * The setName method sets the name of a podium.
     * @param name is the name of the podium.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * The setCapacity method sets the capacity of the podium.
     * @param capacity is the capacity of the podium.
     */
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    /**
     * The setLength method sets the length of the podium.
     * @param length is the length of the podium.
     */
    public void setLength(int length) {
        this.length = length;
    }

    /**
     * The setWidth method sets the width of the podium
     * @param width is the width of the podium
     */
    public void setWidth(int width) {
        this.width = width;
    }

    @Override
    public boolean equals(Object obj) {
        Stage stage = (Stage)obj;
        return stage.getName().equals(name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public String toString()
    {
        return "Stage{" +
                "name='" + name + '\'' +
                ", capacity=" + capacity +
                ", length=" + length +
                ", width=" + width +
                '}';
    }
}

