package tools;

import uni.*;

import java.util.Objects;

public class EmployeeSearcher implements Searcher<Employee, Person> {
    public String getPropertyValue(Employee employee, String mode) {
        String value = null;
        if (Objects.equals(mode, "lastName")) value = employee.getlastName();
        else if (Objects.equals(mode, "firstName")) value = employee.getName();
        else if (Objects.equals(mode, "job")) value = employee.getJob();
        else if (Objects.equals(mode, "seniority")) value = Integer.toString(employee.getSeniority());
        else if (Objects.equals(mode, "salary")) value = Integer.toString(employee.getSalary());
        else if (employee instanceof DidacticEmployee && Objects.equals(mode, "publicationsNumber")) value = Integer.toString(((DidacticEmployee) employee).getPublicationsNumber());
        else if (employee instanceof AdministrationEmployee && Objects.equals(mode, "overtimeAmount")) value = Integer.toString(((AdministrationEmployee) employee).getOvertimeAmount());

        return value;
    }
    @Override
    public MyHashSet<Employee> search(MyHashSet<Person> people, String mode, String keyWord) {
        MyHashSet<Employee> employees = new MyHashSet<>();
        people.forEach(person -> {
            if (person instanceof Employee) employees.add((Employee) person);
        });

        MyHashSet<Employee> answer = new MyHashSet<Employee>();
        employees.forEach(employee -> {
            if (Objects.equals(getPropertyValue(employee, mode), keyWord)) {
                answer.add(employee);
            }
        });
        return answer;
    }
}
