package controller;

import dto.AuthorDto;
import dto.BookDto;
import model.Author;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.BookService;
import service.author.AuthorService;

import javax.jws.WebParam;
import java.util.List;


@Controller
@RequestMapping(value = "/regularUser")
public class UserController {

    private BookService bookService;
    private AuthorService authorService;

    @Autowired
    public UserController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayUser(Model model) {

        model.addAttribute("bookDto", new BookDto());
        return "regularUser";
    }


    @PostMapping(params = "genreSearch")
    public ModelAndView genreSearch(@ModelAttribute BookDto bookDto, Model model) {

        List<Book> bookList = bookService.findAllByGenre(bookDto.getGenre());

        ModelAndView mav = new ModelAndView("searchPage");
        mav.addObject("book", bookList);

        return mav;
    }

    @PostMapping(params = "titleSearch")
    public ModelAndView titleSearch(@RequestParam("name") String name, @ModelAttribute BookDto bookDto, Model model) {

        List<Book> bookList = bookService.findAllByName(name);

        ModelAndView mav = new ModelAndView("searchPage");
        mav.addObject("book", bookList);
        return mav;
    }

    @PostMapping(params = "authorSearch")
    public ModelAndView authorSearch(@ModelAttribute BookDto bookDto, @ModelAttribute AuthorDto authorDto,  Model model) {

        Author author = authorService.findByName(bookDto.getAuthorName());

        List<Book> bookList = bookService.findAllByAuthorId(author.getId());


        ModelAndView mav = new ModelAndView("searchPage");
        mav.addObject("book", bookList);
        return mav;
    }

    @PostMapping(params = "soldBook")
    public String sellBook(@ModelAttribute BookDto bookDto,
                           Model model) {

        return "redirect:/sellBook";
    }


}
