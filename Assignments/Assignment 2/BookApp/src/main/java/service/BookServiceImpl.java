package service;

import dto.BookDto;
import model.Author;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;
import repository.BookRepository;

import java.util.List;
import java.util.Optional;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
    private Book book;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void deleteByName(String name) {
        bookRepository.deleteByName(name);
    }

    @Override
    public List<Book> findAll() {
        return bookRepository.findAll();
    }

    @Override
    public boolean create(BookDto bookDto) {

        Author author = authorRepository.findByName(bookDto.getAuthorName());
        Book book = new Book(bookDto.getName(), author, bookDto.getGenre(), bookDto.getQuantity(), bookDto.getPrice());
        bookRepository.save(book);
        return true;
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
    }

    @Override
    public Optional<Book> findById(Long id) {
        return Optional.empty();
    }


    @Override
    public List<Book> findAllByGenre(String genre) {
        return bookRepository.findAllByGenre(genre);
    }

    @Override
    public List<Book> findAllByName(String name) {

        return bookRepository.findAllByName(name);
    }

    @Override
    public List<Book> findAllByAuthorId(Long id) {
        return bookRepository.findAllByAuthorId(id);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public boolean update(BookDto bookDto) {

        Book book = bookRepository.findById(bookDto.getId()).get();

        book.setName(bookDto.getName());
        book.setGenre(bookDto.getGenre());
        book.setQuantity(bookDto.getQuantity());
        book.setPrice(bookDto.getPrice());

        bookRepository.save(book);

        return bookRepository.findById(book.getId()).isPresent();
    }
}
