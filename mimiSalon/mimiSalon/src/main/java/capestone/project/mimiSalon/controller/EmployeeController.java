package capestone.project.mimiSalon.controller;



import capestone.project.mimiSalon.database.dao.EmployeeDAO;
import capestone.project.mimiSalon.database.entity.Employee;
import capestone.project.mimiSalon.form.CreateEmployeeFormBean;
import capestone.project.mimiSalon.service.EmployeeService;


import io.micrometer.common.util.StringUtils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller

public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeService employeeeService;


    @GetMapping("/employee/search")
    public ModelAndView search(@RequestParam(required = false) String firstNameSearch,
                               @RequestParam(required = false) String lastNameSearch) {
        ModelAndView response = new ModelAndView("employee/search");

        log.debug("in the employee search controller method : first name = " + firstNameSearch + " last name = " + lastNameSearch);

        if (!StringUtils.isEmpty(firstNameSearch) || !StringUtils.isEmpty(lastNameSearch)) {

            response.addObject("firstNameSearch", firstNameSearch);
            response.addObject("lastNameSearch", lastNameSearch);

            if (!StringUtils.isEmpty(firstNameSearch)) {
                firstNameSearch = "%" + firstNameSearch + "%";
            }

            if (!StringUtils.isEmpty(lastNameSearch)) {
                lastNameSearch = "%" + lastNameSearch + "%";
            }

            // we only want to do this code if the user has entered either a first name or a last name
            List<Employee> employees = employeeDAO
                    .findByFirstNameandLastName(firstNameSearch, lastNameSearch);

            response.addObject("employeeVar", employees);


            for (Employee employee : employees) {
                log.debug("employee: id = " + employee.getId() + " last name = " + employee.getLastName());
            }
        }

        return response;
    }

    @GetMapping("/employee/create")
    public ModelAndView createEmployee() {
        ModelAndView response = new ModelAndView("employee/create");

        log.info("In create employee with no Args");
        return response;
    }


    @GetMapping("/employee/createSubmit")
    public ModelAndView createEmployeeSubmit(@Valid CreateEmployeeFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("employee/create");

        log.info("firstname:" + form.getFirstName());
        log.info("lastname:" + form.getLastName());
        log.info("phone:" + form.getPhone());
        log.info("speciality:" + form.getSpeciality());



        Employee employee = new Employee();

        employee.setFirstName(form.getFirstName());
        employee.setLastName(form.getLastName());
        employee.setPhone(form.getPhone());
        employee.setSpeciality(form.getSpeciality());


        //  customerDAO.save(customer);
        employeeDAO.save(employee);

        log.info(" In create customer with  Args");
        return response;
    }
}
