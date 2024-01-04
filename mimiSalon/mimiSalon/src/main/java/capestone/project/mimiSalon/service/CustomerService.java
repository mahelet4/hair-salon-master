package capestone.project.mimiSalon.service;


import capestone.project.mimiSalon.database.dao.CustomerDAO;
import capestone.project.mimiSalon.database.entity.Customer;
import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateCustomerFormBean;


import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CustomerService {

    @Autowired
    private CustomerDAO customerDao;

    @Autowired
    private AuthenticatedUserService authenticatedUserService;

    public Customer createCustomer(CreateCustomerFormBean form) {
        log.debug("id: " + form.getId());
        log.debug("firstName: " + form.getFirstName());
        log.info("lastName: " + form.getLastName());
        log.info("phone: " + form.getPhone());
        log.info("city: " + form.getCity());

        // if the form.id is null then this is a create - if it is not null then it is an edit
        // first we attempt to load it from the database ( basically we assume that it is going to be an edit )
        // if it was found in the database we know the incoming id was valid so we can edit it
        Customer customer = customerDao.findById(form.getId());

        // if the customer is null then we know that this is a create and we have to make a new object
        if ( customer == null ) {
            customer = new Customer();

            // these lien of code loads the current logged in user record from the database
         User user = authenticatedUserService.loadCurrentUser();
            // then we can set the user id onto the customer record we are about to create
            // I am doing here because I only want to update the userid on the customer when it is being created
            //customer.setUserId(user.getId());

            customer.setUser(user);

        }

        // set the incoming values to be save to the database
        customer.setFirstName(form.getFirstName());
        customer.setLastName(form.getLastName());
        customer.setPhone(form.getPhone());
        customer.setCity(form.getCity());


        // if you want to use your own images, put them in the /pub/images folder
        // when editing the customer enter /pub/images/imagename.gif as your image url in the database to display your own image
        // alternatively you can give a full url of an image somewhere on the internet


        return customerDao.save(customer);
    }
}