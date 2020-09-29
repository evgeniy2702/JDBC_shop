import controller.ControllerDepartament;
import controller.ControllerProduct;
import controller.ControllerSaler;
import shop.Department;
import shop.Product;
import shop.Saler;
import shop.Type;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static util.ConnectionUtil.*;

public class View {

    public String SELECT_by_id_saler = "SELECT  id_saler FROM saler WHERE id_saler = ?";
    public String SELECT_by_id_prod = "SELECT  id_prod FROM product WHERE id_prod = ?";
    public String SELECT_by_id_dep = "SELECT  id_dep FROM department WHERE id_dep = ?";
    public String UPDATE_quantity_prod ="{CALL updateQuantityProd(?,?)}";
    public String SELECT_dep = "SELECT * FROM department";
    public String SELECT_saler = "SELECT * FROM saler";
    public String SELECT_product ="SELECT * FROM product";

    public View() throws SQLException, ClassNotFoundException {
        run();
    }

    public <T> boolean equalesEntity(T entity) throws SQLException, ClassNotFoundException {
        getConnection();
        boolean bull = false;
        if( entity instanceof Department) {
            preparedStatement = connection.prepareStatement(SELECT_by_id_dep);
            preparedStatement.setInt(1, ((Department)entity).getId_department());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (((Department)entity).getId_department()== resultSet.getInt(1)){
                        bull = true;
                }
            }
        }
        if( entity instanceof Saler) {
            preparedStatement = connection.prepareStatement(SELECT_by_id_saler);
            preparedStatement.setInt(1, ((Saler)entity).getId_saler());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (((Saler)entity).getId_saler()== resultSet.getInt(1)){
                    bull = true;
                }
            }
        }
        if( entity instanceof Product) {
            preparedStatement = connection.prepareStatement(SELECT_by_id_prod);
            preparedStatement.setInt(1, ((Product)entity).getId_product());
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                if (((Product)entity).getId_product()== resultSet.getInt(1)){
                    bull = true;
                }
            }
        }
        closePreparedStatment();
        closeConnection();
        return  bull;
    }

    public <T> void updateDB( T entity) throws SQLException, ClassNotFoundException {
        getConnection();
            if (entity instanceof Department) {
                System.out.println("Such a department already exist");
            }
            if( entity instanceof Saler){
                System.out.println("Such a saler already exist");
            }
            if( entity instanceof Product){
                callableStatement = connection.prepareCall(UPDATE_quantity_prod);
                callableStatement.setInt(1, ((Product)entity).getId_product());
                callableStatement.registerOutParameter(2, Types.INTEGER);
                callableStatement.executeUpdate();
                System.out.println("Product quantity increased by " + ((Product)entity).getQuantity());
            }
        closePreparedStatment();
            closeConnection();
    }

    public <T> void printEntity(T entity) throws SQLException, ClassNotFoundException {
        getConnection();
        if( entity instanceof Department){
            preparedStatement = connection.prepareStatement(SELECT_dep);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()){
                System.out.println("Departmen description:" + resultSetMetaData.getColumnLabel(1) +
                                resultSet.getInt("id_dep") + resultSetMetaData.getColumnLabel(2) +
                                resultSet.getString("name_dep") + resultSetMetaData.getColumnLabel(3) +
                        resultSet.getString("type_department"));
            }
        }
        if( entity instanceof Saler){
            preparedStatement = connection.prepareStatement(SELECT_saler);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            while (resultSet.next()){
                System.out.println("Saler:" + resultSetMetaData.getColumnLabel(1) + resultSet.getInt("id_saler") +
                        resultSetMetaData.getColumnLabel(2) + resultSet.getString("FIO") + resultSetMetaData.getColumnLabel(3) +
                        resultSet.getString("date_born") + resultSetMetaData.getColumnLabel(4) + resultSet.getDouble("salary") +
                        resultSetMetaData.getColumnLabel(5) + resultSet.getInt("dep_id_s"));
            }
        }
        if( entity instanceof Product){
            preparedStatement = connection.prepareStatement(SELECT_product);
            ResultSet resultSet = preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData = resultSet.getMetaData();
            System.out.println(resultSetMetaData.getColumnLabel(1) + resultSetMetaData.getColumnLabel(2) +
                    resultSetMetaData.getColumnLabel(3) + resultSetMetaData.getColumnLabel(4));
            while (resultSet.next()){
                System.out.println("Product: " + resultSetMetaData.getColumnLabel(1) + resultSet.getInt("id_saler") +
                        resultSetMetaData.getColumnLabel(2) + resultSet.getString("name_product") + resultSetMetaData.getColumnLabel(3) +
                        resultSet.getInt("quantity") + resultSetMetaData.getColumnLabel(4) + resultSet.getInt("dep_id_p"));
            }
        }

        closePreparedStatment();
        closeConnection();
    }

    public void run() throws SQLException, ClassNotFoundException {
        while (true){
            Scanner scanner = scanner();
            int choice = 0;
            System.out.println("Выполнить : 1 - внести данные\n            2 - вывести данные\n            3 - " +
                    "закончить работу");
            if(scanner.hasNextInt()){
                choice = scanner.nextInt();
                switch (choice){
                    case 1:
                        insertData();
                        run();
                        break;
                    case 2:
                        printData();
                        run();
                        break;
                    case 3:
                        stopStart();
                        break;
                    default:
                        System.out.println("Вы выполнили не корректный выбор. Повторите еще раз.");
                        run();
                }
            }
            break;
        }

    }

    public Scanner scanner(){
        return new Scanner(System.in);
    }

    public void stopStart(){
                    if (preparedStatement!= null || callableStatement!= null || connection != null) {
                        closePreparedStatment();
                        closeCallableStatment();
                        closeConnection();
                    }
                    System.out.println("Работа с приложением окончена. Досвидания");
    }

    public int info(){
        int i = 1;
        while (true) {
            Scanner scanner = scanner();
            System.out.println("Выбрать : 1 - про отдел\n          2 - про продавца\n          3 - о товаре");
            if(scanner.hasNextInt()){
                i = scanner.nextInt();
                if(i>=1 && i<=3)
                break;
            } else {
                System.out.println("Повторите свой выбор");
            }
        }
        return i;
    }

    public void insertData() throws SQLException, ClassNotFoundException {
        if(info() == 1){
            Department department = new Department();
            ControllerDepartament controllerDepartament = new ControllerDepartament();
            while (true){
                Scanner scanner = scanner();
                System.out.println("Внесите наименование отдела : ");
                if(scanner.hasNextLine()){
                    department.setNameDep(scanner.nextLine());
                    choiceType(department);
                    if(!equalesEntity(department)){
                        controllerDepartament.saveDepartment(department);
                    }
                    break;
                } else {
                    System.out.println("Повторите ввод");
                }
            }
        } else if (info() == 2){
            Saler saler = new Saler();
            ControllerSaler controllerSaler = new ControllerSaler();
            while (true){
                Scanner scanner = scanner();
                if(scanner.hasNextLine()){
                    System.out.println("Внесите Ф.И.О продавца : ");
                    saler.setFio(scanner.nextLine());
                    if(scanner.hasNextLine()){
                        System.out.println("Внесите дату народження продавца : ");
                        saler.setDateBorn(scanner.nextLine());
                        if(scanner.hasNextDouble()){
                            System.out.println("Внесите ЗП продавца : ");
                            saler.setSalary(scanner.nextDouble());
                            if(scanner.hasNextInt()){
                                System.out.println("Внесите ID отдела : ");
                                saler.setIdDepartment(scanner.nextInt());
                                if(!equalesEntity(saler)){
                                    controllerSaler.saveSaler(saler);
                                }
                                break;
                            } else {
                                System.out.println("Повторите ввод");
                            }
                        } else {
                            System.out.println("Повторите ввод");
                        }
                    } else {
                        System.out.println("Повторите ввод");
                    }
                } else {
                    System.out.println("Повторите ввод");
                }
            }
        } else if (info() == 3){
            Product product = new Product();
            ControllerProduct controllerProduct = new ControllerProduct();
            while (true){
                Scanner scanner = scanner();
                if(scanner.hasNextLine()){
                    System.out.println("Внесите наименование товара : ");
                    product.setNameProd(scanner.nextLine());
                    if(scanner.hasNextInt()){
                        System.out.println("Внесите кол-во товара : ");
                        product.setQuantity(scanner.nextInt());
                        if(scanner.hasNextInt()){
                            System.out.println("Внесите ID отдела : ");
                            product.setIdDepartament(scanner.nextInt());
                                if(!equalesEntity(product)){
                                    controllerProduct.saveProduct(product);
                                }
                                break;
                            } else {
                                System.out.println("Повторите ввод");
                            }
                        } else {
                            System.out.println("Повторите ввод");
                        }
                    } else {
                        System.out.println("Повторите ввод");
                    }
            }
        }
    }

    public void choiceType(Department department){
        while (true){
            Scanner scanner = scanner();
            System.out.println("Выберите тип отдела : 1 - продуктовый\n                      2 - промтовары");
            if(scanner.hasNextInt()){
                int i = scanner.nextInt();
                if (i == 1){
                    department.setTypeDepartment(Type.PRODUCT);
                } else if(i == 2){
                    department.setTypeDepartment(Type.INDUSTRIAL);
                }
                break;
            } else {
                System.out.println("Повторите выбор.");
            }
        }
    }

    public void printData() throws SQLException, ClassNotFoundException {
        if(info() == 1){
            ControllerDepartament conn = new ControllerDepartament();
            conn.printDep();
        } else if(info() == 2){
            ControllerSaler conn = new ControllerSaler();
            conn.printSaler();
        } else if(info() == 3){
            ControllerProduct conn = new ControllerProduct();
            conn.printProd();
        }
    }
}
