package telran.company.test;

import java.time.temporal.*;

import telran.company.dto.Employee;
import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CompanyTest {
    private static final long ID1 = 123;
    private static final long ID2 = 124;
    private static final long ID3 = 125;
    private static final long ID4 = 126;
    private static final long ID5 = 127;
    private static final long ID99 = 1000;
    private static final int SALARY1 = 5000;
    private static final int SALARY2 = 6000;
    private static final int SALARY3 = 7000;
    private static final int SALARY4 = 8000;
    private static final int SALARY5 = 9000;
    private static final String DEPARTMENT1 = "QA";
    private static final String DEPARTMENT2 = "Development";
    private static final String DEPARTMENT3 = "Management";
    private static final String DEPARTMENT99 = "Audit";
    private static final LocalDate DATE1 = LocalDate.of(1970, 10, 23);
    private static final LocalDate DATE2 = LocalDate.of(1975, 1, 1);
    private static final LocalDate DATE3 = LocalDate.of(1980, 5, 3);
    private static final LocalDate DATE4 = LocalDate.of(1990, 5, 3);
    private static final LocalDate DATE5 = LocalDate.of(2000, 5, 3);

    Employee empl1 = new Employee(ID1, "name1", SALARY1, DEPARTMENT1, DATE1);
    Employee empl2 = new Employee(ID2, "name2", SALARY2, DEPARTMENT1, DATE2);
    Employee empl3 = new Employee(ID3, "name3", SALARY3, DEPARTMENT2, DATE3);
    Employee empl4 = new Employee(ID4, "name4", SALARY4, DEPARTMENT2, DATE4);
    Employee empl5 = new Employee(ID5, "name5", SALARY5, DEPARTMENT3, DATE5);
    Employee[] employees = {empl1, empl2, empl3, empl4, empl5};
    CompanyService company = null;

    @org.junit.jupiter.api.BeforeEach
    void setUp() throws Exception {
        company = new CompanyServiceImpl(); // ?
        for (Employee empl : employees) {
            company.hireEmployee(empl);
        }
    }

    @org.junit.jupiter.api.Test
    void hireEmployeeNormal() {
        Employee newEmployee = new Employee(ID99, "name6", SALARY1, DEPARTMENT1, DATE1);
        assertEquals(newEmployee, company.hireEmployee(newEmployee)); //?
    }

    @org.junit.jupiter.api.Test
        // проверяем что попытка добавить в метод существующий empl выкинет exception
    void hireEmployeeException() {
        Employee newEmployee = empl1;
        // FIXME
        boolean flException = false;
        try {
            company.hireEmployee(newEmployee);
        } catch (IllegalStateException e) {
            flException = true;
        }
        // если      false   то тест не прошел
        assertTrue(flException);
    }

    @org.junit.jupiter.api.Test
    void fireEmployeeNormal() {
        assertEquals(empl1, company.fireEmployee(ID1));
        // если метод добавления hireEmployee не бросает exception значит до этого он удалил=> все норм
        assertEquals(empl1, company.hireEmployee(empl1));
    }

    @org.junit.jupiter.api.Test
    void fireEmployeeException() {
        boolean flException = false;
        try {
            company.fireEmployee(ID99);
        } catch (IllegalStateException e) {
            flException = true;
        }
        // если влаг true то проверку мы прошли
        assertTrue(flException);
    }

    @org.junit.jupiter.api.Test
    void getEmployee() {
        assertEquals(empl1, company.getEmployee(ID1));
        assertNull(company.getEmployee(ID99));
    }

    @org.junit.jupiter.api.Test
    void getEmployeesByDepartment() {
        // ожидаемый массив
        Employee[] expectedEmployeesDep1 = {empl1, empl2};
        Employee[] expectedEmployeesDep2 = {empl3, empl4};
        // применяем метод который дает листы
        List<Employee> list1 = company.getEmployeesByDepartment(DEPARTMENT1);
        List<Employee> list2 = company.getEmployeesByDepartment(DEPARTMENT2);
        //    делаем из листов массивы так как метод делает листы
        Employee[] actualDep1 = list1.toArray(new Employee[]{});
        Employee[] actualDep2 = list2.toArray(new Employee[]{});
        // сортируем актуальные массивы по id чтобы потом их сравнить с ожидаемыми массивами в assertArrays
        Arrays.sort(actualDep1);
        Arrays.sort(actualDep2);
        //?
        assertTrue(company.getEmployeesByDepartment(DEPARTMENT99).isEmpty());
        //?
        assertArrayEquals(expectedEmployeesDep1, actualDep1);
        assertArrayEquals(expectedEmployeesDep2, actualDep2);
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
        //TODO home

    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
        //TODO home
    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
        List<Employee> listAll = company.getEmployeeByAge(0, 100);
        Employee[] actualAll = listAll.toArray(new Employee[]{});
        Arrays.sort(actualAll);
        assertArrayEquals(employees, actualAll);

        List<Employee> listEmpty = company.getEmployeeByAge(90, 100);
        assertTrue(listEmpty.isEmpty());

        List<Employee> list2_3 = company.getEmployeeByAge(getAge(DATE2), getAge(DATE1));
        Employee[] actual2_3 = list2_3.toArray(new Employee[]{});
        Employee[] expected2_3 = {empl2, empl3};
        Arrays.sort(actual2_3);
        assertArrayEquals(expected2_3, actual2_3);

    }

    @org.junit.jupiter.api.Test
    void salaryDistributionsByDepartments() {
        //TODO from classwork
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

    private int getAge(LocalDate birthdate) {
        //FIXME wrong JDK
        //int result = ChronoUnit.
        return 0;
    }
}