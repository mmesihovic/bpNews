package App.Services.Interfaces;

import App.Entities.Categories;
import App.Entities.Users;

import java.util.List;

/**
 * Interface defining methods for manipulating Category table in Oracle database.
 *
 * @author  Delila
 * @since   1.0
 */

public interface ICategoryService {
    /**
     * Retrive all Categories from database.
     * @return List of all Categories from database.
     */
    List<Categories> getAllCategories();

    /**
     * Retrive Category by name
     * @param name instance of type string
     * @return Category with the provided name
     */
    Categories findByName(String name);

}
