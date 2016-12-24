package db.dao;

import db.dao.oracle.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Created by Denis on 09.12.2016.
 */
public class OracleDAOFactory extends DAOFactory {
    //public static final String DB_URL = "jdbc:oracle:thin:@localhost:1521/study";
    //private static final String USER = "denis";
    //private static final String USER = "hotel_breakfast";
    //private static final String PASS = "qwerty";

    public static Connection createConnection() throws SQLException{
        DAOSingleton dao = DAOSingleton.getInstance();
        return DriverManager.getConnection(dao.DB_URL, dao.USER, dao.PASS);
    }

    @Override
    public UserDAO getUserDAO() {
        return new OracleUserDAO();
    }

    @Override
    public OrderDAO getOrderDAO() {
        return new OracleOrderDAO();
    }

    @Override
    public MenuDAO getMenuDAO() {
        return new OracleMenuDAO();
    }

    @Override
    public DishDAO getDishDAO() {
        return new OracleDishDAO();
    }

    @Override
    public ProductDAO getProductDAO() {
        return new OracleProductDAO();
    }

    @Override
    public StockDAO getStockDAO() {
        return new OracleStockDAO();
    }
}
