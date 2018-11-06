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
            /*
              String title = "Baudelaire suicide letter sold at auction";
              String subtitle = "French poet Baudelaire suicide letter fetches €234,000 at auction";
              String text = "A letter by the French 19th-Century poet Charles Baudelaire announcing he would kill himself has sold at auction for €234,000 (£204,000; $267,000). The note, dated 30 June 1845, was addressed to Baudelaire's lover Jeanne Duval. The poet, who was 24 years old at the time, attempted to commit suicide on the same day - but survived. The French auction website Osenat said the letter sold to a private buyer for three times the estimated price. In the letter, Baudelaire informed his mistress of his intention to take his own life. 'By the time you receive this letter, I will be dead,' it said. 'I am killing myself because I can no longer live, or bear the burden of falling asleep and waking up again.'";
              String username = "shrenovica1"
              String mail = "shrenovica1@etf.unsa.ba"
            */
            //postNoSQL.addPost();
            //postNoSQL.getAllPosts();
            //postNoSQL.getPostsByTag("France");

            //CategoryNoSQL categoryNoSQL = new CategoryNoSQL();
            //categoryNoSQL.addCategory("Sport");
            //categoryNoSQL.getAllCategories();
        }
    }
}