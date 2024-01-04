package capestone.project.mimiSalon.controller;


import capestone.project.mimiSalon.database.dao.CustomerDAO;
import capestone.project.mimiSalon.database.entity.Customer;

import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateCustomerFormBean;
import capestone.project.mimiSalon.service.AuthenticatedUserService;
import capestone.project.mimiSalon.service.CustomerService;

import io.micrometer.common.util.StringUtils;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

import org.apache.tomcat.util.http.fileupload.IOUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.util.List;

@Slf4j
@Controller

public class CustomerController {

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private CustomerService customerService;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    @GetMapping("/customer/search")
    public ModelAndView search(@RequestParam(required = false) String firstNameSearch,
                               @RequestParam(required = false) String lastNameSearch) {
        ModelAndView response = new ModelAndView("customer/search");

        log.debug("in the customer search controller method : first name = " + firstNameSearch + " last name = " + lastNameSearch);

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
            List<Customer> customers = customerDao.findByFirstNameOrLastName(firstNameSearch, lastNameSearch);

            response.addObject("customerVar", customers);


            for (Customer customer : customers) {
                log.debug("customer: id = " + customer.getId() + " last name = " + customer.getLastName());
            }
        }

        return response;
    }

//    @GetMapping("/customer/delete/{customerId}")
//    public ModelAndView deleteCustomer(@PathVariable int customerId) {
//        ModelAndView response = new ModelAndView("customer/search");
//
//        Customer customer = customerDao.findById(customerId);
//
//        if ( customer != null ) {
//            customerDao.delete(customer);
//        } else {
//            log.warn("Customer with id " + customerId + " was not found") ;
//        }
//
//        return response;
//    }

    @GetMapping("/customer/edit/{customerId}")
    public ModelAndView editCustomer(@PathVariable int customerId, @RequestParam(required = false) String success) {
        log.info("######################### In /customer/edit #########################");
        ModelAndView response = new ModelAndView("customer/create");

        Customer customer = customerDao.findById(customerId);

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }

        CreateCustomerFormBean form = new CreateCustomerFormBean();

        if (customer != null) {
            form.setId(customer.getId());
            form.setFirstName(customer.getFirstName());
            form.setLastName(customer.getLastName());
            form.setPhone(customer.getPhone());
            form.setCity(customer.getCity());

        } else {
            log.warn("Customer with id " + customerId + " was not found");
        }

        response.addObject("form", form);

        return response;

    }

    @GetMapping("/customer/create")
    public ModelAndView createCustomer() {
        ModelAndView response = new ModelAndView("customer/create");

        log.debug("In create customer with no args - log.debug");
        log.info("In create customer with no args - log.info");

        return response;
    }


    // the action attribute on the form tag is set to /customer/createSubmit so this method will be called when the user clicks the submit button
    @GetMapping("/customer/createSubmit")
    public ModelAndView createCustomerSubmit(@Valid CreateCustomerFormBean form, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            log.info("######################### In create customer submit - has errors #########################");
            ModelAndView response = new ModelAndView("customer/create");

            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("error: " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("errors", bindingResult);
            return response;
        }

        log.info("######################### In create customer submit - no error found #########################");

        Customer c = customerService.createCustomer(form);

        // the view name can either be a jsp file name or a redirect to another controller method
        ModelAndView response = new ModelAndView();
        response.setViewName("redirect:/customer/edit/" + c.getId() + "?success=Customer Saved Successfully");

        return response;


    }

    @GetMapping("/customer/myCustomers")
    public void myCustomers() {
        log.info("######################### In my customers #########################");

        // 1) Use the authenticated user service to find the logged in user
        User user = authenticatedUserService.loadCurrentUser();

        // 2) Create a DAO method that will find by userId
        // 3) use the authenticated user id to find a list of all customers created by this user
        List<Customer> customers = customerDao.findByUserId(user.getId());

        // 4) loop over the customers created and log.debug the customer id and customer last name
        for (Customer customer : customers) {
            log.debug("customer: id = " + customer.getId() + " last name = " + customer.getLastName());
        }

    }

    @RequestMapping("/customer/detail")
    public ModelAndView detail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("customer/detail");

        Customer customer = customerDao.findById(id);

        if (customer == null) {
            log.warn("Customer with id " + id + " was not found");
            // in a real application you might redirect to a 404 here because the custoemr was nto found
            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("customer", customer);

        return response;
    }
    @GetMapping("/customer/fileupload")
    public ModelAndView fileUpload(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("customer/fileupload");

        Customer customer = customerDao.findById(id);
        response.addObject("customer", customer);

        log.info(" In fileupload with no Args");
        return response;
    }

    @PostMapping("/customer/fileUploadSubmit")
    public ModelAndView fileUploadSubmit(@RequestParam("file") MultipartFile file,
                                         @RequestParam Integer id) {
        ModelAndView response = new ModelAndView("redirect:/customer/detail?id=" + id);

        log.info("Filename = " + file.getOriginalFilename());
        log.info("Size     = " + file.getSize());
        log.info("Type     = " + file.getContentType());


        // Get the file and save it somewhere
        File f = new File("./src/main/webapp/pub/images/" + file.getOriginalFilename());
        try (OutputStream outputStream = new FileOutputStream(f.getAbsolutePath())) {
            IOUtils.copy(file.getInputStream(), outputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // these 3 lines of code will load the customer by the id passed in
        // update the image url field and then save the customer to the database
        Customer customer = customerDao.findById(id);

        customerDao.save(customer);

        return response;
    }
}
