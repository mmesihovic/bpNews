package App.Services.Implementations;

import App.Entities.Categories;
import App.Entities.Users;
import App.Repositories.ICategoryRepository;
import App.Services.Interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * CategoryService encapsulates the state information needed for get and find Categories.
 * Uses ICategoryRepository.
 * Implements ICategoryService
 * @see ICategoryRepository
 * @see ICategoryService
 *
 * @author  Delila
 * @since   1.0
 */

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    /**
     * Returns list of all categories from Categories table in Oracle database BP20
     *
     * @return      list of all categories from Oracle database BP20
     */

    @Override
    public List<Categories> getAllCategories() {
        return (List<Categories>) categoryRepository.findAll();
    }

    /**
     * Returns one category with specified name
     *
     * @param name      used for finding category with specific name
     * @return          only one Category find by name
     */

    @Override
    public Categories findByName(String name) { return categoryRepository.findByName(name); }

}
