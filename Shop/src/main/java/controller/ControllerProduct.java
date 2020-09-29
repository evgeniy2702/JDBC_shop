package controller;

import dao.daoProduct;
import shop.Product;

import java.sql.SQLException;

public class ControllerProduct extends daoProduct {

    public void saveProduct(Product product) throws SQLException, ClassNotFoundException {
        saveEntity(product);
    }

    public Product getProductByID(int idProduct) throws SQLException, ClassNotFoundException {
        return getEntityById(idProduct);
    }

    public void printProd() throws SQLException, ClassNotFoundException {
        print();
    }
}
