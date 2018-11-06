package App.Services.Implementations;

import App.Entities.Users;
import App.Repositories.IUserRepository;
import App.Services.Interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service for handling User manipulation requests.
 *
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    /**
     * Retriving all Users from database.
     * @return List of all users from database.
     */
    @Override
    public List<Users> getAllUsers() {
        return (List<Users>) userRepository.findAll();
    }

    /**
     * Retrive user by username
     * @param username insance of String
     * @return User with the proivded username
     */
    @Override
    public Users findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
