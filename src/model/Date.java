package model;

/**
 * Created by Denis on 24.12.2016.
 */
public class Date {

    private java.util.Date date;

    public Date(java.util.Date date) {
        this.date = date;
    }

    public Date(long time) {
        this.date = new java.util.Date(time);
    }

    @Override
    public String toString() {
        int dd = date.getDate();
        int mm = date.getMonth() + 1;
        int yy = date.getYear() - 100;
        String res = Integer.toString(dd) + "-" + Integer.toString(mm)+ "-" + Integer.toString(yy);
        return res;
    }
}
