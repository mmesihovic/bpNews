package App.NoSQL;
import App.Entities.Categories;
import App.Entities.Comments;
import App.Entities.Users;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import javax.sound.midi.SysexMessage;
import java.util.Date;
import java.util.List;

/**
 * NoSQL database where the User manipulation is implemented.
 * Used for manipulating User objects.
 */

public class UserNoSQL {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public UserNoSQL(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://shrenovica1:@cluster0-shard-00-00-zung0.mongodb.net:27017,cluster0-shard-00-01-zung0.mongodb.net:27017,cluster0-shard-00-02-zung0.mongodb.net:27017/admin?replicaSet=Cluster0-shard-0&ssl=true"));
        db = mongoClient.getDatabase("prsDB2");
        collection = db.getCollection("User");
    }

    /**
     * Adds user to DB.
     * @param username instance of String
     * @param password instance of String
     * @param mail instance of String
     */

    public void addUser(String username, String password, String mail){
        Document user = new Document("_id", new ObjectId())
                .append("username", username)
                .append("password", password)
                .append("mail", mail);
        collection.insertOne(user);
    }

    /**
     * Maps User to DB Object
     * @param user instance of Users
     */

    public Document toDBObject(Users user){
        return new Document("_id", new ObjectId())
                .append("username", user.getUsername())
                .append("password", user.getPassword())
                .append("mail", user.getMail());
    }

    /**
     * Adds user to DB.
     * @param user instance of User
     */

    public void addUser(Users user){
        collection.insertOne(toDBObject(user));
    }

    /**
     * Retrives all Users from DB.
     */
    public void getAllUsers() {
        FindIterable<Document> iterables = collection.find();
        MongoCursor<Document> cursor = iterables.iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
    }


}
