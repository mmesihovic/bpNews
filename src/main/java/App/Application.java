package App;

import App.Entities.Comments;
import App.Entities.Users;
import App.NoSQL.CommentNoSQL;
import App.NoSQL.PostNoSQL;
import App.Services.Implementations.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class Application implements CommandLineRunner{

    @Autowired
    UserService userService;

    @Autowired
    public static void main(String[] args) {
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

            PostNoSQL postNoSQL = new PostNoSQL();
            //postNoSQL.addPost();
            //postNoSQL.getAllPosts();
            postNoSQL.getPostsByTag("France");
        }
    }
}