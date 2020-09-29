package controller;

import dao.daoDepartment;
import shop.Department;

import java.sql.SQLException;

public class ControllerDepartament extends daoDepartment {

    public void saveDepartment(Department department) throws SQLException, ClassNotFoundException {
        saveEntity(department);
    }

    public Department getDepByID(int idDep) throws SQLException, ClassNotFoundException {
        return getEntityById(idDep);
    }

    public void printDep() throws SQLException, ClassNotFoundException {
        print();
    }
}
