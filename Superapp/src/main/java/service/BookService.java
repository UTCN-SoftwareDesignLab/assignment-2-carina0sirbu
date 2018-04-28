package service;

import dto.BookDto;
import model.Book;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface BookService {

    void deleteByName(String name);

    List<Book> findAll();

    boolean create(BookDto bookDto);

    Book findByName(String name);

    List<Book> findAllByGenre(String genre);

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthorName(String name);

    Book save(Book book);
}
