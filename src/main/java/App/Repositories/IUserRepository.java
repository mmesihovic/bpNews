package App.Repositories;

import App.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository for handling User manipulation
 */
@Repository
public interface IUserRepository extends CrudRepository<Users, Long> {

    /**
     * Retrive USer by Username
     * @param username instance of String
     * @return User with the provided username
     */
    Users findByUsername(String username);

}
