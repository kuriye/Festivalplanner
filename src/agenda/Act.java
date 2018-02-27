package agenda;

import java.util.Comparator;

/**
 * Class takes the artist and stage and puts them together to an Act. Requires startTime, endTime and popularity input.
 */
public class Act implements Comparable<Act> {

    private Artist artist;
    private Stage stage;
    private int startTime;
    private int endTime;
    private int popularity;

    public Act() {}

    public Act(Artist artist, Stage stage, int startTime, int endTime, int popularity) {
        this.artist = artist;
        this.stage = stage;
        this.startTime = startTime;
        this.endTime = endTime;
        this.popularity = popularity;
    }

    /**
     * Returns the Artist of the current Act.
     * @return artist
     */
    public Artist getArtist() {
        return artist;
    }

    /**
     * Returns the Stage where the current act takes place.
     * @return stage
     */
    public Stage getStage() {
        return stage;
    }

    /**
     * Returns the Starting time of the current act.
     * @return startTime
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Returns the Ending time of the current act.
     * @return endTime
     */
    public int getEndTime() {
        return endTime;
    }

    /**
     * Returns the Popularity in percentages of the current act.
     * @return popularity
     */
    public int getPopularity() {
        return popularity;
    }

    /**
     * Sets the Artist of the current act.
     * @param artist defines the artist.
     */
    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    /**
     * Sets the Stage of the current act.
     * @param stage defines the stage.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }

    /**
     * Sets the Artist of the current act.
     * @param startTime defines the Starting time.
     */
    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    /**
     * Sets the endTime of the current act.
     * @param endTime defines the Ending time.
     */
    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    /**
     * Sets the popularity of the current act.
     * @param popularity defines the popularity in percentages.
     */
    public void setPopularity(int popularity) {
        this.popularity = popularity;
    }

    @Override
    public String toString()
    {
        return "Act{" +
                "artist=" + artist +
                ", stage=" + stage +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", popularity=" + popularity +
                '}';
   }

    @Override
    public int compareTo(Act o)
    {
        return this.getStage().getName().compareTo(o.getStage().getName()) ;
    }
}
