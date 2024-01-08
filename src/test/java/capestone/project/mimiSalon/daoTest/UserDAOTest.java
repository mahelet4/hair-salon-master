package capestone.project.mimiSalon.daoTest;

import capestone.project.mimiSalon.database.dao.UserDAO;
import capestone.project.mimiSalon.database.entity.User;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDAO;

    @Test
    @Order(1)
    public void findByEmailIgnoreCaseTest() {
        // given
        String email = "example@example.com"; // Replace with a valid email

        // when
        User user = userDAO.findByEmailIgnoreCase(email);

        // then
        Assertions.assertNotNull(user);
        Assertions.assertEquals(email.toLowerCase(), user.getEmail().toLowerCase());
    }

    @Test
    @Order(2)
    public void findByEmailAndPasswordTest() {
        // given
        String email = "example@example.com"; // Replace with a valid email
        String password = "password"; // Replace with a valid password

        // when
        List<User> users = userDAO.findByemailandpassword(email, password);

        // then
        Assertions.assertFalse(users.isEmpty());
        for (User user : users) {
            Assertions.assertEquals(email.toLowerCase(), user.getEmail().toLowerCase());
            Assertions.assertEquals(password, user.getPassword());
        }
    }

    @Test
    @Order(3)
    public void findByIdTest() {
        // given
        int id = 1; // Replace with a valid ID

        // when
        Optional<User> user = userDAO.findById(id);

        // then
        Assertions.assertTrue(user.isPresent());
        Assertions.assertEquals(id, user.get().getId());
    }

    @Test
    @Order(4)
    public void findAllTest() {
        // when
        List<User> users = userDAO.findAll();

        // then
        Assertions.assertFalse(users.isEmpty());
    }
}

