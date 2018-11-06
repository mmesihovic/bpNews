package App.Repositories;

import App.Entities.Categories;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Interface defining methods for manipulating Category table in Oracle database.
 * Extends CrudRepository that implements all basic CRUD methods.
 * @see CrudRepository
 *
 * @author  Delila
 * @since   1.0
 */

@Repository
public interface ICategoryRepository extends CrudRepository<Categories, Long> {

    public Categories findByName(String name);

}
