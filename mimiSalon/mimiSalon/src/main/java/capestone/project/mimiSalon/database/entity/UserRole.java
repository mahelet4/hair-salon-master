package capestone.project.mimiSalon.database.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
/*this class is a blueprint for the user_roles table in the database.
It includes fields such as id, userId, and roleName to store information about user roles.
The annotations help define its role as a JPA entity and specify the table details.*/
@Getter

@Setter

@Entity

@Table(name = "user_roles")

public class UserRole {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "user_id")
    private Integer userId;

    @Column(name = "role_name")
    private String roleName;

}