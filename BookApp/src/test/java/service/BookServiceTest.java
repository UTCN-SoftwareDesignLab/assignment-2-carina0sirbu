package service;

import com.sun.org.apache.bcel.internal.generic.LNEG;
import dto.BookDto;
import model.Author;
import model.Book;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.BookRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.Silent.class)
public class BookServiceTest {

    @Mock
    BookRepository bookRepository;

    @InjectMocks
    BookServiceImpl bookService;

    Book book;

    @Before
    public void setUp() {


        bookService = new BookServiceImpl(bookRepository);

        List<Book> bookList = new ArrayList<>();

        book = new Book("Carte de test", new Author("Capsunica"), "sanatate", 5, 23);
        bookList.add(book);

        //Book book1 = new Book("Bombonici", "Sugar Land", "dulce", 300, 55);

        Mockito.when(bookRepository.findAllByGenre("sanatate")).thenReturn(bookList);
        Mockito.when(bookRepository.findByName("Carte de test")).thenReturn(book);
        Mockito.when(bookRepository.save(new Book("Bombonici", new Author("Sugar Land"), "dulce", 300, 55)))
                                    .thenReturn(new Book());
    }

    @Test
    public void findById() {

        List<Book> bookList = bookService.findAllByGenre("sanatate");
        Assert.assertTrue(bookList.size() == 1);
    }

    @Test
    public void findByName() {

        Book book = bookService.findByName("Carte de test");
        List<Book> bookList = new ArrayList<>();
        bookList.add(book);

        Assert.assertTrue(bookList.size() == 1);
    }

    @Test
    public void create() {

        Book book = bookService.findByName("Bombonici");
        Assert.assertEquals(book.getAuthor(), "Sugar Land");

        Assert.assertTrue(bookService.create(new BookDto()));
    }
}