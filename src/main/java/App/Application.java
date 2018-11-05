package App;

import App.Entities.Categories;
import App.Entities.Comments;
import App.Entities.Users;
import App.NoSQL.CategoryNoSQL;
import App.NoSQL.CommentNoSQL;
import App.NoSQL.PostNoSQL;
import App.Services.Implementations.CategoryService;
import App.Services.Implementations.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.Locale;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    CategoryService categoryService;

    @Autowired
    public static void main(String[] args) {

        Locale.setDefault(Locale.US);
        SpringApplication.run(Application.class, args);
    }
    @Override
    public void run(String... arg0) throws Exception {

        {
            System.out.println("Pokrenuo sam see " );

            //Users u = userService.findByUsername("mmesihovic1");
            //System.out.println(u.getUsername());

            //CommentNoSQL commentNoSQL = new CommentNoSQL();
            //commentNoSQL.addComment();
            //commentNoSQL.getAllComments();
            //commentNoSQL.getCommentsByUsername("mmesihovic1");

            //PostNoSQL postNoSQL = new PostNoSQL();
            //postNoSQL.addPost();
            //postNoSQL.getAllPosts();
            //postNoSQL.getPostsByTag("France");

            CategoryNoSQL categoryNoSQL = new CategoryNoSQL();
            //categoryNoSQL.addCategory("Sport");
            categoryNoSQL.getAllCategories();
        }
    }
}