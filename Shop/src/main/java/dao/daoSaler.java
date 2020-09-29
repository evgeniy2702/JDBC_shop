package dao;

import shop.Product;
import shop.Saler;

import java.sql.ResultSet;
import java.sql.SQLException;
import static util.ConnectionUtil.*;

public class daoSaler implements dao<Saler> {

    public String INSERT_saler= "INSERT INTO saler(FIO, date_born, salary,dep_id_s) VALUES(?,?,?,?)";
//    public String SELECT_id_saler = "SELECT id_saler FROM saler WHERE FIO = ?";
    public String SELECT_by_id_saler = "SELECT  * FROM saler WHERE id_saler = ?";
    public String SELECT_all = "SELECT * FROM saler";
    @Override
    public void saveEntity(Saler entity) throws SQLException, ClassNotFoundException {
        getConnection();
            preparedStatement = connection.prepareStatement(INSERT_saler);
            preparedStatement.setString(1, entity.getFio());
            preparedStatement.setString(2, entity.getDateBorn());
            preparedStatement.setDouble(3, entity.getSalary());
            preparedStatement.setInt(4, entity.getIdDepartment());
            preparedStatement.executeUpdate();
//            preparedStatement = connection.prepareStatement(SELECT_id_saler);
//            preparedStatement.setString(1, entity.getFio());
//            ResultSet res = preparedStatement.executeQuery();
//            while (res.next()) {
//                entity.setId_saler(res.getInt("id_saler"));
//            }
        closePreparedStatment();
        closeConnection();
    }

    @Override
    public Saler getEntityById(Integer idEntity) throws SQLException, ClassNotFoundException {
        getConnection();
        Saler saler = new Saler();
        preparedStatement = connection.prepareStatement(SELECT_by_id_saler);
        preparedStatement.setInt(1,idEntity);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            saler.setId_saler(resultSet.getInt("id_saler"));
            saler.setFio(resultSet.getString("FIO"));
            saler.setDateBorn(resultSet.getString("date_born"));
            saler.setSalary(resultSet.getDouble("salary"));
            saler.setIdDepartment(resultSet.getInt("dep_id_s"));
        }
        closePreparedStatment();
        closeConnection();
        return saler;
    }

    @Override
    public void print() throws SQLException, ClassNotFoundException {
        getConnection();
        preparedStatement = connection.prepareStatement(SELECT_all);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Saler saler = new Saler();
            saler.setId_saler(resultSet.getInt("id_saler"));
            saler.setFio(resultSet.getString("FIO"));
            saler.setDateBorn(resultSet.getString("date_born"));
            saler.setSalary(resultSet.getDouble("salary"));
            saler.setIdDepartment(resultSet.getInt("dep_id_s"));
            System.out.println(saler.toString());
        }
        closePreparedStatment();
        closeConnection();
    }
}
