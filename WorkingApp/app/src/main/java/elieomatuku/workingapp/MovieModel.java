package elieomatuku.workingapp;

/**
 * Created by elieomatuku on 12/18/17.
 */

import  java.util.Date;

public class MovieModel {



     private  String title;
     private  String director;
     private  String releaseDate;
     private  String producer;
     private  String plot;
     private  String character;



    public MovieModel(String title, String director, String releaseDate, String producer, String plot, String character) {
        this.title = title;
        this.director = director;
        this.releaseDate = releaseDate;
        this.producer = producer;
        this.plot = plot;
        this.character = character;
    }

    public MovieModel() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getPlot()
    {
        return plot;
    }

    public void setPlot(String plot)
    {
        this.plot = plot;
    }

    public String getCharacter()
    {
        return character;
    }

    public void setCharacter(String character)
    {
        this.character = character;
    }


}
