package db.dao;

import java.io.File;
import java.io.FileInputStream;
import java.util.Properties;

/**
 * Created by Denis on 17.12.2016.
 */
public class DAOSingleton {
    private static final DAOSingleton INSTANCE = new DAOSingleton(1);
    public String DB_URL;
    public String USER;
    public String PASS;
    private UserDAO userDAO;
    private DishDAO dishDAO;
    private MenuDAO menuDAO;
    private OrderDAO orderDAO;


    private DAOSingleton(int dbName) {
        String path = new File(".").getAbsolutePath();


        DAOFactory factory = DAOFactory.getDAOFactory(dbName);
        userDAO = factory.getUserDAO();
        dishDAO = factory.getDishDAO();
        menuDAO = factory.getMenuDAO();
        orderDAO = factory.getOrderDAO();
        try {
            String fileName = "db.properties";
            ClassLoader classLoader = this.getClass().getClassLoader();
            File file = new File(classLoader.getResource(fileName).getFile());
            System.out.println("File Found : " + file.exists());
            FileInputStream input = new FileInputStream(file);
            Properties prop = new Properties();
            prop.load(input);
            DB_URL = prop.getProperty("db_url");
            USER = prop.getProperty("db_user");
            PASS = prop.getProperty("db_pass");
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }

    public static DAOSingleton getInstance() {
        return INSTANCE;
    }

    public UserDAO getUserDAO() {
        return userDAO;
    }

    public DishDAO getDishDAO() {
        return dishDAO;
    }

    public MenuDAO getMenuDAO() {
        return menuDAO;
    }

    public OrderDAO getOrderDAO() {
        return orderDAO;
    }

}
