package telran.company.service;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

public class CompanyServiceImpl implements CompanyService{
    HashMap<Long,Employee> employeesMap = new HashMap<>();
    HashMap<String, Set<Employee>> employeesDepartment = new HashMap<>();
    TreeMap<Integer, Set<Employee>> employeesSalary = new TreeMap<>();
    TreeMap<LocalDate,Set<Employee>> employeesAge = new TreeMap<>();
    @Override
    /*
    adds new Employees into a company
    in the case an employee with the given ID alredy exist
    the Exception IllegalStateException must be thrown
    returns reference to the being added
     */
    public Employee hireEmployee(Employee empl) {
        // TODO
        return null;
    }

    @Override
    /*
    remove Employee object from company according to a given ID
    In the case Employee with the given ID does not exist
    the method must throw IllegalStateException
     */
    public Employee fireEmployee(long id) {
        return null;
    }

    @Override
    /*
    returns reference to Employee object with a given ID value
    In the case Employee with the ID does not exist
    the method null
     */
    public Employee getEmployee(long id) {
        return null;
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
