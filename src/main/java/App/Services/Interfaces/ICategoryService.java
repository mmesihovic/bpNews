package App.Services.Interfaces;

import App.Entities.Categories;
import App.Entities.Users;

import java.util.List;

public interface ICategoryService {

    List<Categories> getAllCategories();
    Categories findByName(String name);

}
