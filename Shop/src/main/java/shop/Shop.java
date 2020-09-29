package shop;

import java.util.ArrayList;
import java.util.List;

public class Shop {

    public String nameShop;
    public List<Department> Departments;

    public Shop(String nameShop, List<Department> departments) {
        this.nameShop = nameShop;
        Departments = departments;
    }

    public Shop() {
    }

    public String getNameShop() {
        return this.nameShop;
    }

    public void setNameShop(String nameShop) {
        this.nameShop = nameShop;
    }

    public List<Department> getDepartments() {
        return this.Departments;
    }

    public void setDepartments(List<Department> departments) {
        Departments = departments;
    }

    @Override
    public String toString() {
        String listDep = null;
        for (Department dep: Departments) {
            listDep.concat(dep.toString() + "\n");
        }
        return "Магазин :" + nameShop + ", Отделы :"  + listDep;
    }
}
