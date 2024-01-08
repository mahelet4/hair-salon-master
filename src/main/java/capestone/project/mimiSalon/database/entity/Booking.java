package capestone.project.mimiSalon.database.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;



import java.util.Date;

@Getter
@Setter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user; // Change to User entity

    @ManyToOne
    @JoinColumn(name = "service_id", referencedColumnName = "id")
    private Service service; // Change to Service entity

    @Column(name = "appointment_time")
    private Date appointmentTime;

    @ManyToOne
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private Employee employee; // Change to Employee entity
}