package db.dao.oracle;

import db.dao.OracleDAOFactory;
import db.dao.OrderDAO;
import db.entities.Dish;
import db.entities.Order;
import db.entities.User;
import model.Date;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
public class OracleOrderDAO implements OrderDAO {

    @Override
    public Order selectOrder(User user, Date date) throws SQLException {
        Order order;
        List<Dish> dishesList = new LinkedList<>();
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "SELECT * FROM\n" +
                                     "  BREAKFAST_ORDER\n" +
                                     "  JOIN USR ON BREAKFAST_ORDER.USER_EMAIL_FK = USR.EMAIL\n" +
                                     "  JOIN DISH ON BREAKFAST_ORDER.DISH_NAME_FK = DISH.DISH_NAME\n" +
                                     "WHERE\n" +
                                     "  EMAIL = ?\n" +
                                     "  AND ORDER_DATE = ?"
                     )
        ) {

            ps.setString(1, user.getEmail());
            ps.setString(2, date.toString());
            String test = date.toString();
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                dishesList.add(
                        new Dish(rs.getString("DISH_NAME"),
                                rs.getString("DESCRIPTION"),
                                rs.getString("PICTURE_URL"))
                );
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        order = new Order(date, user);
        order.add(dishesList);
        return order;
    }

    @Override
    public List<Order> selectOrders(Date date) throws SQLException {
        return null;
    }

    @Override
    public List<Order> selectOrders(User user) throws SQLException {
        return null;
    }

    @Override
    public int insertOrder(Order order) throws SQLException {
        int i = 0;
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "INSERT INTO BREAKFAST_ORDER(" +
                                     "USER_EMAIL_FK, DISH_NAME_FK, ORDER_DATE)" +
                                     " VALUES(?,?,?)"
                     )
        ) {
            User user = order.getUser();
            Date date = order.getDate();
            List<Dish> dishesList= order.getDishesList();
            for (Dish dish: dishesList) {
                ps.setString(1, user.getEmail());
                ps.setString(2, dish.getName());
                //ps.setString(3, date.toString());
                ps.setString(3, order.getDate().toString());
                i += ps.executeUpdate();
            }
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return i;
    }

    @Override
    public boolean insertDishInOrder(Order order, Dish dish) throws SQLException{
        int i = 0;
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "INSERT INTO BREAKFAST_ORDER(" +
                                     "USER_EMAIL_FK, DISH_NAME_FK, ORDER_DATE)" +
                                     " VALUES(?,?,?)"
                     )
        ) {
            User user = order.getUser();
            Date date = order.getDate();
            ps.setString(1, user.getEmail());
            ps.setString(2, dish.getName());
            ps.setString(3, order.getDate().toString());
            i += ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return i > 0;
    }

    @Override
    public boolean deleteDishFromOrder(Order order, Dish dish) throws SQLException {
        int i = 0;
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "DELETE FROM BREAKFAST_ORDER WHERE " +
                                     "USER_EMAIL_FK = ? " +
                                     "AND DISH_NAME_FK = ?" +
                                     "AND ORDER_DATE = ?"
                     )
        ) {
            User user = order.getUser();
            Date date = order.getDate();
            ps.setString(1, user.getEmail());
            ps.setString(2, dish.getName());
            ps.setString(3, order.getDate().toString());
            i += ps.executeUpdate();
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return i > 0;
    }

    @Override
    public boolean updateOrder(Order order) throws SQLException {
        return false;
    }
}
