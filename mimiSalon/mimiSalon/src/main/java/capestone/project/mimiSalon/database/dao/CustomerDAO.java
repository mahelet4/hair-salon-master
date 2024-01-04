package capestone.project.mimiSalon.database.dao;

import capestone.project.mimiSalon.database.entity.Customer;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    public Customer findById(Integer id);

    @Modifying
    @Transactional
    int deleteByFirstName(String firstName);

    @Query("SELECT c FROM Customer c WHERE c.firstName LIKE :firstName or c.lastName LIKE :lastName")
    List<Customer> findByFirstNameOrLastName(String firstName, String lastName);

    @Query("SELECT c FROM Customer c WHERE c.user.id = :userId")
    List<Customer> findByUserId(Integer userId);



}