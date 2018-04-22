package repository;

import model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {

    @Transactional
    void deleteByName(String name);

    Book findByName(String name);

    List<Book> findAllByGenre(String genre);

    List<Book> findAllByName(String name);

    List<Book> findAllByAuthorName(String name);

}
