package db.entities;

import model.Date;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
public class Order {

    private Date date;
    private User user;
    private List<Dish> dishesList;

    public Order(Date date, User user) {
        this.date = date;
        this.user = user;
        dishesList = new LinkedList<>();
    }

    public boolean add(Dish d) {
        if (dishesList.contains(d)) return false;
        return dishesList.add(d);
    }

    public int add(List<Dish> dishes) {
        for (Dish d: dishes) {
            this.dishesList.add(d);
        }
        return this.dishesList.size();
    }

    public boolean delete(Dish d) {
        return dishesList.remove(d);
    }

    public List<Dish> getDishesList() {
        return dishesList;
    }

    public Date getDate() {
        return date;
    }

    public User getUser() {
        return user;
    }

    public void deleteAll() {
        dishesList = new LinkedList<>();
    }

    public boolean contains(Dish dish) {
        return dishesList.contains(dish);
    }

}
