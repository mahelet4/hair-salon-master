package capestone.project.mimiSalon.database.dao;

import capestone.project.mimiSalon.database.entity.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {

    public Employee findById(int id);
    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE :firstName AND e.lastName LIKE :lastName")
    List<Employee> findByFirstNameandLastName(String firstName, String lastName);
}


