package capestone.project.mimiSalon.controller;

import capestone.project.mimiSalon.database.entity.User;
import capestone.project.mimiSalon.form.RegisterUserFormBean;
import capestone.project.mimiSalon.service.AuthenticatedUserService;
import capestone.project.mimiSalon.service.UserService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller

public class AuthController {

    private final UserService userService;
    private final AuthenticatedUserService authenticatedUserService;

    public AuthController(UserService userService, AuthenticatedUserService authenticatedUserService) {
        this.userService = userService;
        this.authenticatedUserService = authenticatedUserService;
    }

    // Display the login page
    @RequestMapping("/auth/login")
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView("auth/login");
        return modelAndView;
    }

    // Display the registration page
    @GetMapping("/auth/register")
    public ModelAndView register() {
        ModelAndView modelAndView = new ModelAndView("auth/register");
        return modelAndView;
    }

    // Handle the form submission for user registration
    @PostMapping("/auth/registerSubmit")
    public ModelAndView registerSubmit(@Valid RegisterUserFormBean form, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            log.info("Validation errors found during registration.");
            ModelAndView modelAndView = new ModelAndView("auth/register");

            // Loop through validation errors and log them
            for (ObjectError error : bindingResult.getAllErrors()) {
                log.info("Error: " + error.getDefaultMessage());
            }

            modelAndView.addObject("form", form);
            modelAndView.addObject("errors", bindingResult);
            return modelAndView;
        }

        log.info("No validation errors found during registration.");

        // Create a new user based on the form data
        User user = userService.createNewUser(form);

        ModelAndView modelAndView = new ModelAndView("redirect:/");

        return modelAndView;
    }
}
