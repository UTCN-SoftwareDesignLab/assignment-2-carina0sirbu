package controller;

import dto.AuthorDto;
import dto.BookDto;
import dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import service.BookService;
import service.report.ReportGenerator;
import service.report.ReportGeneratorFactory;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Controller
@RequestMapping(value = "/admin")
public class AdminController {

    private BookService bookService;
    private ReportGeneratorFactory reportGeneratorFactory;

    private static final String APPLICATION_PDF = "application/pdf";
    private static final String APPLICATION_CSV = "application/txt";

    @Autowired
    public AdminController(BookService bookService, ReportGeneratorFactory reportGeneratorFactory) {
        this.bookService = bookService;
        this.reportGeneratorFactory = reportGeneratorFactory;
    }

    @GetMapping
    public String admin(Model model) {

        return "admin";
    }

    @PostMapping(params = "authors")
    public String goToBooks(@ModelAttribute AuthorDto authorDto) {

        return "redirect:/author";
    }

    @PostMapping(params = "books")
    public String goToBooks(@ModelAttribute BookDto bookDto, @ModelAttribute AuthorDto authorDto) {

        return "redirect:/book";
    }

    @PostMapping(params = "users")
    public String goToUsers(@ModelAttribute UserDto userDto) {

        return "redirect:/manageUser";
    }

    @RequestMapping(params = "pdfReport", method = RequestMethod.POST)
    public @ResponseBody HttpEntity<byte[]> generatePdfReport() throws IOException {

        ReportGenerator reportGenerator = reportGeneratorFactory.generateReport("pdf");
        reportGenerator.generateReport();

        String filePath = "E:/Poli is love/3rd yr/2nd sem/SD/assignment-2-carina0sirbu/BookApp/BooksOutOfStock.pdf";
        File file = getFile(filePath);
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "pdf"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<byte[]>(document, header);
    }

    @RequestMapping(params = "csvReport", method = RequestMethod.POST)
    public @ResponseBody HttpEntity<byte[]> generateCsvReport() throws IOException {

        ReportGenerator reportGenerator = reportGeneratorFactory.generateReport("csv");
        reportGenerator.generateReport();

        String filePath = "E:/Poli is love/3rd yr/2nd sem/SD/assignment-2-carina0sirbu/BookApp/BooksOutOfStock.csv";
        File file = getFile(filePath);
        byte[] document = FileCopyUtils.copyToByteArray(file);

        HttpHeaders header = new HttpHeaders();
        header.setContentType(new MediaType("application", "csv"));
        header.set("Content-Disposition", "inline; filename=" + file.getName());
        header.setContentLength(document.length);

        return new HttpEntity<byte[]>(document, header);
    }


    private File getFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        if (!file.exists()){
            throw new FileNotFoundException("file with path: " + filePath + " was not found.");
        }
        return file;
    }
}
