package capestone.project.mimiSalon.service;

import capestone.project.mimiSalon.database.dao.UserDAO;

import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.RegisterUserFormBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {

    @Autowired
    private UserDAO userDao;

    @Lazy
    @Autowired
    private PasswordEncoder passwordEncoder;

    public User createNewUser(RegisterUserFormBean form) {
        User user = new User();

        user.setEmail(form.getEmail().toLowerCase());

        String encoded = passwordEncoder.encode(form.getPassword());
        log.debug("Encoded password: " + encoded);
        user.setPassword(encoded);

        // Set the create_date field to the current date and time

        return userDao.save(user);
    }
    public List<User> findAll() {
        return userDao.findAll();
    }
    public Optional<User> findById(Integer id) {
        return userDao.findById(id);
    }
}

