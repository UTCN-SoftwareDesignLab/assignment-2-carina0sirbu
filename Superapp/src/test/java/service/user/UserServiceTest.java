package service.user;

import model.Role;
import model.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import repository.UserRepository;

import java.util.ArrayList;
import java.util.List;



@RunWith(MockitoJUnitRunner.Silent.class)
public class UserServiceTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Before
    public void setUp() throws Exception {

        userService = new UserServiceImpl(userRepository);

        List<User> userList = new ArrayList<>();

        User user1 = new User("carina.test", "parolaTest12#", Role.USER);

        User user2 = new User("andra.test", "parolaTest13#", Role.USER);

        userList.add(user1);
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);
        Mockito.when(userRepository.findByUsername(user1.getUsername())).thenReturn(user1);

        //Mockito.when(userRepository.deleteByUsername(user1.getUsername())).thenReturn(true);
        //Mockito.when(userRepository.save(new User("filme.test", "parolaTest14#", Role.USER))).thenReturn()

    }

    @Test
    public void findAll() {

        Assert.assertTrue(userService.findAll().size() == 2);
    }

    @Test
    public void findByUsername() {

        Assert.assertEquals(userRepository.findByUsername("carina.test").getUsername(), "carina.test");
    }

}