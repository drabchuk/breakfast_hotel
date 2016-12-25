package beans;

import db.dao.DAOSingleton;
import db.dao.OrderDAO;
import db.entities.Order;
import db.entities.User;
import model.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;

/**
 * Created by Denis on 25.12.2016.
 */
@ManagedBean(name = "order_view")
@SessionScoped
public class OrderViewBean {

    private Order order;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String viewOrder(Order order) {
        setOrder(order);
        return "view";
    }

}
