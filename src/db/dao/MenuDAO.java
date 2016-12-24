package db.dao;

import db.entities.Dish;
import db.entities.Menu;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public interface MenuDAO {

    List<Dish> getDishesList() throws SQLException;

}
