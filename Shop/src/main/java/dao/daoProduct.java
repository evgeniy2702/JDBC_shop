package dao;

import shop.Department;
import shop.Product;
import shop.Saler;
import shop.Type;

import java.sql.*;

import static util.ConnectionUtil.*;

public class daoProduct implements dao<Product> {

    public String INSERT_product = "INSERT INTO product (name_product, quantity, dep_id_p) VALUES (?,?,?)";
//    public String SELECT_by_id ="SELECT id_prod FROM product WHERE name_product = ?";
    public String SELECT_by_id_product = "SELECT * FROM product WHERE id_prod = ?";
    public String SELECT_all = "SELECT * FROM product";

    //Перегруженый метод записи в БД
    @Override
    public void saveEntity(Product entity) throws SQLException, ClassNotFoundException {
        getConnection();
            preparedStatement = connection.prepareStatement(INSERT_product);
            preparedStatement.setString(1,entity.nameProd);
            preparedStatement.setInt(2,entity.quantity);
            preparedStatement.setInt(3,entity.idDepartament);
            preparedStatement.executeUpdate();
//            preparedStatement = connection.prepareStatement(SELECT_by_id);
//            preparedStatement.setString(1,entity.getNameProd());
//            ResultSet res = preparedStatement.executeQuery();
//            while (res.next()) {
//                entity.setId_product(res.getInt("id_prod"));
//            }
        closePreparedStatment();
        closeConnection();
    }

    //Метод, получения элемента БД по его ИД
    @Override
    public Product getEntityById(Integer idEntity) throws SQLException, ClassNotFoundException {
        getConnection();
        Product product = new Product();
        preparedStatement = connection.prepareStatement(SELECT_by_id_product);
        preparedStatement.setInt(1,idEntity);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            product.setId_product(resultSet.getInt("id_prod"));
            product.setNameProd(resultSet.getString("name_product"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setIdDepartament(resultSet.getInt("dep_id_p"));
        }
        closePreparedStatment();
        closeConnection();
        return product;
    }

    //Метод , выводящий элемент на печать по его ИД
    @Override
    public void print() throws SQLException, ClassNotFoundException {
        getConnection();
        preparedStatement = connection.prepareStatement(SELECT_all);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Product product = new Product();
            product.setId_product(resultSet.getInt("id_prod"));
            product.setNameProd(resultSet.getString("name_product"));
            product.setQuantity(resultSet.getInt("quantity"));
            product.setIdDepartament(resultSet.getInt("dep_id_p"));
            System.out.println(product.toString());
        }
        closePreparedStatment();
        closeConnection();
    }
}
