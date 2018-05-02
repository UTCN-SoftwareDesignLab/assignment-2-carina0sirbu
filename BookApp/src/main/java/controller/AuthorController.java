package controller;

import dto.AuthorDto;
import model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.author.AuthorService;


@Controller
@RequestMapping(value = "/author")
public class AuthorController {

    private AuthorService authorService;

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

        Author author = authorService.findByName(name);

        if (author == null) {

            model.addAttribute("authorDto", authorDto);
            model.addAttribute("message", "Author not in the system. Would you like to create it?");
        }
        else {

            authorDto.setId(author.getId());

            model.addAttribute("authorDto", authorDto);
        }

        return "author";
    }

    @PostMapping(params = "update")
    public String updateAuthor(Model model, @ModelAttribute AuthorDto authorDto, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "author";
        }

        authorService.update(authorDto);
        model.addAttribute("message", "Update successful");

        return "author";
    }

    @PostMapping(params = "create")
    public String createAuthor(@RequestParam("name") String name, Model model, @ModelAttribute AuthorDto authorDto) {

        authorService.save(new Author(name));

        return "author";

    }
}
