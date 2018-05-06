package service.user;

import dto.UserDto;
import model.Role;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import repository.UserRepository;

import java.security.MessageDigest;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private UserRepository userRepository;
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void deleteByUsername(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
    public boolean create(UserDto userDto) {

        String password = encoder.encode(userDto.getPassword());

        System.out.println("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX--" + password);

        User user = new User(userDto.getUsername(), password, Role.USER);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean delete(UserDto userDto) {


        User user = new User(userDto.getUsername(), encoder.encode(userDto.getPassword()), Role.USER);
        userRepository.delete(user);
        return true;
    }

    @Override
    public boolean update(UserDto userDto) {


        return false;
    }

    @Override
    public User findByUsername(String username) {

        return userRepository.findByUsername(username);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {



        return userRepository.findByUsernameAndPassword(username, encoder.encode(password));
    }

    private String encodePassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(password.getBytes("UTF-8"));
            StringBuilder hexString = new StringBuilder();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}
