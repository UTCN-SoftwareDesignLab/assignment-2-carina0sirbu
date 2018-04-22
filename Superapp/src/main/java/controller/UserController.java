package controller;

import dto.BookDto;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BookService;
import java.util.List;


@Controller
@RequestMapping(value = "/regularUser")
public class UserController {

    private BookService bookService;

    @Autowired
    public UserController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayUser(Model model) {

        model.addAttribute("bookDto", new BookDto());
        return "regularUser";
    }


    @PostMapping(params = "genreSearch")
    public String genreSearch(@ModelAttribute BookDto bookDto, Model model) {

        List<Book> bookList = bookService.findAllByGenre(bookDto.getGenre());
        model.addAttribute("book", bookList);

        return "searchPage";
    }

    @PostMapping(params = "titleSearch")
    public String titleSearch(@RequestParam("name") String name, Model model) {

        System.out.println(name);
        List<Book> bookList = bookService.findAllByName(name);


        model.addAttribute(bookList);

        return "searchPage";
    }

    @PostMapping(params = "authorSearch")
    public String authorSearch(@ModelAttribute BookDto bookDto, Model model) {

        List<Book> bookList = bookService.findAllByAuthorName(bookDto.getAuthorName());
        model.addAttribute("book", bookList);

        return "searchPage";
    }



}
