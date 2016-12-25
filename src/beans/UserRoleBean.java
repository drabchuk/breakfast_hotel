package beans;

import model.UserRole;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

@ManagedBean(name = "UserRole")
@ApplicationScoped
public class UserRoleBean {

    private UserRole[] values = UserRole.values();

    public UserRole[] getValues() {
        return values;
    }

}
