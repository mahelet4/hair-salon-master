package capestone.project.mimiSalon.service;

import capestone.project.mimiSalon.database.dao.EmployeeDAO;

import capestone.project.mimiSalon.database.entity.Employee;
import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateEmployeeFormBean;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDAO;


    public Optional<Employee> findEmployeeById(Integer id) {
        return employeeDAO.findById(id);
    }


    public Employee createEmployee(CreateEmployeeFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("name: " + form.getName());
        log.info("speciality: " + form.getSpeciality());

        // Use Optional to handle the case where an employee might not be found
        Employee employee = employeeDAO.findById(form.getId()).orElse(null);

        // If employee doesn't exist, create a new one
        if (employee == null) {
            employee = new Employee();
        }

        employee.setName(form.getName());
        employee.setSpeciality(form.getSpeciality());

        return employeeDAO.save(employee);

    }


    public List<Employee> findAll() {
        return employeeDAO.findAll();
    }
    public Optional<Employee> findById(Integer id) {
        return employeeDAO.findById(id);
    }
}

