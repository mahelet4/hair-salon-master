package capestone.project.mimiSalon.database.dao;

import capestone.project.mimiSalon.database.entity.Booking;


import capestone.project.mimiSalon.database.entity.Service;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface BookingDAO extends JpaRepository<Booking, Long> {

    Optional<Booking> findById(Integer id); // Use Optional<Booking> here




    // Assuming Booking has a field named 'user' which is a reference to the User entity
    @Query("SELECT b FROM Booking b WHERE b.user.id = :userId")
    List<Booking> findByUserId(@Param("userId") Integer userId);

    // Assuming Booking has a field named 'service' which is a reference to the Service entity
    @Query("SELECT b FROM Booking b WHERE b.service.id = :serviceId")
    List<Booking> findByServiceId(Integer serviceId);

}

