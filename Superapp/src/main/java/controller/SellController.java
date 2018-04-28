package controller;

import dto.BookDto;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.BookService;

@Controller
@RequestMapping(value = "sellBook")
public class SellController {

    private BookService bookService;

    @Autowired
    public SellController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String displayUser(Model model) {

        model.addAttribute("bookDto", new BookDto());
        return "sellBook";
    }

    @PostMapping(params = "soldBook")
    public String sellBook(@RequestParam("name") String title,
                           @RequestParam("quantity") int quantity,
                           @ModelAttribute BookDto bookDto,
                           Model model) {

        Book book = bookService.findByName(title);

        if (book != null) {
            if (book.getQuantity() < quantity) {

                model.addAttribute("message", "Not enough stock! There are only " + book.getQuantity() + " books left.");

            } else {

                model.addAttribute("message", "Selling successful! Total amount to pay: " + book.getPrice() * quantity);
                book.setQuantity(book.getQuantity() - quantity);
                bookService.save(book);

            }

        }
        else {
            model.addAttribute("bookDto", bookDto);
            model.addAttribute("message", "Book not in the system");
        }
        return "sellBook";
    }
}
