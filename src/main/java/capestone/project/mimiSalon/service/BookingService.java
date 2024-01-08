package capestone.project.mimiSalon.service;


import capestone.project.mimiSalon.database.dao.BookingDAO;
import capestone.project.mimiSalon.database.dao.EmployeeDAO;
import capestone.project.mimiSalon.database.dao.ServiceDAO;
import capestone.project.mimiSalon.database.dao.UserDAO;
import capestone.project.mimiSalon.database.entity.Booking;
import capestone.project.mimiSalon.database.entity.Employee;
import capestone.project.mimiSalon.database.entity.Service;
import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateBookingFormBean;

import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;



import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
public class BookingService {

    @Autowired
    private BookingDAO bookingDAO;

    @Autowired
    private UserDAO userDAO;

    @Autowired
    private ServiceDAO serviceDAO;

    @Autowired
    private EmployeeDAO employeeDao;

    @Autowired
    private UserService userService; // UserService to fetch User entities

    @Autowired
    private ServiceService serviceService; // ServiceService to fetch Service entities

    @Autowired
    private EmployeeService employeeService; // EmployeeService to fetch Employee entities

    public Booking createOrUpdateBooking(CreateBookingFormBean form) {
        log.debug("user_id: " + form.getUser_id());
        log.info("service_id: " + form.getService_id());
        log.info("appointment_time: " + form.getAppointment_time());
        log.info("employee_id: " + form.getEmployee_id());

        // Fetching the actual entities
        User user = userDAO.findById(form.getUser_id()).orElse(null);
        Service service = serviceDAO.findById(form.getService_id()).orElse(null);
        Employee employee = employeeDao.findById(form.getEmployee_id()).orElse(null);

        // Create a Booking entity and populate it with the fetched entities
        Booking booking = new Booking();
        booking.setUser(user);
        booking.setService(service);
        booking.setAppointmentTime(form.getAppointment_time());
        booking.setEmployee(employee);

        // Save the booking using your BookingDAO
        return bookingDAO.save(booking);
    }

    public Booking getBookingById(Long id) {
        return bookingDAO.findById(id).orElse(null);
    }

    public List<Booking> findByUserId(Integer user_id) {
        // Assuming you have a method in BookingDAO to find bookings by name
        return bookingDAO.findByUserId(user_id);
    }
    public Optional<Booking> findById(Integer id) {
        // Assuming you have a method in BookingDAO to find bookings by name
        return bookingDAO.findById(id);
    }

}
