package capestone.project.mimiSalon.controllers;



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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeDAO employeeDAO;

    @GetMapping("/search")
    public ModelAndView search(@RequestParam(required = false) String nameSearch) {
        ModelAndView response = new ModelAndView("employee/search");
        log.debug("In the employee search controller method: name = " + nameSearch);

        if (!StringUtils.isEmpty(nameSearch)) {
            String adjustedNameSearch = "%" + nameSearch.trim() + "%";

            // Retrieve employees matching the search term
            List<Employee> employees = employeeDAO.findByName(adjustedNameSearch);
            response.addObject("employeeVar", employees);

            if (employees.isEmpty()) {
                log.debug("No employees found for the search term: " + nameSearch);
                response.addObject("noResultsFound", true);
            } else {
                log.debug("Number of employees found: " + employees.size());
            }
        } else {
            response.addObject("noResultsFound", true);
        }

        return response;
    }

    @GetMapping("/create")
    public ModelAndView createEmployee() {
        ModelAndView response = new ModelAndView("employee/create");
        log.info("In create employee with no Args");
        response.addObject("form", new CreateEmployeeFormBean());
        return response;
    }

    @PostMapping("/createSubmit")
    public ModelAndView createEmployeeSubmit(@Valid CreateEmployeeFormBean form, BindingResult bindingResult) {
        ModelAndView response = new ModelAndView("employee/create");

        // Handling form validation errors
        if (bindingResult.hasErrors()) {
            StringBuilder errorMessageBuilder = new StringBuilder();
            bindingResult.getAllErrors().forEach(error -> {
                errorMessageBuilder.append(error.getDefaultMessage()).append("<br>");
            });

            // Adding the accumulated error messages to the ModelAndView
            response.addObject("errorMessage", errorMessageBuilder.toString());
            response.addObject("form", form); // Add form back to the view
            return response;
        }

        log.info("name:" + form.getName());
        log.info("speciality:" + form.getSpeciality());

        // Check if employee already exists
        List<Employee> existingEmployees = employeeDAO.findByName(form.getName());
        if (!existingEmployees.isEmpty()) { // Use isEmpty() to check if the list is not empty
            response.addObject("errorMessage", "An employee with this name already exists.");
            response.addObject("form", form); // Add form back to the view
            return response;
        }

        // Creating and saving a new employee
        Employee employee = new Employee();
        employee.setName(form.getName());
        employee.setSpeciality(form.getSpeciality());
        employeeDAO.save(employee);

        // Redirecting or informing of successful submission
        response.addObject("successMessage", "Employee created successfully!");
        response.addObject("form", new CreateEmployeeFormBean()); // Reset the form
        return response;
    }


    @GetMapping("/edit/{id}")
    public ModelAndView editEmployee(@PathVariable Long id) {
        ModelAndView response = new ModelAndView("employee/create");
        Optional<Employee> employeeOptional = employeeDAO.findById(id);

        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            CreateEmployeeFormBean form = new CreateEmployeeFormBean();

            form.setId(employee.getId());
            form.setName(employee.getName());
            form.setSpeciality(employee.getSpeciality());

            response.addObject("form", form);
        } else {
            log.warn("Employee with id " + id + " was not found");
            response.addObject("errorMessage", "Employee not found");
            // Optionally, redirect to a different view or show an error message
        }

        return response;
    }
}




