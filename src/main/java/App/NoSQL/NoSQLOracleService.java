package App.NoSQL;

import App.Entities.Users;
import App.Services.Implementations.UserService;
import com.mongodb.BasicDBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class NoSQLOracleService {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;
    UserService userService;

    /**
     * Constructs new PostNoSQL object and initializes class attributes.
     * <p>
     * Requires no parameters.
     */
    public NoSQLOracleService(){
        //mongoClient = new MongoClient(new MongoClientURI("mongodb://ddevic1:ddevic1@cluster0-shard-00-00-ohdsv.mongodb.net:27017,cluster0-shard-00-01-ohdsv.mongodb.net:27017,cluster0-shard-00-02-ohdsv.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
        db = mongoClient.getDatabase("prsdb1");
        collection = db.getCollection("Post");
        userService = new UserService();
    }

    public void getPosts(String username){
        System.out.println(userService);
        Users user = userService.findByUsername(username);
        BasicDBObject query = new BasicDBObject("author.username", user.getUsername());
        FindIterable<Document> iterables = collection.find(query);
        MongoCursor<Document> cursor = iterables.iterator();
        try {
            while(cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }

}
