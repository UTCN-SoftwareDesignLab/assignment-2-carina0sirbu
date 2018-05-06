package controller;

import dto.UserDto;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.user.UserService;

@Controller
@RequestMapping(value = "/manageUser")
public class ManageUserController {

    private UserService userService;
    private String searchUsername;

    @Autowired
    public ManageUserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String getUser(Model model) {

        model.addAttribute("userDto", new UserDto());
        return "manageUser";
    }

    @PostMapping
    public String searchUser(Model model, @RequestParam("username") String username, @ModelAttribute UserDto userDto) {

        this.searchUsername = username;

        if(userService.findByUsername(username) == null) {
            model.addAttribute("message", "User is NOT in the system or some error occured");
            return "manageUser";
        }
        else {
            model.addAttribute("message", "User is in the system");
            return "manageUser";
        }

    }

    @PostMapping(params = "delete")
    @Transactional
    public String deleteUser(@RequestParam("username") String username, @ModelAttribute UserDto userDto, Model model) {

        userService.deleteByUsername(username);

        return "manageUser";
    }


    @PostMapping(params = "update")
    public String updateUser(@RequestParam("username") String username, Model model, @ModelAttribute UserDto userDto) {

        User user = userService.findByUsername(searchUsername);

        user.setUsername(username);

        userService.save(user);

        return "manageUser";
    }
}
