package agenda;

/**
 * Class takes the artist and podium and puts them together to an Act. Requires startTime, endTime and popularity input.
 */
public class Act {

    private Artist artist;
    private Podium podium;
    private int startTime;
    private int endTime;
    private int popularity;

    public Act(Artist artist, Podium podium, int startTime, int endTime, int popularity) {
        this.artist = artist;
        this.podium = podium;
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
     * Returns the Podium where the current act takes place.
     * @return podium
     */
    public Podium getPodium() {
        return podium;
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
     * Sets the Podium of the current act.
     * @param podium defines the podium.
     */
    public void setPodium(Podium podium) {
        this.podium = podium;
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
                ", podium=" + podium +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", popularity=" + popularity +
                '}';
    }
}
