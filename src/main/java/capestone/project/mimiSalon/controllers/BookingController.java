package capestone.project.mimiSalon.controllers;

import capestone.project.mimiSalon.database.dao.EmployeeDAO;
import capestone.project.mimiSalon.database.dao.ServiceDAO;
import capestone.project.mimiSalon.database.dao.UserDAO;
import capestone.project.mimiSalon.database.entity.Booking;
import capestone.project.mimiSalon.database.entity.Employee;
import capestone.project.mimiSalon.database.entity.Service;
import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateBookingFormBean;
import capestone.project.mimiSalon.service.BookingService;
import capestone.project.mimiSalon.service.EmployeeService;
import capestone.project.mimiSalon.service.ServiceService;
import capestone.project.mimiSalon.service.UserService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Controller
@RestController
@RequestMapping
public class BookingController {

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private EmployeeDAO employeeDAO;

    @Autowired
    private EmployeeService employeeService;

    private final BookingService bookingService;
    private final UserService userService; // Assuming you have a service for Users
    private final ServiceService serviceService; // Assuming you have a service for Services


    @Autowired
    public BookingController(BookingService bookingService, UserService userService, ServiceService serviceService, EmployeeService employeeService) {
        this.bookingService = bookingService;
        this.userService = userService;
        this.serviceService = serviceService;
        this.employeeService = employeeService;
    }


    @GetMapping("/booking/create")
    public ModelAndView createBookingForm(HttpSession session) {
        ModelAndView modelAndView = new ModelAndView("booking/create");

        // Retrieve the selectedServices session attribute as a String
        String selectedServiceIds = (String) session.getAttribute("selectedServices");

        // Parse the comma-separated string into a List of Longs
        List<Long> selectedServices = new ArrayList<>();
        if (selectedServiceIds != null && !selectedServiceIds.isEmpty()) {
            String[] idsArray = selectedServiceIds.split(",");
            for (String id : idsArray) {
                selectedServices.add(Long.parseLong(id));
            }
        }

        // Add other data as needed
        List<User> users = userService.findAll();
        modelAndView.addObject("users", users);

        List<Employee> employees = employeeService.findAll(); // Use employeeService here
        modelAndView.addObject("employees", employees);

        modelAndView.addObject("selectedServices", selectedServices);
        modelAndView.addObject("form", new CreateBookingFormBean()); // Form backing object

        return modelAndView;
    }




    @GetMapping("/edit/{bookingId}")
    public ModelAndView editBookingForm(@PathVariable Long bookingId) {
        // Fetch the booking details by bookingId from your data source
        // Populate the form with the booking details
        Booking booking = bookingService.getBookingById(bookingId);

        ModelAndView modelAndView = new ModelAndView("booking/service");
        List<Service> services = serviceService.getAllServices();
        modelAndView.addObject("services", services);
        modelAndView.addObject("form", new CreateBookingFormBean()); // Form backing object
        modelAndView.addObject("editBooking", booking); // Add the booking to be edited
        return modelAndView;
    }

    @GetMapping("booking/search")
    public ModelAndView searchBookingById(@RequestParam("bookingId") Optional<Long> bookingId) {
        ModelAndView response = new ModelAndView("booking/search");

        bookingId.ifPresent(id -> {
            Booking booking = bookingService.getBookingById(id);
            if (booking != null) {
                response.addObject("booking", booking);
            } else {
                response.addObject("notFound", true);
            }
        });

        return response;
    }

    @GetMapping("/detail")
    public ModelAndView bookingDetail(@RequestParam Integer id) {
        ModelAndView response = new ModelAndView("booking/detail");

        Optional<Booking> bookingOptional = bookingService.findById(id);

        if (!bookingOptional.isPresent()) {
            log.warn("Booking with id " + id + " was not found");
            response.setViewName("redirect:/error/404");
            return response;
        }

        response.addObject("booking", bookingOptional.get());
        return response;
    }
    // Populate the form bean from the Booking entity
    private void populateFormBean(CreateBookingFormBean form, Booking booking) {
        form.setId(booking.getId());
        form.setUser_id(booking.getUser().getId());
        form.setService_id(booking.getService().getId());
        form.setAppointment_time(booking.getAppointmentTime());
        form.setEmployee_id(booking.getEmployee().getId());
        // ... other fields ...
    }

    // Add lists of users, services, and employees to the model
    private void addDropdownAttributes(ModelAndView modelAndView) {
        modelAndView.addObject("users", userService.findAll());
        modelAndView.addObject("services", serviceService.findAll());
        modelAndView.addObject("employees", employeeService.findAll());
    }
    @GetMapping("/booking/createSubmit")
    public ModelAndView createBookingSubmit(@ModelAttribute("form") CreateBookingFormBean form, BindingResult result) {
        ModelAndView response = new ModelAndView();

        if (result.hasErrors()) {
            // Handle errors
            response.setViewName("booking/create");
            addDropdownAttributes(response); // Re-populate dropdowns
            return response;
        }

        Booking booking = convertFormToEntity(form);

        bookingService.createOrUpdateBooking(form); // Adjusted to use your existing method

        response.addObject("successMessage", "Booking created successfully!");
        response.setViewName("redirect:/booking/success"); // or any other success view

        return response;
    }

    private Booking convertFormToEntity(CreateBookingFormBean form) {
        Booking booking = new Booking();
        // Populate booking entity with form data
        booking.setUser(userService.findById(form.getUser_id()).orElse(null));
        booking.setService(serviceService.findById(form.getService_id()).orElse(null));
        booking.setEmployee(employeeService.findById(form.getEmployee_id()).orElse(null));
        booking.setAppointmentTime(form.getAppointment_time());
        // Set other fields as necessary
        return booking;
    }
    @PostMapping("/complete")
    public String completeBooking(@ModelAttribute("bookingForm") CreateBookingFormBean bookingForm,
                                  @SessionAttribute("selectedServices") List<Service> selectedServices,
                                  RedirectAttributes redirectAttributes) {
        // Access the selected service details from the session (selectedServices)
        // Access the appointment time and other booking information from bookingForm

        // Save the booking details in your database
        // You can use the selectedServices, appointment time, and other booking information

        // After completing the booking, you can display a success message or redirect to a confirmation page
        return "booking/confirmation";
    }


}
