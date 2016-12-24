package db.dao;

import db.entities.Dish;
import db.entities.Order;
import db.entities.User;
import model.Date;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public interface OrderDAO {

    Order selectOrder(User user, Date date) throws SQLException;
    List<Order> selectOrders(Date date) throws SQLException;
    List<Order> selectOrders(User user) throws SQLException;
    int insertOrder(Order order) throws SQLException;
    boolean updateOrder(Order order) throws SQLException;
    boolean insertDishInOrder(Order order, Dish dish) throws SQLException;
    boolean deleteDishFromOrder(Order order, Dish dish) throws SQLException;
}
