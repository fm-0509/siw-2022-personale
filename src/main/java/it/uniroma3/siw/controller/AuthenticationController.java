package it.uniroma3.siw.controller;

import it.uniroma3.siw.controller.validator.CredentialValidator;
import it.uniroma3.siw.controller.validator.UserValidator;
import it.uniroma3.siw.model.security.Credential;
import it.uniroma3.siw.model.security.User;
import it.uniroma3.siw.service.CredentialService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AuthenticationController {
    @Autowired
    private CredentialService credentialsService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CredentialValidator credentialsValidator;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String showRegisterForm (Model model) {
        User u = new User();
        Credential c = new Credential();
        model.addAttribute("user", u);
        model.addAttribute("credential", c);
        return "registerUser";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm (Model model) {
        return "loginForm";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout(Model model) {
        return "index";
    }

    @RequestMapping(value = "/default", method = RequestMethod.GET)
    public String defaultAfterLogin(Model model) {

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Credential credentials = credentialsService.getCredential(userDetails.getUsername());
        if (credentials.getRole().equals(Credential.ADMIN_ROLE)) {
            return "admin/home";
        }
        return "home";
    }

    @RequestMapping(value = { "/register" }, method = RequestMethod.POST)
    public String registerUser(@ModelAttribute("user") User user,
                               BindingResult userBindingResult,
                               @ModelAttribute("credentials") Credential credentials,
                               BindingResult credentialsBindingResult,
                               Model model) {

        // validate user and credentials fields
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credentials, credentialsBindingResult);

        // if neither of them had invalid contents, store the User and the Credentials into the DB
        if(!userBindingResult.hasErrors() && ! credentialsBindingResult.hasErrors()) {
            // set the user and store the credentials;
            // this also stores the User, thanks to Cascade.ALL policy
            credentials.setUser(user);
            credentialsService.saveCredential(credentials);
            return "registrationSuccessful";
        }
        model.addAttribute("credential", credentials);
        model.addAttribute("user", user);
        return "registerUser";
    }
}