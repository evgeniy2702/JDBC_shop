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
import java.util.Scanner;

import static util.ConnectionUtil.*;

public class View {

    public String SELECT_by_FIO = "SELECT  FIO FROM saler WHERE FIO = ?";
    public String SELECT_by_name = "SELECT  name_product FROM product WHERE name_product = ?";
    public String SELECT_by_name_dep = "SELECT  name_dep FROM department WHERE name_dep = ?";
    public String UPDATE_quantity_prod ="UPDATE `product` SET `quantity` = `quantity` + ? WHERE `id_prod` = ?";
    public String SELECT_id_prod = "SELECT `id_prod` FROM `product` as idP WHERE `name_product` = ?";
    public String SELECT_dep = "SELECT * FROM department";
    public String SELECT_saler = "SELECT * FROM saler";
    public String SELECT_product ="SELECT * FROM product";

    //Конструктор , который запускает приложение
    public View() throws SQLException, ClassNotFoundException {
        run();
    }

    //Метод, который просматривает базу данных на предмет есть такой элемент или нет
    public <T> boolean equalesEntity(T entity) throws SQLException, ClassNotFoundException {
        getConnection();
        boolean bull = false;
        if( entity instanceof Department) {
                preparedStatement = connection.prepareStatement(SELECT_by_name_dep);
                preparedStatement.setString(1, ((Department) entity).getNameDep());
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        if (((Department) entity).getNameDep().equalsIgnoreCase(resultSet.getString(1))) {
                            bull = true;
                        }
                }
        }
        if( entity instanceof Saler) {
                preparedStatement = connection.prepareStatement(SELECT_by_FIO);
                preparedStatement.setString(1, ((Saler) entity).getFio());
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        if (((Saler) entity).getFio().equalsIgnoreCase(resultSet.getString(1))) {
                            bull = true;
                        }
                }
        }
        if( entity instanceof Product) {
                preparedStatement = connection.prepareStatement(SELECT_by_name);
                preparedStatement.setString(1, ((Product) entity).getNameProd());
                ResultSet resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        if (((Product) entity).getNameProd().equalsIgnoreCase(resultSet.getString(1))) {
                            bull = true;
                        }
                    }
        }
        closePreparedStatment();
        closeConnection();
        return  bull;
    }

    // Метод, который выдает сообщшение об нахождении такого объекта уже в БД или
    // увеличивает кол-во
    public <T> void updateDB( T entity) throws SQLException, ClassNotFoundException {
        getConnection();
            if (entity instanceof Department) {
                System.out.println("Such a department already exist");
            }
            if( entity instanceof Saler){
                System.out.println("Such a saler already exist");
            }
            if( entity instanceof Product){
                preparedStatement = connection.prepareCall(UPDATE_quantity_prod);
                preparedStatement.setInt(1, ((Product)entity).getQuantity());
                preparedStatement.setInt(2, ((Product)entity).getId_product());
                preparedStatement.executeUpdate();
                System.out.println("Product quantity increased by " + ((Product)entity).getQuantity());
            }
        closePreparedStatment();
            closeConnection();
    }

    public void selectIdProd(Product product) throws SQLException, ClassNotFoundException {
        getConnection();
        preparedStatement = connection.prepareCall(SELECT_id_prod);
        preparedStatement.setString(1,product.getNameProd());
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            product.setId_product(resultSet.getInt(1));
        }
        closePreparedStatment();
        closeConnection();
    }
    //Метод, который выводит на печать заданный объект по его ИД
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

    //Метод, запускающий работу приложения
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

    //Метод сканера
    public Scanner scanner(){
        return new Scanner(System.in);
    }

    //Метож окончания работы прилоения
    public void stopStart(){
                    if (preparedStatement!= null || callableStatement!= null || connection != null) {
                        closePreparedStatment();
                        closeCallableStatment();
                        closeConnection();
                    }
                    System.out.println("Работа с приложением окончена. Досвидания");
    }

    //Метод меню выбора
    public int info(){
        int i = 1;
        while (true) {
            Scanner scanner = scanner();
            System.out.println("Выбрать : 1 - про отдел\n          2 - про продавца\n          3 - о товаре\n" +
                    "          4 - назад");
            if(scanner.hasNextInt()){
                i = scanner.nextInt();
                if(i>=1 && i<=4)
                break;
            } else {
                System.out.println("Повторите свой выбор");
            }
        }
        return i;
    }

    //Метод для вноса информации об объектах в БД
    public void insertData() throws SQLException, ClassNotFoundException {
        int i = info();
        if(i == 1){
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
                    } else {
                        updateDB(department);
                    }
                    break;
                } else {
                    System.out.println("Повторите ввод");
                }
            }
        } else if (i == 2){
            Saler saler = new Saler();
            ControllerSaler controllerSaler = new ControllerSaler();
            while (true){
                Scanner scanner = scanner();
                System.out.println("Внесите Ф.И.О продавца : ");
                if(scanner.hasNextLine()){
                    saler.setFio(scanner.nextLine());
                    System.out.println("Внесите дату народження продавца : ");
                    if(scanner.hasNextLine()){
                        saler.setDateBorn(scanner.nextLine());
                        System.out.println("Внесите ЗП продавца : ");
                        if(scanner.hasNextDouble()){
                            saler.setSalary(scanner.nextDouble());
                            System.out.println("Внесите ID отдела : ");
                            if(scanner.hasNextInt()){
                                saler.setIdDepartment(scanner.nextInt());
                                if(!equalesEntity(saler)){
                                    controllerSaler.saveSaler(saler);
                                } else {
                                    updateDB(saler);
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
        } else if (i == 3){
            Product product = new Product();
            ControllerProduct controllerProduct = new ControllerProduct();
            while (true){
                Scanner scanner = scanner();
                System.out.println("Внесите наименование товара : ");
                if(scanner.hasNextLine()){
                    System.out.println("Внесите кол-во товара : ");
                    product.setNameProd(scanner.nextLine());
                    if(scanner.hasNextInt()){
                        System.out.println("Внесите ID отдела : ");
                        product.setQuantity(scanner.nextInt());
                        if(scanner.hasNextInt()){
                            product.setIdDepartament(scanner.nextInt());
                                if(!equalesEntity(product)){
                                    controllerProduct.saveProduct(product);
                                } else {
                                    selectIdProd(product);
                                    updateDB(product);
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
        } else if (i == 4){
            run();
        }
    }

    // Метод выбора типа отделов магазина
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

    //Метод вывода на печать всех элементов БД по типам
    public void printData() throws SQLException, ClassNotFoundException {
        int i = info();
        if(i == 1){
            ControllerDepartament conn = new ControllerDepartament();
            conn.printDep();
        } else if(i == 2){
            ControllerSaler conn = new ControllerSaler();
            conn.printSaler();
        } else if(i == 3){
            ControllerProduct conn = new ControllerProduct();
            conn.printProd();
        } else if (i == 4){
            run();
        }
    }
}
