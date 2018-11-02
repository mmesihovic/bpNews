package App.Repositories;

import App.Entities.Users;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends CrudRepository<Users, Long> {

    Users findByUsername(String username);

}
