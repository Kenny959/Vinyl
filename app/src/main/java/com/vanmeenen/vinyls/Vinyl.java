
package com.vanmeenen.vinyls;

/**
 * Created by k.vanmeenen on 15/04/2016.
 */
public class Vinyl {
    private long id;
    private String name;
    private String song;
    private String photo;


    public Vinyl(){
    }

    public Vinyl(String name, String song, String photo){
        this.name = name;
        this.song = song;
        this.photo = photo;
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSong() {
        return song;
    }

    public void setSong(String song) {
        this.song = song;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name + song + photo;
    }


    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
