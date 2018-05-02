package service.author;

import dto.AuthorDto;
import model.Author;
import org.springframework.stereotype.Service;

@Service
public interface AuthorService {

    Author findByName(String name);

    Author save(Author author);

    boolean update(AuthorDto authorDto);
}
