package capestone.project.mimiSalon.daoTest;

import capestone.project.mimiSalon.database.dao.ServiceDAO;
import capestone.project.mimiSalon.database.entity.Service;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ServiceDAOTest {

    @Autowired
    private ServiceDAO serviceDAO;

    @Test
    @Order(1)
    public void findByIdTest() {
        // given
        int id = 1; // Replace with a valid service ID

        // when
        Optional<Service> service = serviceDAO.findById(id);

        // then
        Assertions.assertTrue(service.isPresent());
        Assertions.assertEquals(id, service.get().getId());
    }

    @Test
    @Order(2)
    public void findAllTest() {
        // when
        List<Service> services = serviceDAO.findAll();

        // then
        Assertions.assertFalse(services.isEmpty());
    }

    @Test
    @Order(3)
    public void findByNameTest() {
        // given
        String name = "ServiceName"; // Replace with a valid service name

        // when
        List<Service> services = serviceDAO.findByName(name);

        // then
        Assertions.assertFalse(services.isEmpty());
        for (Service service : services) {
            Assertions.assertEquals(name, service.getName());
        }
    }

    @Test
    @Order(4)
    public void findByMaxPriceTest() {
        // given
        double maxPrice = 50.0; // Replace with a valid maximum price

        // when
        List<Service> services = serviceDAO.findByMaxPrice(maxPrice);

        // then
        Assertions.assertFalse(services.isEmpty());
        for (Service service : services) {
            Assertions.assertTrue(service.getPrice() <= maxPrice);
        }
    }
}

