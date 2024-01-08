package capestone.project.mimiSalon.database.dao;

import capestone.project.mimiSalon.database.entity.Employee;


import capestone.project.mimiSalon.database.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeDAO extends JpaRepository<Employee, Long> {


    List<Employee> findAll();
    @Query("SELECT e FROM Employee e WHERE e.name LIKE :name")
    List<Employee> findByName(String name);
    Optional<Employee> findById(Integer id);
}


