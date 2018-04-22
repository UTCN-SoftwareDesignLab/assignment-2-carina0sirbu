package service;

import dto.BookDto;
import model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.BookRepository;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private Book book;
    @Autowired
    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
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

        Book book = new Book(bookDto.getName(), bookDto.getAuthorName(), bookDto.getGenre(), bookDto.getQuantity(), bookDto.getPrice());
        bookRepository.save(book);
        return true;
    }

    @Override
    public boolean delete(BookDto bookDto) {

        Book book = new Book(bookDto.getName(), bookDto.getAuthorName(), bookDto.getGenre(), bookDto.getQuantity(), bookDto.getPrice());
        bookRepository.delete(book);
        return true;
    }

    @Override
    public boolean update(BookDto bookDto) {
        return false;
    }

    @Override
    public Book findByName(String name) {
        return bookRepository.findByName(name);
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
    public List<Book> findAllByAuthorName(String name) {
        return bookRepository.findAllByAuthorName(name);
    }

    @Override
    public Book save(Book book) {
        return bookRepository.save(book);
    }
}
