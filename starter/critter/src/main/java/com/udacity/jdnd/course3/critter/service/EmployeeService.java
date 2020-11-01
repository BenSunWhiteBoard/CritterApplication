package com.udacity.jdnd.course3.critter.service;

import com.udacity.jdnd.course3.critter.entity.Employee;
import com.udacity.jdnd.course3.critter.repository.EmployeeRepository;
import java.time.DayOfWeek;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import org.springframework.stereotype.Service;

@Service
public class EmployeeService {

    private EmployeeRepository employeeRepository;

    public EmployeeService(
            EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee saveEmployee(Employee employee){
        return employeeRepository.save(employee);
    }

    public Employee getEmployeeById(Long id){
        Optional<Employee> result = employeeRepository.findById(id);
        return result.orElse(null);
    }

    public List<Employee> getAllEmployee(){
        return employeeRepository.findAll();
    }

    public Boolean setAvailability(Set<DayOfWeek> daysAvailable, Long id){
        Optional<Employee> result = employeeRepository.findById(id);
        if(result.isPresent()){
            Employee employee = result.get();
            employee.setDaysAvailable(daysAvailable);
            return true;
        }else {
            return false;
        }
    }
}
