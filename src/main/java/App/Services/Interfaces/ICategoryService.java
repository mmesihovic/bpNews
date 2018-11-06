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

    List<Categories> getAllCategories();
    Categories findByName(String name);

}
