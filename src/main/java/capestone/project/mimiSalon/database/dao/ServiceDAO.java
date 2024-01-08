package capestone.project.mimiSalon.database.dao;



import capestone.project.mimiSalon.database.entity.Service;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;



public interface ServiceDAO extends JpaRepository<Service, Long> {

    Optional<Service> findById(Integer id);

    List<Service> findByName(String name);
    List<Service> findAll();

    // Corrected query assuming the entity is named 'Service' and has a 'price' field
    @Query("SELECT s FROM Service s WHERE s.price <= :maxPrice")
    List<Service> findByMaxPrice(double maxPrice);


}
