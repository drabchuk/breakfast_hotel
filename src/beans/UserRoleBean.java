package beans;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

/**
 * Created by Denis on 18.12.2016.
 */
@ManagedBean(name = "UserRole")
@ApplicationScoped
public class UserRoleBean {

    private UserRole[] values = UserRole.values();

    public UserRole[] getValues() {
        return values;
    }

}
