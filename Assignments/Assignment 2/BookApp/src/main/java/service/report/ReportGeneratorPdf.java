package service.report;

import model.Book;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDFont;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.springframework.beans.factory.annotation.Autowired;
import repository.BookRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ReportGeneratorPdf implements ReportGenerator {

    private BookRepository bookRepository;
    

    @Autowired
    public ReportGeneratorPdf(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void generateReport() {

        List<Book> bookList = StreamSupport.stream(bookRepository.findAll().spliterator(),false)
                .filter(b->b.getQuantity()==0)
                .collect(Collectors.toCollection(ArrayList::new));

        PDDocument document = new PDDocument();

        PDPage blankPage = new PDPage();
        document.addPage(blankPage);

        try {
            PDPageContentStream pageContentStream = new PDPageContentStream(document, blankPage);
            pageContentStream.beginText();
            pageContentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
            pageContentStream.setLeading(14.5f);
            pageContentStream.newLineAtOffset(50, 700);

            for(Book book: bookList) {

                pageContentStream.showText("ID: " + book.getId());
                pageContentStream.newLine();
                pageContentStream.showText("Title: " + book.getName());
                pageContentStream.newLine();
                pageContentStream.showText("Author name: " + book.getAuthor().toString());
                pageContentStream.newLine();
                pageContentStream.showText("Genre: " + book.getGenre());
                pageContentStream.newLine();
                pageContentStream.showText("Price: " + book.getPrice());
                pageContentStream.newLine();
                pageContentStream.newLine();
            }

            pageContentStream.endText();
            pageContentStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            document.save("E:/Poli is love/3rd yr/2nd sem/SD/assignment-2-carina0sirbu/BookApp/BooksOutOfStock.pdf");
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            document.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
