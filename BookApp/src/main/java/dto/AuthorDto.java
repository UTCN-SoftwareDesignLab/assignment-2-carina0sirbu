package dto;

import model.Book;

import javax.validation.constraints.Size;
import java.util.List;

public class AuthorDto {

    @Size(min = 1)
    private String name;

    private List<Book> bookList;

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addBook(Book book) {
        this.bookList.add(book);
    }

    public void removeBook(Book book) {
        this.bookList.remove(book);
    }
}
