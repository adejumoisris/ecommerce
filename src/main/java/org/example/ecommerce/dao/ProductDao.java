package org.example.ecommerce.dao;

import org.example.ecommerce.models.Cart;
import org.example.ecommerce.models.Products;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ProductDao {
    private Connection connection;
    private String query;
    private PreparedStatement preparedStatement;
    private ResultSet resultSet;

    public ProductDao(Connection connection) {
        this.connection = connection;
    }

    // get All products from the database

    public List<Products> getAllProducts() {
        List<Products> products = new ArrayList<Products>();
        try {
            query = "SELECT * FROM products";
            preparedStatement = connection.prepareStatement(query);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Products row = new Products();
                row.setId(resultSet.getInt("id"));
                row.setProductName(resultSet.getString("productName"));
                row.setProductCategory(resultSet.getString("productCategory"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));
                products.add(row);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return products;
    }
// get All products in cart

    public List<Cart> getCartProducts(ArrayList<Cart> cartList) {
        List<Cart> productsCart = new ArrayList<Cart>();

        try {
            if (cartList.size()>0){
                for (Cart item : cartList) {
                    query = "SELECT * FROM products WHERE id = ?";
                    preparedStatement = this.connection.prepareStatement(query); // needs to edit something
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Cart row = new Cart();
                        row.setId(resultSet.getInt("id"));
                        row.setProductName(resultSet.getString("productName"));
                        row.setProductCategory(resultSet.getString("productCategory"));
                        row.setPrice(resultSet.getDouble("price")*item.getQuantity()); // multiply product with quantity
                        row.setQuantity(item.getQuantity());
                        productsCart.add(row);
                    }
                }
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
          //  e.printStackTrace();
        }
        return productsCart;

    }

    public  Products getSingleProduct(int id){
        Products row = null;

        try {
            query = "SELECT * FROM products WHERE id = ?";
            preparedStatement = this.connection.prepareStatement(query);
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                row = new Products();
                row.setId(resultSet.getInt("id"));
                row.setProductName(resultSet.getString("productName"));
                row.setProductCategory(resultSet.getString("productCategory"));
                row.setPrice(resultSet.getDouble("price"));
                row.setImage(resultSet.getString("image"));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return row ;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList) {
        double sum = 0;
        try {
            if (cartList.size()>0){
                for (Cart item : cartList) {
                    query = "SELECT price FROM products where id = ?";
                    preparedStatement = this.connection.prepareStatement(query);
                    preparedStatement.setInt(1, item.getId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        sum += resultSet.getDouble("price")*item.getQuantity();
                    }

                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return sum;
    }
}
