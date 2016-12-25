package model;

import db.entities.Dish;

/**
 * Created by Denis on 25.12.2016.
 */
public class ProductSalesCount {

    private Dish dish;
    private int total;

    public ProductSalesCount(Dish dish, int total) {
        this.dish = dish;
        this.total = total;
    }

    public Dish getDish() {
        return dish;
    }

    public void setDish(Dish dish) {
        this.dish = dish;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
}
