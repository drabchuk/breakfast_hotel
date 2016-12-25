package db.dao.oracle;

import db.dao.DishDAO;
import db.dao.OracleDAOFactory;
import db.entities.Dish;
import model.ProductSalesCount;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
public class OracleDishDAO implements DishDAO {

    @Override
    public List<ProductSalesCount> getStockAccount() throws SQLException {
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {
            List<ProductSalesCount> list = new LinkedList<>();
            String query =
                    "SELECT * FROM STOCK_BALANCE";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String dishName = rs.getString("DISH_NAME_FK");
                String dishDescription = rs.getString("DESCRIPTION");
                String pictireURL = rs.getString("PICTURE_URL");
                Integer balance = rs.getInt("BALANCE");
                list.add(new ProductSalesCount(
                        new Dish(dishName, dishDescription, pictireURL),
                        balance)
                );
            }
            return list;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }

    @Override
    public List<ProductSalesCount> getTopProducts() throws SQLException {
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {
            List<ProductSalesCount> list = new LinkedList<>();
            String query =
                    "SELECT * FROM TOP_DISHES";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                String dishName = rs.getString("DISH_NAME");
                String dishDescription = rs.getString("DESCRIPTION");
                String pictireURL = rs.getString("PICTURE_URL");
                Integer total = rs.getInt("TOTAL");
                list.add(new ProductSalesCount(
                        new Dish(dishName, dishDescription, pictireURL),
                        total)
                );
            }
            return list;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }
}
