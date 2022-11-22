package com.tavanhieu.fastfood.my_class;

import java.io.Serializable;
import java.util.List;

public class Payment implements Serializable {
    private User user;
    private List<BuyProduct> arrBuyProduct;
    private Float itemTotal, shipping, totalPayment;
    private String date;

    public Payment() {
    }

    public Payment(User user, List<BuyProduct> arrBuyProduct, Float itemTotal, Float shipping, Float totalPayment, String date) {
        this.user = user;
        this.arrBuyProduct = arrBuyProduct;
        this.itemTotal = itemTotal;
        this.shipping = shipping;
        this.totalPayment = totalPayment;
        this.date = date;
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

    public Float getTotalPayment() {
        return totalPayment;
    }

    public void setTotalPayment(Float totalPayment) {
        this.totalPayment = totalPayment;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
