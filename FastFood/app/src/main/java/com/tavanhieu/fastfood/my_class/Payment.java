package com.tavanhieu.fastfood.my_class;

import java.io.Serializable;
import java.util.List;

public class Payment implements Serializable {
    private String id;
    private User user;
    private List<BuyProduct> arrBuyProduct;
    private Float itemTotal, shipping, totalPayment;
    private String status;
    private MyDate date;

    public Payment() {
    }

    public Payment(User user, List<BuyProduct> arrBuyProduct, Float itemTotal, Float shipping, Float totalPayment, String status, MyDate date) {
        this.user = user;
        this.arrBuyProduct = arrBuyProduct;
        this.itemTotal = itemTotal;
        this.shipping = shipping;
        this.totalPayment = totalPayment;
        this.status = status;
        this.date = date;
        this.id = getDate().getDayMonthYear() + getDate().getHourMinute() + getDate().getSecond();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<BuyProduct> getArrBuyProduct() {
        return arrBuyProduct;
    }

    public void setArrBuyProduct(List<BuyProduct> arrBuyProduct) {
        this.arrBuyProduct = arrBuyProduct;
    }

    public Float getItemTotal() {
        return itemTotal;
    }

    public void setItemTotal(Float itemTotal) {
        this.itemTotal = itemTotal;
    }

    public Float getShipping() {
        return shipping;
    }

    public void setShipping(Float shipping) {
        this.shipping = shipping;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public MyDate getDate() {
        return date;
    }

    public void setDate(MyDate date) {
        this.date = date;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}
