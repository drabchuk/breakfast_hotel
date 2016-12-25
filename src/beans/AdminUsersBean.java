package beans;

import db.dao.DAOSingleton;
import db.dao.UserDAO;
import db.entities.User;
import model.UserRole;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 25.12.2016.
 */
@ManagedBean(name = "adminUsers")
@RequestScoped
public class AdminUsersBean {

    List<User> users;
    UserDAO userDAO;

    public AdminUsersBean() {
        userDAO = DAOSingleton.getInstance().getUserDAO();
        try {
            this.users = userDAO.selectUsers();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean setRole(User user) {
        try {
            return userDAO.updateUserRole(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteUser(User user) {
        try {
            return userDAO.deleteUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<User> getUsers() {
        return users;
    }
}
