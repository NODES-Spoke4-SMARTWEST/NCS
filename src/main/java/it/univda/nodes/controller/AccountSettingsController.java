package it.univda.nodes.controller;

import it.univda.nodes.entity.User;
import it.univda.nodes.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/*@Controller
@RequestMapping("/account")
public class AccountSettingsController {

    @Autowired
    private UserService userService;

    @GetMapping("/settings")
    public String getAccountSettings(Principal principal, Model model) {
        // Assuming the username is stored in the Principal
        String username = principal.getName();
        User user = userService.findByUsername(username);

        if (user != null) {
            model.addAttribute("user", user);
            return "accountSettings";
        } else {
            return "redirect:/login"; // or some error page
        }
    }

    @PostMapping("/settings")
    public String updateAccountSettings(@ModelAttribute User user, Model model) {
        userService.save(user);
        model.addAttribute("user", user);
        model.addAttribute("success", "Account settings updated successfully!");
        return "accountSettings";
    }
}*/

@Controller
@RequestMapping("/account")
public class AccountSettingsController {

    @Autowired
    private UserService userService;
    @Autowired
    private HubService hubService;
    @Autowired
    private CompetenceService competenceService;
    @Autowired
    private InterestService interestService;
    @Autowired
    private RoleService roleService;

    @GetMapping("/settings")
    public String showAccountSettings(Model model) {
        model.addAttribute("user", userService.getCurrentUser());
        model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("allCompetences", competenceService.findAllCompetences());
        model.addAttribute("allInterests", interestService.findAllInterests());
        model.addAttribute("allRoles", roleService.findAll());
        return "account-settings";
    }

    @PostMapping("/settings")
    public String updateAccountSettings(@ModelAttribute User user, BindingResult result, Model model) {
        model.addAttribute("hubs", hubService.findAllHubs());
        model.addAttribute("allCompetences", competenceService.findAllCompetences());
        model.addAttribute("allInterests", interestService.findAllInterests());
        model.addAttribute("allRoles", roleService.findAll());

        if (!result.hasErrors()) {
            userService.updateUser(user);

            model.addAttribute("success", "Profile updated successfully!");
        }

        return "account-settings";
    }

}

