package controller;


import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import dto.AuthorDto;
import dto.BookDto;
import dto.UserDto;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.user.UserService;

import javax.validation.Valid;

@Controller
@RequestMapping(value = "/login")
public class LoginController {

    private UserService userService;

    @Autowired
    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getCreate(Model model) {

        model.addAttribute("userDto", new UserDto());
        model.addAttribute("bookDto", new BookDto());
        return "login";
    }

    @PostMapping(params = "register")
    public String create(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {


        if (bindingResult.hasErrors()) {
            return "login";
        }
        if (userService.create(userDto)) {
            model.addAttribute("message", "User create successfully!");
            return "login";
        }
        else {
            model.addAttribute("message", "Error while trying to add user");
            return "login";
        }


    }

    @PostMapping(params = "login")
    public String login(@ModelAttribute UserDto userDto,
                        @ModelAttribute BookDto bookDto,
                        Model model) {

        User user = userService.findByUsernameAndPassword(userDto.getUsername(), userDto.getPassword());

        if (user != null) {
            if (user.getRole().equals(Role.ADMIN))
                return "redirect:/admin";
            else
                return "redirect:/regularUser";
        }
        else {
            model.addAttribute("message", "User not in the system. Would you like to register?");
            return "login";
        }
    }



}
