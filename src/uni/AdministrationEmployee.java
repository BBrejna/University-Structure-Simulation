package uni;

import java.io.Serializable;

public class AdministrationEmployee extends Employee implements Serializable {
    int overtimeAmount;

    public AdministrationEmployee() {
        overtimeAmount = 0;
    }
    public AdministrationEmployee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary) {
        super(name, lastName, pesel, age, gender, job, seniority, salary);
        this.overtimeAmount = 0;
    }
    public AdministrationEmployee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary, int overtimeAmount) {
        super(name, lastName, pesel, age, gender, job, seniority, salary);
        this.overtimeAmount = overtimeAmount;
    }

    public int getOvertimeAmount() {
        return overtimeAmount;
    }

    public void setOvertimeAmount(int overtimeAmount) {
        if (overtimeAmount < 0) overtimeAmount = 0;
        this.overtimeAmount = overtimeAmount;
    }
//
//    @Override
//    public String toString() {
//        return "AdministrationEmployee{" +
//                "overtimeAmount=" + overtimeAmount +
//                "} " + super.toString();
//    }
}
