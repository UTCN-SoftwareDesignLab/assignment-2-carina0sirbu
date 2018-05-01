package service.author;

import model.Author;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.AuthorRepository;

@Service
public class AuthorSeviceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorSeviceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public Author findByName(String name) {
        return authorRepository.findByName(name);

    }

    @Override
    public Author save(Author author) {
        return authorRepository.save(author);
    }
}
