package capestone.project.mimiSalon.service;

import capestone.project.mimiSalon.database.dao.EmployeeDAO;
import capestone.project.mimiSalon.database.entity.Employee;
import capestone.project.mimiSalon.form.CreateEmployeeFormBean;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeService {

    @Autowired
    private EmployeeDAO employeeDao;


    public Employee createEmployee(CreateEmployeeFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("firstName: " + form.getFirstName());
        log.info("lastName: " + form.getLastName());
        log.info("phone: " + form.getPhone());
        log.info("speciality: " + form.getSpeciality());

        Employee employee = employeeDao.findById(form.getId());


        if ( employee == null ) {
            employee = new Employee();

        }

        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setPhone(form.getPhone());
        employee.setSpeciality(form.getSpeciality());


        return employeeDao.save(employee);
    }
}


