package controller;

import dto.BookDto;
import dto.UserDto;
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
@RequestMapping(value = "/register")
public class RegisterController {

    private UserService userService;

    @Autowired
    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String getCreate(Model model) {

        model.addAttribute("userDto", new UserDto());
        return "register";
    }

    @PostMapping(params = "register")
    public String create(@ModelAttribute @Valid UserDto userDto, BindingResult bindingResult, Model model) {

        if (bindingResult.hasErrors()) {
            return "register";
        }
        if (userService.create(userDto)) {
            model.addAttribute("message", "User created successfully!");
            return "register";
        }
        else {
            model.addAttribute("message", "Error while trying to add user");
            return "register";
        }
    }
}
