package beans;

import db.DatabaseConnector;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@ManagedBean(name = "user_bean")
@SessionScoped
public class UserBeanOld {

    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String dbPassword;
    private String dbEmail;
    DatabaseConnector ds;
   //DataSource ds;

    public UserBeanOld() {
            //Context ctx = new InitialContext();
            //ds = (DataSource) ctx.lookup("java:comp/env/jdbc/database");
        try {
            ds = new DatabaseConnector();
        } catch (SQLException e) {
            e.printStackTrace();
        }
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

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
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

    public String add() {
        int i = 0;
        if (firstName != null) {
            PreparedStatement ps = null;
            Connection con = null;
            try {
                if (ds != null) {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "INSERT INTO usr(EMAIL, PASS, FIRST_NAME, SECOND_NAME) VALUES(?,?,?,?)";
                        ps = con.prepareStatement(sql);
                        ps.setString(1, email);
                        ps.setString(2, password);
                        ps.setString(3, firstName);
                        ps.setString(4, lastName);
                        i = ps.executeUpdate();
                        System.out.println("Data Added Successfully");
                    }
                }
            } catch (Exception e) {
                System.out.println(e);
            } finally {
                try {
                    con.close();
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        if (i > 0) {
            return "success";
        } else
            return "unsuccess";
    }

    public void dbData(String email) {
        if (email != null) {
            PreparedStatement ps = null;
            Connection con = null;
            ResultSet rs = null;

            if (ds != null) {
                try {
                    con = ds.getConnection();
                    if (con != null) {
                        String sql = "SELECT EMAIL, PASS, FIRST_NAME, SECOND_NAME FROM USR WHERE EMAIL = '"
                                + email + "'";
                        ps = con.prepareStatement(sql);
                        rs = ps.executeQuery();
                        rs.next();
                        dbEmail = rs.getString("EMAIL");
                        dbPassword = rs.getString("PASS");
                        firstName = rs.getString("FIRST_NAME");
                        lastName = rs.getString("SECOND_NAME");
                    }
                } catch (SQLException sqle) {
                    sqle.printStackTrace();
                }
            }
        }
    }

    public String login() {
        dbData(email);
        if (email.equals(dbEmail) && password.equals(dbPassword)) {
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
