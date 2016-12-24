package beans_old;

import beans.UserRole;
import db.dao.DAOSingleton;
import db.dao.UserDAO;
import db.entities.User;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;


@ManagedBean(name = "usero1")
@SessionScoped
public class UserBean_o1 {

    private UserDAO userDAO;
    private UserRole role = UserRole.UN_AURHORIZED;
    private String firstName;
    private String secondName;
    private String email;
    private String password;
    private String dbPassword;
    private String dbEmail;

    public UserBean_o1() {
        userDAO = DAOSingleton.getInstance().getUserDAO();
    }

    public String getDbPassword() {
        return dbPassword;
    }

    public String getDbEmail() {
        return dbEmail;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String name) {
        this.firstName = name;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }

    public String add(){
        User user = new User(email, password, firstName, secondName);
        try {
            userDAO.insertUser(user);
            return "success";
        } catch (SQLException e) {
            e.printStackTrace();
            return "unsuccess";
        }
    }

    public void dbData(String email) throws SQLException{
        User dbUser = userDAO.selectUserByEmail(email);
        if (dbUser == null) return;
        dbEmail = dbUser.getEmail();
        dbPassword = dbUser.getPass();
        firstName = dbUser.getFirstName();
        secondName = dbUser.getSecondName();
    }

    public String login() throws SQLException{
        dbData(email);
        if (email.equals(dbEmail) && password.equals(dbPassword)) {
            role = UserRole.AUTHORIZED;
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
