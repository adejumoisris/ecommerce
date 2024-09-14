package org.example.ecommerce.dao;

import org.example.ecommerce.models.Order;
import org.example.ecommerce.models.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class OrderDao {
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public OrderDao(Connection connection) {
        this.connection = connection;
    }

    public boolean insertOrder(Order model) {
        boolean result = false;

        try {
            query = "INSERT INTO orders (p_id, u_id,o_quantity,o_date) VALUES(?,?,?,?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, model.getId());
            preparedStatement.setInt(2, model.getUid());
            preparedStatement.setInt(3, model.getQuantity());
            preparedStatement.setString(4, model.getDate());
            preparedStatement.executeUpdate();
            result = true;

        } catch (Exception e) {
            e.printStackTrace();
        }

        return result;
    }


    public List<Order> userOrders(int id) {
        List<Order>  list = new ArrayList<>();

        try {
            query = "SELECT * FROM orders WHERE u_id=?  order by o_id desc";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1,id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Order order = new Order();
                ProductDao productDao = new ProductDao(this.connection);
                int pId = resultSet.getInt("p_id");

                Products products = productDao.getSingleProduct(pId);
                order.setOrderId(resultSet.getInt("o_id"));
                order.setId(pId);
                order.setProductName(products.getProductName());
                order.setProductCategory(products.getProductCategory());
                order.setPrice(products.getPrice()*resultSet.getInt("o_quantity"));
                order.setQuantity(resultSet.getInt("o_quantity"));
                order.setDate(resultSet.getString("o_date"));
                list.add(order);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    public void  canselOrder(int id) {
        try {
            query = "delete from orders where o_id=?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            preparedStatement.execute();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
