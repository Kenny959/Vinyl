
package com.vanmeenen.vinyls;

/**
 * Created by k.vanmeenen on 15/04/2016.
 */
public class Vinyl {
    private long id;
    private String name;
    private String description;

    public Vinyl(){
    }

    public Vinyl(String name, String description){
        this.name = name;
        this.description = description;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // Will be used by the ArrayAdapter in the ListView
    @Override
    public String toString() {
        return name + description;
    }
}
