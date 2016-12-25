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
@ManagedBean(name = "topProducts")
@RequestScoped
public class TopProductsBean {

    DishDAO dishDAO;
    List<ProductSalesCount> topProducts;

    public TopProductsBean() {
        dishDAO = DAOSingleton.getInstance().getDishDAO();
        try {
            this.topProducts = dishDAO.getTopProducts();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<ProductSalesCount> getTopProducts() {
        return topProducts;
    }

}
