package capestone.project.mimiSalon.daoTest;

import capestone.project.mimiSalon.database.dao.EmployeeDAO;
import capestone.project.mimiSalon.database.entity.Employee;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;
import java.util.Optional;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class EmployeeDAOTest {

    @Autowired
    private EmployeeDAO employeeDAO;

    @Test
    @Order(1)
    public void findByNameTest() {
        // given
        String name = "John"; // Replace with a valid name

        // when
        List<Employee> employees = employeeDAO.findByName(name);

        // then
        Assertions.assertFalse(employees.isEmpty());
        for (Employee employee : employees) {
            Assertions.assertTrue(employee.getName().contains(name));
        }
    }

    @Test
    @Order(2)
    public void findByIdTest() {
        // given
        int id = 1; // Replace with a valid ID

        // when
        Optional<Employee> employee = employeeDAO.findById(id);

        // then
        Assertions.assertTrue(employee.isPresent());
        Assertions.assertEquals(id, employee.get().getId());
    }

    @Test
    @Order(3)
    public void findAllTest() {
        // when
        List<Employee> employees = employeeDAO.findAll();

        // then
        Assertions.assertFalse(employees.isEmpty());
    }
}

