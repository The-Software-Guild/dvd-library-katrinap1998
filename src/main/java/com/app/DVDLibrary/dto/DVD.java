package com.app.DVDLibrary.dto;

public class DVD {

    private String title;
    private String director;
    private String studio;
    private String notes;
    private String releaseDate;
    private String rating;

    public DVD(String title){
        this.title = title;
    }

    // getters
    public String getTitle(){
        return title;
    }

    public String getDirector(){
        return director;
    }

    public String getStudio(){
        return studio;
    }

    public String getNotes(){
        return notes;
    }

    public String getReleaseDate(){
        return releaseDate;
    }

    public String getRating(){
        return rating;
    }

    //setters
    public void  setTitle(String title){
        this.title = title;
    }

    public void  setDirector(String director){
        this.director = director;
    }

    public void setStudio(String studio){
        this.studio = studio;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
    public void setReleaseDate(String releaseDate){
        this.releaseDate = releaseDate;
    }
    public void setRating(String rating){
        this.rating = rating;
    }

}
