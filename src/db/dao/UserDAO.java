package db.dao;

import db.entities.User;

import java.sql.SQLException;

/**
 * Created by Denis on 09.12.2016.
 */
public interface UserDAO {

    int insertUser(User user) throws SQLException;
    User selectUserByEmail(String email) throws SQLException;

}
