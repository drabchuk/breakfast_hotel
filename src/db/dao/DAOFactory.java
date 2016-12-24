package db.dao;

/**
 * Created by Denis on 09.12.2016.
 */
public abstract class DAOFactory {

    public static final int ORACLE = 1;

    public abstract UserDAO getUserDAO();
    public abstract OrderDAO getOrderDAO();
    public abstract MenuDAO getMenuDAO();
    public abstract DishDAO getDishDAO();
    public abstract ProductDAO getProductDAO();
    public abstract StockDAO getStockDAO();

    public static DAOFactory getDAOFactory(int whichFactory) {
        switch (whichFactory) {
            case ORACLE :
                return new OracleDAOFactory();
            default :
                return null;
        }
    }

}
