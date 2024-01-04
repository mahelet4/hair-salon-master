package capestone.project.mimiSalon.database.dao;



import capestone.project.mimiSalon.database.entity.SalonService;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;



public interface SalonServiceDAO extends JpaRepository<SalonService, Long> {

    Optional<SalonService> findById(Integer id);

    List<SalonService> findByName(String name);

    // Add your custom queries here using the @Query annotation
    @Query("SELECT ss FROM SalonService ss WHERE ss.price <= :maxPrice")
    List<SalonService> findByMaxPrice(double maxPrice);

    @Query("SELECT ss FROM SalonService ss WHERE ss.description LIKE %:keyword%")
    List<SalonService> findByDescriptionContaining(String keyword);
}