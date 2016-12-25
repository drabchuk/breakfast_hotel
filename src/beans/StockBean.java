package beans;

import db.dao.DAOSingleton;
import db.dao.DishDAO;
import model.ProductSalesCount;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 25.12.2016.
 */
@ManagedBean(name = "stock")
@RequestScoped
public class StockBean {
    DishDAO dishDAO;
    List<ProductSalesCount> dishes;

    public StockBean() {
        dishDAO = DAOSingleton.getInstance().getDishDAO();
        try {
            this.dishes = dishDAO.getStockAccount();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductSalesCount> getDishes() {
        return dishes;
    }
}
