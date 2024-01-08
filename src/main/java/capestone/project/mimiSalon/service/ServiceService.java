package capestone.project.mimiSalon.service;

import capestone.project.mimiSalon.database.dao.ServiceDAO;
import capestone.project.mimiSalon.database.entity.Service;
import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.CreateServiceFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

@Slf4j
@org.springframework.stereotype.Service
public class ServiceService {

    private final ServiceDAO serviceDAO;

    @Autowired
    public ServiceService(ServiceDAO serviceDAO) {
        this.serviceDAO = serviceDAO;
    }

    public List<Service> getAllServiceServices() {
        return serviceDAO.findAll();
    }

    public Optional<Service>getAllServiceServicesById(Integer id) {
        return serviceDAO.findById(id);
    }

    public Service createOrUpdateSalonService(CreateServiceFormBean form) {
        log.debug("SalonService ID: " + form.getId());
        log.debug("Name: " + form.getName());
        log.debug("imageUrl: " + form.getImage_url());
        log.info("Price: " + form.getPrice());

        Service service = new Service();

        // If the form ID is not null, it means we are editing an existing SalonService
        if (form.getId() != null) {
            Optional<Service> existingSalonService = getAllServiceServicesById(form.getId());
            if (existingSalonService.isPresent()) {
                service = existingSalonService.get();
            }
        }

        // Set the properties from the form
        service.setName(form.getName());
        service.setImageUrl(form.getImage_url());
        service.setPrice(form.getPrice());

        // Save or update the SalonService
        return serviceDAO.save(service);
    }

    public void deleteSalonService(Long id) {
        serviceDAO.deleteById(id);
    }
    public List<Service> findAll() {
        return serviceDAO.findAll();
    }
    public List<Service> getAllServices() {
        return serviceDAO.findAll();
    }

    public Optional<Service> findById(Integer id) {
        return serviceDAO.findById(id);
    }
}
