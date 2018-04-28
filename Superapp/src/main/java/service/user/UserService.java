package service.user;

import dto.UserDto;
import model.User;

import java.util.List;

public interface UserService {

    void deleteByUsername(String username);

    boolean create(UserDto userDto);

    boolean delete(UserDto userDto);

    boolean update(UserDto userDto);

    User findByUsername(String username);

    User save(User user);

    List<User> findAll();

    User findByUsernameAndPassword(String username, String password);
}
