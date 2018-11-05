package App.Services.Implementations;

import App.Entities.Categories;
import App.Entities.Users;
import App.Repositories.ICategoryRepository;
import App.Services.Interfaces.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService implements ICategoryService {
    @Autowired
    private ICategoryRepository categoryRepository;

    @Override
    public List<Categories> getAllCategories() {
        return (List<Categories>) categoryRepository.findAll();
    }

    @Override
    public Categories findByName(String name) { return categoryRepository.findByName(name); }

}
