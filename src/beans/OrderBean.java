package beans;

import db.dao.DAOSingleton;
import db.dao.OrderDAO;
import db.entities.Dish;
import db.entities.Order;
import db.entities.User;
import model.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;

/**
 * Created by Denis on 19.12.2016.
 */
@ManagedBean(name = "order")
@SessionScoped
public class OrderBean {

    private Order order;
    private OrderDAO orderDAO;

    public OrderBean() {
        //User in session
        orderDAO = DAOSingleton.getInstance().getOrderDAO();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UserBean userBean = (UserBean) facesContext.getApplication().createValueBinding("#{user}").getValue(facesContext);
        User user = userBean.getUser();
        Date d_test = new Date(System.currentTimeMillis());
        //this.order = new Order(new Date(16, 12, 19), user);
        try {
            this.order = orderDAO.selectOrder(user, d_test);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addDish(Dish dish) {
        boolean res = order.add(dish);
        if (res) {
            try {
                orderDAO.insertDishInOrder(order, dish);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean removeDish(Dish dish) {
        boolean res = order.delete(dish);
        if (res) {
            try {
                orderDAO.deleteDishFromOrder(order, dish);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return res;
    }

    public boolean contains(Dish dish) {
        return order.contains(dish);
    }

    /*public int save() {
        try {
            return orderDAO.insertOrder(order);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }*/

}
