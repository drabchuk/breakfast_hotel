package db.entities;

import db.dao.DAOSingleton;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
@ManagedBean(name = "menu")
@ApplicationScoped
public class Menu {

    private List<Dish> dishes;

    public Menu() {
        try {
            dishes = DAOSingleton.getInstance().getMenuDAO().getDishesList();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Dish> getDishes() {
        return dishes;
    }

    public void setDishes(List<Dish> dishes) {
        this.dishes = dishes;
    }

    public void add(Dish dish) {
        this.dishes.add(dish);
    }

}
