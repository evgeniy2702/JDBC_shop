package dao;

import shop.Department;
import shop.Type;
import util.ConnectionUtil;

import java.sql.ResultSet;
import java.sql.SQLException;

import static util.ConnectionUtil.*;

public class daoDepartment implements dao<Department> {

    public String INSERT_department ="INSERT INTO department(name_dep, type_department) VALUES (?,?)";
//    public String SELECT_id = "SELECT id_dep FROM department WHERE name_dep =?";
    public String SELECT_by_id = "SELECT * FROM department WHERE id_dep =?";
    public String SELECT_all = "SELECT * FROM department";

    @Override
    public void saveEntity(Department entity) throws SQLException, ClassNotFoundException {
        getConnection();
        preparedStatement = connection.prepareStatement(INSERT_department);
        preparedStatement.setString(1,entity.getNameDep());
        preparedStatement.setString(2,entity.getTypeDepartment().getString());
        preparedStatement.executeUpdate();
//        preparedStatement = connection.prepareStatement(SELECT_id);
//        preparedStatement.setString(1,entity.getNameDep());
//        ResultSet resultSet = preparedStatement.executeQuery();
//        while(resultSet.next()){
//            entity.setId_department(resultSet.getInt("id_dep"));
//        }
        closePreparedStatment();
        closeConnection();
    }

    @Override
    public Department getEntityById(Integer idEntity) throws SQLException, ClassNotFoundException {
        Department department = new Department();
        getConnection();
        preparedStatement = connection.prepareStatement(SELECT_by_id);
        preparedStatement.setInt(1,idEntity);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            department.setId_department(resultSet.getInt("id_dep"));
            department.setNameDep(resultSet.getString("name_dep"));
            department.setTypeDepartment(Type.valueOf(resultSet.getString("type_departament")));
        }
        closePreparedStatment();
        closeConnection();
        return department;
    }

    @Override
    public void print() throws SQLException, ClassNotFoundException {
        getConnection();
        preparedStatement = connection.prepareStatement(SELECT_all);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Department department = new Department();
            department.setId_department(resultSet.getInt("id_dep"));
            department.setNameDep(resultSet.getString("name_dep"));
            department.setTypeDepartment(Type.valueOf(resultSet.getString("type_department")));
            System.out.println(department.toString());
        }
        closePreparedStatment();
        closeConnection();
    }
}
