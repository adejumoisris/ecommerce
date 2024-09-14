package org.example.ecommerce.models;

public class Order extends Products{
    private int orderId;
    private int uid;
    private int quantity;
    private String date;

  public Order(){

  }

    public Order(int id, String image, double price, String productCategory, String productName, String date, int orderId, int quantity, int uid) {
        super(id, image, price, productCategory, productName);
        this.date = date;
        this.orderId = orderId;
        this.quantity = quantity;
        this.uid = uid;
    }


    public Order(int id, String image, double price, String productCategory, String productName, String date, int quantity, int uid) {
        super(id, image, price, productCategory, productName);
        this.date = date;
        this.quantity = quantity;
        this.uid = uid;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Order{" +
                "date='" + date + '\'' +
                ", orderId=" + orderId +
                ", uid=" + uid +
                ", quantity=" + quantity +
                '}';
    }
}
