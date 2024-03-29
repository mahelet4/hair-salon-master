package capestone.project.mimiSalon.database.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;




@Getter

@Setter

@Entity

@Table(name = "user_roles")

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(name = "user_role")
    private String userRole;

}