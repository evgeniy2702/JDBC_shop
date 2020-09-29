package shop;

import java.sql.SQLException;

import static util.ConnectionUtil.*;

public class Product {

    public Integer id_product;
    public String nameProd;
    public Integer quantity;
    public Integer idDepartament;

    public Product(String nameProd, Integer quantity, Integer idDepartment) {
        this.nameProd = nameProd;
        this.quantity = quantity;
        this.idDepartament = idDepartment;
    }

    public Product() {
    }

    public Integer getId_product() {
        return id_product;
    }

    public void setId_product(Integer id_product){
        this.id_product = id_product;
    }

    public String getNameProd() {
        return nameProd;
    }

    public void setNameProd(String nameProd) {
        this.nameProd = nameProd;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Integer getIdDepartament() {
        return idDepartament;
    }

    public void setIdDepartament(Integer idDepartament) {
        this.idDepartament = idDepartament;
    }

    @Override
    public String toString() {
            return "Товар :" +
                    "id_№=" + id_product +
                    " | название товара: " + nameProd +
                    " | кол-во : " + quantity +
                    " | тип товара :" + idDepartament;
    }
}
