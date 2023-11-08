package telran.company.test;

import java.time.temporal.*;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;
import telran.company.service.CompanyService;
import telran.company.service.CompanyServiceImpl;

import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@TestMethodOrder(MethodOrderer.OrderAnnotation.class) // порядок проведения тестов
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
    private static final String FILE_PATH = "test.data";

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
        /* поместили код в метод
        //    делаем из листов массивы так как метод делает листы
        Employee[] actualDep1 = list1.toArray(new Employee[]{});
        Employee[] actualDep2 = list2.toArray(new Employee[]{});
        // сортируем актуальные массивы по id чтобы потом их сравнить с ожидаемыми массивами в assertArrays
        Arrays.sort(actualDep1);
        Arrays.sort(actualDep2);
        //?

        //?
        assertArrayEquals(expectedEmployeesDep1, actualDep1);
        assertArrayEquals(expectedEmployeesDep2, actualDep2);
         */
        assertTrue(company.getEmployeesByDepartment(DEPARTMENT99).isEmpty());
        // новый метод
        runListTest(expectedEmployeesDep1, list1);
        runListTest(expectedEmployeesDep2, list2);
    }

    @org.junit.jupiter.api.Test
    void getAllEmployees() {
        /*
        List<Employee> actualList = company.getAllEmployees();
        Employee[] actualArray1 = actualList.toArray(new Employee[]{});
        Arrays.sort(actualArray1);
        assertArrayEquals(employees,actualArray1);
         */
        runListTest(employees, company.getAllEmployees());

        assertNull(company.getAllEmployees());
    }

    @org.junit.jupiter.api.Test
    void getEmployeesBySalary() {
        /*
        List<Employee> actualList = company.getEmployeesBySalary(4000, 1000);
        Employee[] actualArray = actualList.toArray(new Employee[]{});
        Arrays.sort(actualArray);
        assertArrayEquals(employees, actualArray);

        List<Employee> actualList1 = company.getEmployeesBySalary(0, 4000);
        assertTrue(actualList1.isEmpty());

         */
        runListTest(employees, company.getEmployeesBySalary(0, Integer.MAX_VALUE));
        //           пустой массив
        runListTest(new Employee[0], company.getEmployeesBySalary(10000, Integer.MAX_VALUE));
        //  создаем новый массив с элементами
        runListTest(new Employee[]{empl1, empl2}, company.getEmployeesBySalary(SALARY1, SALARY3));

    }

    @org.junit.jupiter.api.Test
    void getEmployeeByAge() {
        /*
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

         */
        runListTest(employees, company.getEmployeeByAge(0,100));
        runListTest(new Employee[0], company.getEmployeeByAge(90,100));
        runListTest(new Employee[]{empl2,empl3}, company.getEmployeeByAge(getAge(DATE3),getAge(DATE1)));


    }

    @org.junit.jupiter.api.Test
    void salaryDistributionsByDepartments() {
        DepartmentAvgSalary[] expectedDistribution = {
                new DepartmentAvgSalary(DEPARTMENT1, (SALARY1 + SALARY2) / 2),
                new DepartmentAvgSalary(DEPARTMENT2, (SALARY3 + SALARY4) / 2),
                new DepartmentAvgSalary(DEPARTMENT3, SALARY5)
        };
        List<DepartmentAvgSalary> listDistribution = company.salaryDistributionsByDepartments();
        DepartmentAvgSalary[] actualDistribution = listDistribution.toArray(new DepartmentAvgSalary[]{});
        Arrays.sort(actualDistribution);
        assertArrayEquals(expectedDistribution, actualDistribution);
    }

    @org.junit.jupiter.api.Test
    void getSalaryDistribution() {
        int interval = 2000;
        List<SalaryIntervalDistribution> distribution = company.getSalaryDistribution(2000);
        SalaryIntervalDistribution[] expectedDistribution = {
                new SalaryIntervalDistribution(SALARY1,SALARY1+interval,2),
                new SalaryIntervalDistribution(SALARY3,SALARY3+interval, 2),
                new SalaryIntervalDistribution(SALARY5,SALARY5+interval,1)
        };
        assertArrayEquals(expectedDistribution,distribution.toArray(new SalaryIntervalDistribution[0]));

    }

    @org.junit.jupiter.api.Test
    void updateDepartment() {
        assertEquals(empl2,company.updateDepartment(ID2, DEPARTMENT2));
        runListTest(new Employee[]{empl1},company.getEmployeesByDepartment(DEPARTMENT1));
        runListTest(new Employee[]{empl2,empl3,empl4},company.getEmployeesByDepartment(DEPARTMENT2));

    }

    @org.junit.jupiter.api.Test
    void updateSalary() {
        assertEquals(empl2, company.updateSalary(ID2,SALARY3));
        runListTest(new Employee[]{empl1},company.getEmployeesBySalary(SALARY1,SALARY3));
        runListTest(new Employee[]{empl2,empl3,empl4}, company.getEmployeesBySalary(SALARY3,SALARY5));

    }

    @org.junit.jupiter.api.Test
    @Order(1)
    void save() {
        company.save(FILE_PATH);// просто сохранил проверим дальше
    }

    @org.junit.jupiter.api.Test
    @Order(2)
    void restore() {
        CompanyService companySaved = new CompanyServiceImpl();
        companySaved.restore(FILE_PATH);
        runListTest(employees, companySaved.getAllEmployees());
    }

    private int getAge(LocalDate birthdate) {
        int result = (int) ChronoUnit.YEARS.between(birthdate, LocalDate.now());
        return result;
    }

    private void runListTest(Employee[] expected, List<Employee> list) {
        Employee[] actual = list.toArray(new Employee[]{});
        Arrays.sort(actual);
        assertArrayEquals(expected, actual);
    }
}