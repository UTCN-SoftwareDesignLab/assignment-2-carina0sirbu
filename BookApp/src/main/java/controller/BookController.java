package controller;

import dto.AuthorDto;
import dto.BookDto;
import model.Author;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import service.BookService;
import service.author.AuthorService;


@Controller
@RequestMapping(value = "/book")
public class BookController {

    private BookService bookService;
    private AuthorService authorService;
    private String searchName;

    @Autowired
    public BookController(BookService bookService, AuthorService authorService) {
        this.bookService = bookService;
        this.authorService = authorService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getBook(Model model) {

        model.addAttribute("authorDto", new AuthorDto());
        model.addAttribute("bookDto", new BookDto());

        return "book";
    }

    @PostMapping(params = "create")
    public String createBook(@ModelAttribute BookDto bookDto,
                             @RequestParam("authorName") String name,
                             BindingResult bindingResult, Model model) {

        if(bindingResult.hasErrors()) {
            return "login";
        }

        Author author = authorService.findByName(name);
        bookDto.setAuthorName(author.getName());

        if (bookService.create(bookDto)) {

            model.addAttribute("message", "Book added to the system!");
        }
        else {
            model.addAttribute("message", "Error while trying to add the book.");
        }
        return "book";
    }

    @PostMapping(params = "search")
    public String searchBook(@RequestParam("name") String name, Model model, @ModelAttribute BookDto bookDto, BindingResult bindingResult) {

        if(bindingResult.hasErrors()) {
            return "login";
        }

        this.searchName = name;

        Book book = bookService.findByName(name);

        if (book == null) {
            model.addAttribute("bookDto", bookDto);
            model.addAttribute("message", "Book not in the system. Would you like to create it?");

        }
        else {
            bookDto.setName(book.getName());
            bookDto.setAuthorName(book.getAuthor().getName());
            bookDto.setGenre(book.getGenre());
            bookDto.setPrice(book.getPrice());
            bookDto.setQuantity(book.getQuantity());

            model.addAttribute("bookDto", bookDto);
        }
        return "book";
    }

    @PostMapping(params = "delete")
    @Transactional
    public String deleteBook(@RequestParam("name") String name, Model model, @ModelAttribute BookDto bookDto) {

        bookService.deleteByName(name);

        if (bookService.findByName(name) == null) {


            model.addAttribute("message", "Delete was successful!");

            bookDto.setName("");
            bookDto.setAuthorName("");
            bookDto.setGenre("");
            bookDto.setPrice(0);
            bookDto.setQuantity(0);
        }
        else {
            model.addAttribute("message", "Error while trying to delete!");
        }

        return "book";
    }

    @PostMapping(params = "update")
    public String updateBook(@RequestParam("name") String name,
                             @RequestParam("authorName") String authorName,
                             @RequestParam("genre") String genre,
                             @RequestParam("price") double price,
                             @RequestParam("quantity") int quantity,
                             Model model, @ModelAttribute BookDto bookDto) {

        Book book = bookService.findByName(searchName);

        Author author = authorService.findByName(authorName);

        book.setAuthor(author);
        book.setName(name);
        book.setGenre(genre);
        book.setPrice(price);
        book.setQuantity(quantity);

        if (bookService.save(book) == null) {
            model.addAttribute("message", "Update failed");
        }
        else {
            model.addAttribute("message", "Book updated successfully");
        }

        return "book";
    }
}
