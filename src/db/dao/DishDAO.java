package db.dao;

import model.ProductSalesCount;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by Denis on 09.12.2016.
 */
public interface DishDAO {

    List<ProductSalesCount> getTopProducts() throws SQLException;

}
