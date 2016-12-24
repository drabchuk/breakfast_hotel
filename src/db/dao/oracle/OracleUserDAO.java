package db.dao.oracle;

import db.dao.OracleDAOFactory;
import db.dao.UserDAO;
import db.entities.User;

import java.sql.*;

/**
 * Created by Denis on 16.12.2016.
 */
public class OracleUserDAO implements UserDAO {

    @Override
    public int insertUser(User user) throws SQLException{
        int i = 0;
        try (Connection con = OracleDAOFactory.createConnection()
             ; PreparedStatement ps =
                     con.prepareStatement(
                             "INSERT INTO usr(EMAIL, PASS, FIRST_NAME, SECOND_NAME) VALUES(?,?,?,?)"
                     )
            ) {
            ps.setString(1, user.getEmail());
            ps.setString(2, user.getPass());
            ps.setString(3, user.getFirstName());
            ps.setString(4, user.getSecondName());
            i = ps.executeUpdate();
            System.out.println("Data Added Successfully");
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
        return i;
    }

    @Override
    public User selectUserByEmail(String email) throws SQLException {
        if (email == null) return null;
        User user = new User();
        ResultSet rs = null;
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {

            String query =
                    "SELECT EMAIL, PASS, FIRST_NAME, SECOND_NAME FROM USR WHERE EMAIL = '"
                            + email + "'";
            rs = statement.executeQuery(query);
            if (!rs.next()) return null;
            user.setEmail(rs.getString("EMAIL"));
            user.setPass(rs.getString("PASS"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setSecondName(rs.getString("SECOND_NAME"));
            return user;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }
}
