package capestone.project.mimiSalon.database.dao;

import capestone.project.mimiSalon.database.entity.Booking;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookingDAO extends JpaRepository<Booking, Long> {

    List<Booking> findById(Integer id); // Use Optional<Booking> here

    List<Booking> findByCustomerName(String customerName);

    // Add your custom queries here using the @Query annotation
    @Query("SELECT b FROM Booking b WHERE b.status = :status")
    List<Booking> findByStatus(String status);

    @Query("SELECT b FROM Booking b WHERE b.service = :service")
    List<Booking> findByServiceId(String service);

}
