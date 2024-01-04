package capestone.project.mimiSalon.service;

import capestone.project.mimiSalon.database.dao.SalonServiceDAO;
import capestone.project.mimiSalon.database.entity.SalonService;
import capestone.project.mimiSalon.form.CreateSalonServiceFormBean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class SalonServiceService {

    private final SalonServiceDAO salonServiceDAO;

    @Autowired
    public SalonServiceService(SalonServiceDAO salonServiceDAO) {
        this.salonServiceDAO = salonServiceDAO;
    }

    public List<SalonService> getAllSalonServices() {
        return salonServiceDAO.findAll();
    }

    public Optional<SalonService> getSalonServiceById(Integer id) {
        return salonServiceDAO.findById(id);
    }

    public SalonService createOrUpdateSalonService(CreateSalonServiceFormBean form) {
        log.debug("SalonService ID: " + form.getId());
        log.debug("Name: " + form.getName());
        log.debug("Description: " + form.getDescription());
        log.info("Price: " + form.getPrice());

        SalonService salonService = new SalonService();

        // If the form ID is not null, it means we are editing an existing SalonService
        if (form.getId() != null) {
            Optional<SalonService> existingSalonService = getSalonServiceById(form.getId());
            if (existingSalonService.isPresent()) {
                salonService = existingSalonService.get();
            }
        }

        // Set the properties from the form
        salonService.setName(form.getName());
        salonService.setDescription(form.getDescription());
        salonService.setPrice(form.getPrice());

        // Save or update the SalonService
        return salonServiceDAO.save(salonService);
    }

    public void deleteSalonService(Long id) {
        salonServiceDAO.deleteById(id);
    }
}
