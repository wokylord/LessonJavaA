package telran.company.service;

import telran.company.dto.DepartmentAvgSalary;
import telran.company.dto.Employee;
import telran.company.dto.SalaryIntervalDistribution;

import java.util.List;

public class CompanyServiceImpl implements CompanyService{
    @Override
    public Employee hireEmployee(Employee empl) {
        // TODO
        return null;
    }

    @Override
    public Employee fireEmployee(long id) {
        return null;
    }

    @Override
    public Employee getEmployee(long id) {
        return null;
    }

    @Override
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
