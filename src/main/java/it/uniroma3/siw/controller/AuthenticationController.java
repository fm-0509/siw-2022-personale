package it.uniroma3.siw.controller;

import it.uniroma3.siw.BootstrapAlert;
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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
public class AuthenticationController {
    @Autowired
    private CredentialService credentialsService;

    @Autowired
    private UserValidator userValidator;

    @Autowired
    private CredentialValidator credentialsValidator;

    @RequestMapping(value="/register", method = RequestMethod.GET)
    public String getRegisterPage(Model model) {
        model.addAttribute("user", new User());
        model.addAttribute("credential", new Credential());
        return "redirect:/";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String showLoginForm (RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Info("<strong>&Egrave; necessario effettuare il login</strong>"));
        return "redirect:/";
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

    @PostMapping("/register")
    public String newUser(@Valid @ModelAttribute(value = "credential") Credential credential,
                          BindingResult CredentialsBindingResult, @Valid @ModelAttribute(value = "user") User user,
                          BindingResult userBindingResult, RedirectAttributes redirectAttributes, Model model) {
        this.userValidator.validate(user, userBindingResult);
        this.credentialsValidator.validate(credential, CredentialsBindingResult);
        if(!userBindingResult.hasErrors() && !CredentialsBindingResult.hasErrors()) {
            credential.setUser(user);
            credentialsService.saveCredential(credential);
            redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Registrazione effettuata correttamente</strong>"));

            return "redirect:/";
        }
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Danger("<strong>ERRORE:</strong> Credenziali errate"));
        return "redirect:/";
    }

    @RequestMapping("/loginSuccess")
    public String loginSuccess(Model model, RedirectAttributes redirectAttributes)
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Login effettuato correttamente</strong> - Benvenuto "+userDetails.getUsername()));
        return "redirect:/";
    }

    @RequestMapping("/logoutSuccess")
    public String logoutSuccess(Model model, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Success("<strong>Log out eseguito correttamente</strong>"));
        return "redirect:/";
    }


    @RequestMapping(value = "/loginError",method = RequestMethod.GET)
    public String loginError(Model model, RedirectAttributes redirectAttributes)
    {
        redirectAttributes.addFlashAttribute("alert", BootstrapAlert.Danger("<strong>ERRORE:</strong> Credenziali errate"));
        return "redirect:/";
    }
}
