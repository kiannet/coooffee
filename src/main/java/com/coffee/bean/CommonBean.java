package com.coffee.bean;

import com.coffee.service.CommonService;
import com.coffee.service.OrderService;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class CommonBean {
    private int count;
    private OrderBean currentOrder;

    private List<String> sortNames;
    private List<OrderBean> orders = new ArrayList<OrderBean>();

    private OrderService orderService = new OrderService();
    private CommonService commonService = new CommonService();

    public List<String> getSortNames() {
        return sortNames;
    }

    public void setSortNames(List<String> sortNames) {
        this.sortNames = sortNames;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public OrderBean getCurrentOrder() {
        return currentOrder;
    }

    public void setCurrentOrder(OrderBean currentOrder) {
        this.currentOrder = currentOrder;
    }

    public void setOrders(List<OrderBean> orders) {
        this.orders = orders;
    }

    public List<OrderBean> getOrders() {
        return orders;
    }

    public String showAllOrders() {

        String outcome;
        orders = orderService.findAllOrders();

        if(orders.size() == 0){
            FacesMessage noOrdersMessage = new FacesMessage("There're no orders yet.");
            FacesContext.getCurrentInstance().addMessage(null, noOrdersMessage);
        }

        outcome = "seeOrders";

        return outcome;
    }

    public void findAllOrders(){
        orders = orderService.findAllOrders();
        count = orders.size();
    }


    public String changeOrder(OrderBean order) {
        String outcome;

        currentOrder = order;
        findAllSortNames();
        outcome = "change";

        return outcome;
    }

    public void findAllSortNames(){
        sortNames = commonService.findAllSortNames();
    }

    public String toMakeOrder(){
        findAllSortNames();
        return "newOrder";
    }


    public String updateOrder(OrderBean order) {
        String outcome;

        if(validateDate()) {
            FacesMessage doneMessage = new FacesMessage("Order №" + order.getId() + " has been successfully updated.");
            orderService.updateOrder(order);

            outcome = "main";

            FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        }
        else {
            outcome = "";
            FacesMessage doneMessage = new FacesMessage("Incorrect date value. Try again." );
            FacesContext.getCurrentInstance().addMessage(null, doneMessage);

        }

        return outcome;
    }

    public boolean validateDate(){
        return this.currentOrder.getToDate().after(this.currentOrder.getFromDate());
    }

    public String deleteOrder(OrderBean order) {
        String outcome;
        FacesMessage doneMessage = new FacesMessage("Order №" + order.getId() + " has been successfully deleted.");

        orderService.deleteOrder(order);
        outcome = "main";

        FacesContext.getCurrentInstance().addMessage(null, doneMessage);

        return outcome;
    }

    public String toDeleteOrder(OrderBean orderBean){
        String outcome;
        currentOrder = orderBean;
        outcome = "todelete";
        return outcome;

    }
}
