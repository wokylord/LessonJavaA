package telran.company.service;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

import java.time.LocalDate;
import java.util.*;

public class CompanyServiceImpl implements CompanyService {
    HashMap<Long, Employee> employeesMap = new HashMap<>();
    /***********************************************************/
    HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
    //key - department, value- Set of employees working in the department
    /*************************************************************/
    TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
    //key - salary, value - set of employees having the salary value
    /****************************************************************/
    TreeMap<LocalDate, Set<Employee>> employeesAge = new TreeMap<>();
    //key birth date; value set of employees born at the date

    /*******************************************************************/
    @Override
    /*
    adds new Employees into a company
    in the case an employee with the given ID alredy exist
    the Exception IllegalStateException must be thrown
    returns reference to the being added
     */
    public Employee hireEmployee(Employee empl) {
        long id = empl.id();
        if (employeesMap.containsKey(id)) {
            throw new IllegalStateException("Employee already exists " + id);
        }
        employeesMap.put(id, empl);
        addEmployeeSalary(empl);
        addEmployeeDepartment(empl);
        addEmployeeAge(empl);
        return empl;
    }

    private void addEmployeeAge(Employee empl) {
        LocalDate birthdate = empl.birthdate();
        Set<Employee> set =
                employeesAge.computeIfAbsent(birthdate, k -> new HashSet<>());
        set.add(empl);
    }

    private void addEmployeeDepartment(Employee empl) {
        String department = empl.department();
//		Set<Employee> set = employeesDepartment.computeIfAbsent(department, k -> new HashSet<>());
//		set.add(empl);
        Set<Employee> set = employeesDepartment.get(department);
        if (set == null) {
            set = new HashSet<>();
            employeesDepartment.put(department, set);
        }
        set.add(empl);

    }

    private void addEmployeeSalary(Employee empl) {
        employeesSalary.computeIfAbsent(empl.salary(), k -> new HashSet<>())
                .add(empl);

    }

    @Override
    /*
    remove Employee object from company according to a given ID
    In the case Employee with the given ID does not exist
    the method must throw IllegalStateException
     */
    public Employee fireEmployee(long id) {
        Employee empl = employeesMap.remove(id);
        if(empl == null) {
            throw new IllegalStateException("Employee not found " + id);
        }
        removeEmployeesDepartment(empl);
        removeEmployeesSalary(empl);
        removeEmployeesAge(empl);
        return empl;
    }
    private void removeEmployeesAge(Employee empl) {
        LocalDate birthDate = empl.birthdate();
        Set<Employee> set = employeesAge.get(birthDate);
        set.remove(empl); //removing reference to being
        //removed
        //employee from the set
        // of employees with the given birth date
        if (set.isEmpty()) {
            employeesAge.remove(birthDate);
        }

    }

    private void removeEmployeesSalary(Employee empl) {
        int salary = empl.salary();
        Set<Employee> set = employeesSalary.get(salary);
        set.remove(empl);
        if(set.isEmpty()) {
            employeesSalary.remove(salary);
        }

    }

    private void removeEmployeesDepartment(Employee empl) {
        String department = empl.department();
        Set<Employee> set = employeesDepartment.get(department);
        set.remove(empl);
        if(set.isEmpty()) {
            employeesDepartment.remove(department);
        }

    }

    @Override
    /*
    returns reference to Employee object with a given ID value
    In the case Employee with the ID does not exist
    the method null
     */
    public Employee getEmployee(long id) {
        return employeesMap.get(id);
    }

    @Override
    /*
    returns list of employee objects working in a given department
    in the case none employees in the department, the method returns method list
     */
    public List<Employee> getEmployeesByDepartment(String department) {
        return null;
    }

    @Override
    public List<Employee> getAllEmployees() {
        return null;
    }

    @Override
    public List<Employee> getEmployeesBySalary(int salaryFrom, int salaryTo) {
        return null;
    }

    @Override
    public List<Employee> getEmployeeByAge(int fromAge, int ageTo) {
        return null;
    }

    @Override
    public List<DepartmentAvgSalary> salaryDistributionsByDepartments() {
        return null;
    }

    @Override
    public List<SalaryIntervalDistribution> getSalaryDistribution(int interval) {
        return null;
    }

    @Override
    public Employee updateDepartment(long id, String newDepartment) {
        return null;
    }

    @Override
    public Employee updateSalary(long id, int newSalary) {
        return null;
    }

    @Override
    public void save(String filePath) {

    }

    @Override
    public void restore(String filePath) {

    }
}
