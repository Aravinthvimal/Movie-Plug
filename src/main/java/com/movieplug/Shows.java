package com.movieplug;

public class Shows {

    int id;
    int MovieId;
    int TheatreId;
    String name;
    
    public int getId() {
        return id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    
    public int getMovieId() {
        return MovieId;
    }
    
    public void setMovieId(int MovieId) {
        this.MovieId = MovieId;
    }
    
    public int getTheatreId() {
        return TheatreId;
    }
    
    public void setTheatreId(int TheatreId) {
        this.TheatreId = TheatreId;
    }
    
    public String getShowTime() {
        return name;
    }
    
    public void setShowTime(String name) {
        this.name = name;
    }
}
