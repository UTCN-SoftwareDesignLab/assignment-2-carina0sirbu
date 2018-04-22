package controller;

import dto.BookDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import service.BookService;
import service.user.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private BookService bookService;
    private UserService userService;

    @Autowired
    public AdminController(BookService bookService, UserService userService) {
        this.bookService = bookService;
        this.userService = userService;
    }

    @GetMapping
    public String admin(Model model) {

        return "admin";
    }

    @PostMapping(params = "books")
    public String goToBooks(@ModelAttribute BookDto bookDto) {

        return "book";
    }

    @PostMapping(params = "users")
    public String goToUsers(@ModelAttribute UserDto userDto) {

        return "manageUser";
    }

    @PostMapping(params = "reports")
    public String goToReports() {

        return "user";
    }
}
