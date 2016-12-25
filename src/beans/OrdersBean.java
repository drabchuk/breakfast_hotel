package beans;

import db.dao.DAOSingleton;
import db.dao.OrderDAO;
import db.entities.Order;
import model.Date;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 24.12.2016.
 */
@ManagedBean(name = "orders")
@RequestScoped
public class OrdersBean {

    private List<Order> orderList;
    private OrderDAO orderDAO;

    public OrdersBean() {
        orderDAO = DAOSingleton.getInstance().getOrderDAO();
        try {
            orderList = orderDAO.selectOrders(new Date(System.currentTimeMillis()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    public String viewOrder(Order order) {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        OrderViewBean orderViewBean = (OrderViewBean)
                facesContext.getApplication()
                        .createValueBinding("#{order_view}").getValue(facesContext);
        orderViewBean.setOrder(order);
        return "view";
    }
}
