package App.Services.Interfaces;

import App.Entities.Users;
import org.springframework.web.multipart.MultipartFile;

import java.nio.ByteBuffer;
import java.util.List;

/**
 * Methods used for User manipulation
 */
public interface IUserService {

    /**
     *  Retrive all users from database.
     * @return List of all users from database.
     */
    List<Users> getAllUsers();

    /**
     * Retrive user by username
     * @param username instance of String
     * @return User with the provided username.
     */
    Users findByUsername(String username);
    boolean AddFile(String firstname, String lastname, String email, MultipartFile file, String fileName);

}
