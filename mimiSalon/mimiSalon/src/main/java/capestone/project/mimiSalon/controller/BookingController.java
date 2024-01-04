package capestone.project.mimiSalon.controller;






import capestone.project.mimiSalon.database.entity.Booking;
import capestone.project.mimiSalon.form.CreateBookingFormBean;
import capestone.project.mimiSalon.service.BookingService;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@RestController

@RequestMapping("/booking")
public class BookingController {

    private final BookingService bookingService;

    @Autowired
    public BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @GetMapping("/create")
    public ModelAndView createBookingForm() {
        ModelAndView response = new ModelAndView("booking/create");
        response.addObject("form", new CreateBookingFormBean());
        return response;
    }

    // Handle the submission of the booking form
    @PostMapping("/bookingSubmit")
    public String createBookingSubmit(@ModelAttribute("form") CreateBookingFormBean form) {
        // Use CreateBookingFormBean to create a Booking entity
        Booking booking = new Booking();
        booking.setCustomerName(form.getCustomerName());
        booking.setService(form.getService());
        booking.setDate(form.getDate());
        booking.setStatus(form.getStatus());

        // Save the booking using your BookingService
        bookingService.createOrUpdateBooking(form);

        // Redirect to a success page or display a success message
        return "redirect:/booking/create"; // You can customize the success page URL
    }
    @GetMapping("/edit/{bookingId}")
    public ModelAndView editBooking(@PathVariable Long bookingId, @RequestParam(required = false) String success) {
        log.info("######################### In /booking/edit #########################");
        ModelAndView response = new ModelAndView("booking/edit");

        Booking booking = bookingService.getBookingById(bookingId);

        if (!StringUtils.isEmpty(success)) {
            response.addObject("success", success);
        }

        CreateBookingFormBean form = new CreateBookingFormBean();

        if (booking != null) {
            form.setId(booking.getId());
            form.setCustomerName(booking.getCustomerName());
            form.setService(booking.getService());
            form.setDate(booking.getDate());
            form.setStatus(booking.getStatus());
            // Add more fields as needed
        } else {
            log.warn("Booking with id " + bookingId + " was not found");
        }

        response.addObject("form", form);

        return response;
    }

}
