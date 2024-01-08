package capestone.project.mimiSalon.database.dao;

;
import capestone.project.mimiSalon.database.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserDAO extends JpaRepository<User, Long> {

    public User findByEmailIgnoreCase(String email);
    @Query("SELECT u FROM User u WHERE u.email LIKE :email AND u.password LIKE :password")
    List<User> findByemailandpassword(String email, String password);
    Optional<User> findById(Integer id);
   List<User>findAll();
}


