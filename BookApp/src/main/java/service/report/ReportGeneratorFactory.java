package service.report;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

@Service
public class ReportGeneratorFactory {

    @Autowired
    private BookRepository bookRepository;

    public ReportGenerator generateReport(String type) {

        if (type.equals("pdf")) {

            return new ReportGeneratorPdf(bookRepository);
        }

        if (type.equals("csv")) {

            return new ReportGeneratorCsv(bookRepository);
        }

        return null;
    }

}
