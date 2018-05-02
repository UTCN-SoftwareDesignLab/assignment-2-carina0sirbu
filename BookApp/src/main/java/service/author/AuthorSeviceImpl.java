package service.author;

import dto.AuthorDto;
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

    @Override
    public boolean update(AuthorDto authorDto) {

        Author author = authorRepository.findById(authorDto.getId()).get();

        author.setName(authorDto.getName());

        authorRepository.save(author);

        return authorRepository.findById(author.getId()).isPresent();
    }
}
