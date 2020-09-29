package shop;

import java.time.LocalDate;
import java.util.Date;

public class Saler {

    public Integer id_saler;
    public String fio;
    public String dateBorn;
    public Double salary;
    public Integer idDepartment;

    public Saler(String fio, String dateBorn, Double salary, Integer idDepartment) {
        this.fio = fio;
        this.dateBorn = dateBorn;
        this.salary = salary;
        this.idDepartment = idDepartment;
    }

    public Saler() {
    }


    public Integer getId_saler() {
        return this.id_saler;
    }

    public void setId_saler(Integer id_saler) {
        this.id_saler = id_saler;
    }

    public String getFio() {
        return this.fio;
    }

    public void setFio( String fio) {
        this.fio = fio;
    }

    public String getDateBorn() {
        return this.dateBorn;
    }

    public void setDateBorn(String dateBorn) {
        this.dateBorn = dateBorn;
    }

    public Double getSalary() {
        return this.salary;
    }

    public void setSalary(Double salary) {
        this.salary = salary;
    }

    public Integer getIdDepartment() {
        return this.idDepartment;
    }

    public void setIdDepartment(Integer idDepartment) {
        this.idDepartment = idDepartment;
    }

    @Override
    public String toString() {
        return "Продавец : " +
                "уникальный номер : " + id_saler + ";\n" +
                "Ф.И.О. : " + fio + ";\n" +
                "дата рождения : " + dateBorn + "; ЗП: " + salary + "; Отдел : " + idDepartment;
    }
}
