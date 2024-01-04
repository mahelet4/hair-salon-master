package capestone.project.mimiSalon.controller;

import capestone.project.mimiSalon.database.dao.SalonServiceDAO;
import capestone.project.mimiSalon.database.entity.SalonService;
import capestone.project.mimiSalon.service.SalonServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.util.List;

@Controller

@RequestMapping("/salon")
public class SalonServiceController {

    private final SalonServiceDAO salonServiceDAO;
    private final SalonServiceService salonServiceService;

    @Autowired
    public SalonServiceController(SalonServiceDAO salonServiceDAO, SalonServiceService salonServiceService) {
        this.salonServiceDAO = salonServiceDAO;
        this.salonServiceService = salonServiceService;
    }

    @GetMapping("/list")
    public String listSalonServices(Model model) {
        List<SalonService> salonServices = salonServiceService.getAllSalonServices();
        model.addAttribute("services", salonServices);
        return "salon-service-list"; // This should match your JSP view name
    }

    @GetMapping("/select")
    public String selectSalonService(Model model) {
        // Fetch the list of available salon services from the database
        List<SalonService> services = salonServiceDAO.findAll();

        // Add the list of services to the model
        model.addAttribute("services", services);

        return "service/select"; // Return the view for selecting a salon service
    }

    @GetMapping("/book")
    public String bookSalonService(@RequestParam("serviceId") Long serviceId, Model model) {
        // Fetch the selected salon service from the database
        SalonService selectedService = salonServiceDAO.findById(serviceId).orElse(null);

        if (selectedService == null) {
            // Handle error if the service is not found
            return "error/404"; // You can customize the error page
        }

        // Pass the selected service to the booking form
        model.addAttribute("selectedService", selectedService);

        return "booking/booking-form"; // Return the booking form
    }
}

