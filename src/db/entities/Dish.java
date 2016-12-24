package db.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 * Created by Denis on 16.12.2016.
 */
@ManagedBean(name = "dish")
@RequestScoped
public class Dish {

    private String name;
    private String description;
    private String picture_url;

    public Dish() {
    }

    public Dish(String name, String description, String picture_url) {
        this.name = name;
        this.description = description;
        this.picture_url = picture_url;
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

    public String getPicture_url() {
        return picture_url;
    }

    public void setPicture_url(String picture_url) {
        this.picture_url = picture_url;
    }

    @Override
    public int hashCode() {
        if (name == null) return super.hashCode();
        return name.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Dish)) return false;
        if (this.hashCode() != obj.hashCode()) return false;
        return this.name.equals(((Dish) obj).getName());
    }
}
