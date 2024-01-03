package uni;

import java.io.Serializable;

public abstract class Employee extends Person implements Serializable {
    protected String job;
    protected int seniority;
    protected int salary;

    public Employee() {
        this.job = "test";
        this.seniority = -1;
        this.salary = -1;
    }
    public Employee(String name, String lastName, String pesel, double age, String gender) {
        super(name, lastName, pesel, age, gender);
        this.job = "Uni employee";
        this.seniority = 0;
        this.salary = 4499;
    }
    public Employee(String name, String lastName, String pesel, double age, String gender, String job, int seniority, int salary) {
        super(name, lastName, pesel, age, gender);
        this.job = job;
        this.seniority = seniority;
        this.salary = salary;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public int getSeniority() {
        return seniority;
    }

    public void setSeniority(int seniority) {
        this.seniority = seniority;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "job='" + job + '\'' +
                ", seniority=" + seniority +
                ", salary=" + salary +
                "} " + super.toString();
    }
}
