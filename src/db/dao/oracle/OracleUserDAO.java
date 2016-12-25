package db.dao.oracle;

import model.UserRole;
import db.dao.OracleDAOFactory;
import db.dao.UserDAO;
import db.entities.User;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

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
                    "SELECT EMAIL, PASS, FIRST_NAME, SECOND_NAME, USER_ROLE FROM USR WHERE EMAIL = '"
                            + email + "'";
            rs = statement.executeQuery(query);
            if (!rs.next()) return null;
            user.setEmail(rs.getString("EMAIL"));
            user.setPass(rs.getString("PASS"));
            user.setFirstName(rs.getString("FIRST_NAME"));
            user.setSecondName(rs.getString("SECOND_NAME"));
            user.setRole(UserRole.values()[rs.getInt("USER_ROLE")]);
            return user;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }

    @Override
    public List<User> selectUsers() throws SQLException {
        List<User> users = new LinkedList<>();
        ResultSet rs = null;
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {

            String query = "SELECT * FROM USR";
            rs = statement.executeQuery(query);
            while (rs.next()) {
                User user = new User();
                user.setEmail(rs.getString("EMAIL"));
                user.setPass(rs.getString("PASS"));
                user.setFirstName(rs.getString("FIRST_NAME"));
                user.setSecondName(rs.getString("SECOND_NAME"));
                user.setRole(UserRole.values()[rs.getInt("USER_ROLE")]);
                users.add(user);
            }
            return users;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }

    @Override
    public boolean updateUserRole(User user) throws SQLException {
        try (Connection con = OracleDAOFactory.createConnection()
             ; Statement statement = con.createStatement()) {
            String query = "UPDATE USR SET USER_ROLE = " + user.getRole().ordinal() +
                    " WHERE EMAIL = '" + user.getEmail() +"'";
            int i = statement.executeUpdate(query);
            return i > 0;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }


    @Override
    public boolean deleteUser(User user) throws SQLException {
        try (Connection con = OracleDAOFactory.createConnection()
             ; CallableStatement cs = con.prepareCall("{CALL DELETE_USER(?)}")) {
            //String query = "DELETE FROM USR WHERE EMAIL = '" + user.getEmail() +"'";
            //int i = statement.executeUpdate(query);
            //return i > 0;
            cs.setString(1,user.getEmail());
            cs.execute();
            return true;
        } catch (SQLException sqle) {
            sqle.printStackTrace();
            throw new SQLException(sqle);
        }
    }
}
