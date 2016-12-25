package db.dao;

import db.entities.User;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public interface UserDAO {

    int insertUser(User user) throws SQLException;
    User selectUserByEmail(String email) throws SQLException;
    List<User> selectUsers() throws SQLException;
    boolean updateUserRole(User user) throws SQLException;
    boolean deleteUser(User user) throws SQLException;

}
