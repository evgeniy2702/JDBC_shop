package controller;


import dao.daoSaler;
import shop.Saler;

import java.sql.SQLException;

public class ControllerSaler extends daoSaler {

    public void saveSaler(Saler saler) throws SQLException, ClassNotFoundException {
        saveEntity(saler);
    }

    public Saler getSalerByID(int idSaler) throws SQLException, ClassNotFoundException {
        return getEntityById(idSaler);
    }

    public void printSaler() throws SQLException, ClassNotFoundException {
        print();
    }
}
