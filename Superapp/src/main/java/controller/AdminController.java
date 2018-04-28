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
import service.report.ReportGenerator;
import service.report.ReportGeneratorFactory;
import service.user.UserService;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private BookService bookService;
    private ReportGeneratorFactory reportGeneratorFactory;

    @Autowired
    public AdminController(BookService bookService, ReportGeneratorFactory reportGeneratorFactory) {
        this.bookService = bookService;
        this.reportGeneratorFactory = reportGeneratorFactory;
    }

    @GetMapping
    public String admin(Model model) {

        return "admin";
    }

    @PostMapping(params = "books")
    public String goToBooks(@ModelAttribute BookDto bookDto) {

        return "redirect:/book";
    }

    @PostMapping(params = "users")
    public String goToUsers(@ModelAttribute UserDto userDto) {

        return "redirect:/manageUser";
    }

    @PostMapping(params = "pdfReport")
    public String generatePdf(@ModelAttribute UserDto userDto, Model model) {

        ReportGenerator reportGenerator = reportGeneratorFactory.generateReport("pdf");
        reportGenerator.generateReport();

        model.addAttribute("message", "PDF report generated");
        return "admin";
    }

    @PostMapping(params = "csvReport")
    public String generateCsv(@ModelAttribute UserDto userDto, Model model) {

        ReportGenerator reportGenerator = reportGeneratorFactory.generateReport("csv");
        reportGenerator.generateReport();

        model.addAttribute("message", "CSV report generated");
        return "admin";
    }
}
