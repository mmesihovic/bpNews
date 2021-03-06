package App.Services.Implementations;

import App.Entities.Users;
import App.Repositories.IUserRepository;
import App.Repositories.Implementation.UserRepository;
import App.Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.nio.ByteBuffer;
import java.util.List;

/**
 * Service for handling User manipulation requests.
 *
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;
    @Autowired
    UserRepository _userRepository;

    public UserService(){
        //_userRepository = new UserRepository();
    }

    /**
     * Retriving all Users from database.
     * @return List of all users from database.
     */
    @Override
    @Transactional
    public List<Users> getAllUsers() {
        return (List<Users>) userRepository.findAll();
    }



    /**
     * Retrive user by username
     * @param username insance of String
     * @return User with the proivded username
     */

    @Override
    @Transactional
    public Users findByUsername(String username) {

        return userRepository.findByUsername(username);
    }


    @Override
    @Transactional
    public boolean AddFile(String firstname, String lastname, String email, MultipartFile file, String fileName) {
        System.out.println("bar ovo");
        return _userRepository.AddFile(firstname,lastname,email,file, fileName);
    }

    @Transactional
    public List<String> GetFiles(String filename, String username){

        return _userRepository.GetFiles(username, filename);
    }
}
