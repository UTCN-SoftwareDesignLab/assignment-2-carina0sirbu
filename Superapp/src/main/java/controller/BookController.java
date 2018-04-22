package controller;

import dto.BookDto;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BookService;





@Controller
@RequestMapping(value = "/book")
public class BookController {

    private BookService bookService;
    private String searchName;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getBook(Model model) {

        model.addAttribute("bookDto", new BookDto());

        return "book";
    }

    @PostMapping(params = "create")
    public String createBook(@ModelAttribute BookDto bookDto, Model model) {

        bookService.create(bookDto);
        return "book";
    }

    @PostMapping(params = "search")
    public String searchBook(@RequestParam("name") String name, Model model, @ModelAttribute BookDto bookDto) {

        this.searchName = name;

        Book book = bookService.findByName(name);

        bookDto.setName(book.getName());
        bookDto.setAuthorName(book.getAuthorName());
        bookDto.setGenre(book.getGenre());
        bookDto.setPrice(book.getPrice());
        bookDto.setQuantity(book.getQuantity());

        model.addAttribute("bookDto", bookDto);

        return "book";
    }

    @PostMapping(params = "delete")
    @Transactional
    public String deleteBook(@RequestParam("name") String name, Model model, @ModelAttribute BookDto bookDto) {

        bookService.deleteByName(name);

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

        book.setAuthorName(authorName);
        book.setName(name);
        book.setGenre(genre);
        book.setPrice(price);
        book.setQuantity(quantity);

        bookService.save(book);

        return "book";
    }
}
