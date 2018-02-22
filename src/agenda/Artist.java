package agenda;

import util.OnlineImageGetter;

import java.io.File;

public class Artist {
/**
Name is the name of the artist
Genre is the kind of music
 **/
    private String name;
    private int popularity;
    private String genre;
    private File onlineImage;

    public Artist() {}

    public Artist(String name, int popularity, String genre) {

        this.name = name;
        this.popularity = popularity;
        this.genre = genre;
        try{
            onlineImage = new File(OnlineImageGetter.getImage(this.getName()));
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    /**
    Returns the name of the artist
     **/
    public String getName() {

        return name;
    }

    /**
    Returns the popularity of the artist
     **/
    public int getPopularity(){

        return popularity;
    }

    /**
    Returns the genre of the artist
     **/
    public String getGenre() {

        return genre;
    }

    /**
    Sets the name of the artist
     **/
    public void setName(String name) {

        this.name = name;
    }

    /**
    Sets the popularity of the artist
     **/
    public void setPopularity(int popularity) {

        this.popularity = popularity;
    }

    /**
    Sets the genre of the artist
     **/
    public void setGenre(String genre) {

        this.genre = genre;
    }

    @Override
    public String toString()
    {
        return "Artist{" +
                "name='" + name + '\'' +
                ", popularity=" + popularity +
                ", genre='" + genre + '\'' +
                '}';
    }
}
