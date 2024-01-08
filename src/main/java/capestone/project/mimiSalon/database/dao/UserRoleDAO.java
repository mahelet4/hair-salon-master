package capestone.project.mimiSalon.database.dao;



import capestone.project.mimiSalon.database.entity.UserRole;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
/* this interface extends a Spring Data JPA repository and includes a custom method to find user roles by user ID.
 It abstracts the database operations related to user roles, making it easier to interact with the database
 in the application code.*/
public interface UserRoleDAO extends JpaRepository<UserRole, Long> {
    public List<UserRole> findByUserId(Integer userId);
}