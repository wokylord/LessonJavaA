package telran.company.test;

import telran.company.dto.Employee;

import static org.junit.jupiter.api.Assertions.*;
class CompanyTest {
    private static final long ID1 = 0;
    private static final long ID2 = 0;
    Employee empl1 = new Employee(ID1,"name1", SALARY1, DEPARTMENT1, DATE1);
    Employee empl2 = new Employee(ID2,"name2", SALARY2, DEPARTMENT1, DATE2);


    @org.junit.jupiter.api.BeforeEach
    void setUp() {
    }

    @org.junit.jupiter.api.Test
    void hireEmployee() {
    }

    @org.junit.jupiter.api.Test
    void fireEmployee() {
    }

    @org.junit.jupiter.api.Test
    void getEmployee() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeesByDepartment() {
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
    }

    @org.junit.jupiter.api.Test
    void salaryDistributionsByDepartments() {
    }

    @org.junit.jupiter.api.Test
    void getSalaryDistribution() {
    }

    @org.junit.jupiter.api.Test
    void updateDepartment() {
    }

    @org.junit.jupiter.api.Test
    void updateSalary() {
    }

    @org.junit.jupiter.api.Test
    void save() {
    }

    @org.junit.jupiter.api.Test
    void restore() {
    }
}