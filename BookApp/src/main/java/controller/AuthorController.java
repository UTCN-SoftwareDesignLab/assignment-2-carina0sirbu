package controller;

import dto.AuthorDto;
import dto.UserDto;
import model.Author;
import model.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.author.AuthorService;

import javax.validation.constraints.PastOrPresent;

@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    private AuthorService authorService;
    private String searchAuthor;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public String getAuthor(Model model) {

        model.addAttribute("authorDto", new AuthorDto());
        return "author";
    }

    @PostMapping
    public String searchAuthor(Model model, @RequestParam("name") String name, @ModelAttribute AuthorDto authorDto) {

        this.searchAuthor = name;

        if(authorService.findByName(name) == null) {
            model.addAttribute("message", "Author is NOT in the system or some error occured");
            return "author";
        }
        else {
            model.addAttribute("message", "Author registered");
            return "author";
        }

    }

    @PostMapping(params = "update")
    public String updateAuthor(@RequestParam("name") String name, Model model, @ModelAttribute AuthorDto authorDto) {

        Author author = authorService.findByName(searchAuthor);

        author.setName(name);

        if (authorService.save(author) == null) {
            model.addAttribute("message", "Update failed");
        }
        else {
            model.addAttribute("message", "Author updated successfully!");
        }

        return "author";
    }

    @PostMapping(params = "create")
    public String createAuthor(@RequestParam("name") String name, Model model, @ModelAttribute AuthorDto authorDto) {

        authorService.save(new Author(name));

        return "author";

    }
}
