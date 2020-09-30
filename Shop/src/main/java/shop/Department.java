package shop;

import java.util.ArrayList;
import java.util.List;

public class Department {

    Integer id_department;
    String nameDep;
    Type typeDepartment;
    List<Integer> Salers = new ArrayList<>();
    List<Integer> Products = new ArrayList<>();

    public Department(String nameDep,
                      Type typeDepartment) {
        this.nameDep = nameDep;
        this.typeDepartment = typeDepartment;
    }

    public Department(final String nameDep, final List<Integer> products) {
        this.nameDep = nameDep;
        this.Products = products;
    }

    public Department() {
    }

    public Integer getId_department() {
        return this.id_department;
    }

    public void setId_department(Integer id_department) {
        this.id_department = id_department;
    }

    public String getNameDep() {
        return this.nameDep;
    }

    public void setNameDep(String nameDep) {
        this.nameDep = nameDep;
    }

    public Type getTypeDepartment() {
        return this.typeDepartment;
    }

    public void setTypeDepartment(Type typeDepartment) {
        this.typeDepartment = typeDepartment;
    }

    public List<Integer> getSalers() {
        return this.Salers;
    }

    public void setSalers(List<Integer> salers) {
        this.Salers = salers;
    }

    public List<Integer> getProducts() {
        return this.Products;
    }

    public void setProducts(List<Integer> products) {
        this.Products = products;
    }

    public  void addProduct( Product product){
        this.Products.add(product.getId_product());
    }

    public  void addSaler( Saler saler){
        this.Salers.add(saler.getId_saler());
    }

    @Override
    public String toString() {
        return "Отдел : уникальный номер: " + id_department +
                "; наименование отдела: " + nameDep +
                "; тип отдела: " + typeDepartment.getString();
    }
}
