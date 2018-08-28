package com.coffee.bean;

import com.coffee.constant.Constant;
import com.coffee.constant.Message;
import com.coffee.service.CommonService;
import com.coffee.service.OrderService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "coffeeorder")
@ManagedBean
@SessionScoped
public class OrderBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "sort")
    private String sortName;
    @Column(name = "delivery")
    private String delivery;
    @Column(name = "quantity")
    private double quantity;
    @Column(name = "fromdate")
    private Date fromDate;
    @Column(name = "todate")
    private Date toDate;
    @Column(name = "price")
    private double price;

    public void setId(int id) {
        this.id = id;
    }

    public void setSortName(String sortName) {
        this.sortName = sortName;
    }

    public void setDelivery(String delivery) {
        this.delivery = delivery;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public void setFromDate(Date fromDate) {
        this.fromDate = fromDate;
    }

    public void setToDate(Date toDate) {
        this.toDate = toDate;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public String getSortName() {
        return sortName;
    }

    public String getDelivery() {
        return delivery;
    }

    public double getQuantity() {
        return quantity;
    }

    public Date getFromDate() {
        return fromDate;
    }

    public Date getToDate() {
        return toDate;
    }

    public double getPrice() {
        return price;
    }



    public void addOrder() {
        OrderService orderService = new OrderService();
        FacesMessage message;

        if (this.toDate.after(this.fromDate)) {
            orderService.saveOrder(this);
            message = new FacesMessage(Message.ADDED);
        } else {
            message = new FacesMessage(Message.INCORRECT_DATE_INPUT);
        }

        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    public void recountPrice(){
        CommonService commonService = new CommonService();
        double priceForKg = commonService.findPriceBySortName(this.sortName);

        this.price = this.quantity*priceForKg/1000;

        if (this.delivery.equals(Constant.BY_COURIER)){
            this.price = this.price + 1;
        }
    }

    public void changePrice(){
        if ((this.price > 0) && this.delivery.equals(Constant.PICK_UP)){
            this.price = this.price - 1;
        }

        if (this.delivery.equals(Constant.BY_COURIER)){
            this.price = this.price + 1;
        }
    }


    @Override
    public String toString() {
        return "Order № " + this.id + " " + this.sortName + " " + this.delivery + " " + this.quantity +
                " grams; From " + this.fromDate + " to " + this.toDate + " " + this.price + " $ ";
    }
}
