package db.dao.oracle;

import db.dao.MenuDAO;
import db.dao.OracleDAOFactory;
import db.entities.Dish;
import db.entities.Menu;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Denis on 16.12.2016.
 */
public class OracleMenuDAO implements MenuDAO{

    @Override
    public List<Dish> getDishesList() throws SQLException {
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {
            List<Dish> list = new LinkedList<>();
            String query =
                    "SELECT DISH_NAME, DESCRIPTION, PICTURE_URL " +
                            "FROM MENU JOIN DISH ON MENU.DISH_NAME_FK = DISH.DISH_NAME";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()) {
                list.add(
                        new Dish(
                                rs.getString("DISH_NAME"),
                                rs.getString("DESCRIPTION"),
                                rs.getString("PICTURE_URL")
                        )
                );
            }
            return list;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }
}
