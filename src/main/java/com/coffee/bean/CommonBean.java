package com.coffee.bean;

import com.coffee.constant.Message;
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

    public void showAllOrders() {

        orders = orderService.findAllOrders();

        if(orders.size() == 0){
            FacesMessage noOrdersMessage = new FacesMessage(Message.NO_ORDERS);
            FacesContext.getCurrentInstance().addMessage(null, noOrdersMessage);
        }

    }

    public void findAllOrders(){
        orders = orderService.findAllOrders();
        count = orders.size();
    }


    public void changeOrder(OrderBean order) {
        currentOrder = order;
        findAllSortNames();

    }

    public void findAllSortNames(){
        sortNames = commonService.findAllSortNames();
    }

    public void updateOrder(OrderBean order) {
        FacesMessage doneMessage;

        if(this.currentOrder.getToDate().after(this.currentOrder.getFromDate())) {
            doneMessage = new FacesMessage(Message.ORDER_NUMBER + order.getId() + Message.UPDATED);
            orderService.updateOrder(order);

            FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        }
        else {
            doneMessage = new FacesMessage(Message.INCORRECT_DATE_INPUT);
            FacesContext.getCurrentInstance().addMessage(null, doneMessage);
        }

    }

    public void deleteOrder(OrderBean order) {
        FacesMessage doneMessage = new FacesMessage(Message.ORDER_NUMBER + order.getId() + Message.DELETED);

        orderService.deleteOrder(order);

        FacesContext.getCurrentInstance().addMessage(null, doneMessage);

    }

    public void toDeleteOrder(OrderBean orderBean){
        currentOrder = orderBean;

    }
}
