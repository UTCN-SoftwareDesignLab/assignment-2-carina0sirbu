package service.report;

import model.Book;
import repository.BookRepository;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReportGeneratorCsv implements ReportGenerator {

    private static final String COMMA = ",";
    private static final String NEWLINE = "\n";
    public static final String FILEHEADER = "Id, title, author name, genre, price";

    private BookRepository bookRepository;

    public ReportGeneratorCsv(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void generateReport() {

        List<Book> bookList = StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .filter(b->b.getQuantity()==0)
                .collect(Collectors.toCollection(ArrayList::new));


        FileWriter fileWriter = null;

        try {
            fileWriter = new FileWriter("BooksOutOfStock.csv");

            fileWriter.append(FILEHEADER);
            fileWriter.append(NEWLINE);

            for(Book book: bookList) {
                fileWriter.append(book.toString());
                fileWriter.append(NEWLINE);
            }

            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
