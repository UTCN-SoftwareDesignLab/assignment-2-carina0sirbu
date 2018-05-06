package service;

import dto.BookDto;
import model.Book;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface BookService {

    void deleteByName(String name);

    List<Book> findAll();

    boolean create(BookDto bookDto);

    Book findByName(String name);

    Optional<Book> findById(Long id);

    List<Book> findAllByGenre(String genre);

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthorId(Long id);

    Book save(Book book);

    boolean update(BookDto bookDto);
}
