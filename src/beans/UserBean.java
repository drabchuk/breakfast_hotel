package beans;

import db.dao.DAOSingleton;
import db.dao.UserDAO;
import db.entities.User;
import model.UserRole;

import java.sql.SQLException;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;


@ManagedBean(name = "user")
@SessionScoped
public class UserBean {

    private UserDAO userDAO;
    private UserRole role = UserRole.UN_AURHORIZED;
    private User user;
    private User dbUser;

    public UserBean() {
        userDAO = DAOSingleton.getInstance().getUserDAO();
        user = new User();
    }

    public String getDbPassword() {
        return dbUser.getPass();
    }

    public String getDbEmail() {
        return dbUser.getEmail();
    }

    public String getFirstName() {
        return user.getFirstName();
    }

    public void setFirstName(String name) {
        this.user.setFirstName(name);
    }

    public String getSecondName() {
        return user.getSecondName();
    }

    public void setSecondName(String secondName) {
        user.setSecondName(secondName);
    }

    public String getEmail() {
        return user.getEmail();
    }

    public void setEmail(String email) {
        user.setEmail(email);
    }

    public String getPassword() {
        return user.getPass();
    }

    public void setPassword(String password) {
        user.setPass(password);
    }

    public User getUser() {
        return user;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String add() {
        try {
            userDAO.insertUser(user);
            this.login();
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "unsuccess";
        }
    }

    public void dbData(String email) throws SQLException {
        dbUser = userDAO.selectUserByEmail(email);
        if (dbUser == null) return;
        user.setFirstName(dbUser.getFirstName());
        user.setSecondName(dbUser.getSecondName());
        user.setRole(dbUser.getRole());
    }

    public String homeRedirect() {
        return "home";
    }

    public String login() throws SQLException {
        dbData(user.getEmail());
        if (dbUser == null) return "invalid";
        if (user.getPass().equals(dbUser.getPass())) {
            role = user.getRole();
            return "output";
        } else
            return "invalid";
    }

    public void logout() {
        FacesContext.getCurrentInstance().getExternalContext()
                .invalidateSession();
        FacesContext.getCurrentInstance()
                .getApplication().getNavigationHandler()
                .handleNavigation(FacesContext.getCurrentInstance(), null, "/login.xhtml");
    }
}
