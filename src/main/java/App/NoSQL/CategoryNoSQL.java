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
 * NoSQL  database where Category manipulation is implemented
 * Used for Category manipulation
 */
public class CategoryNoSQL {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public CategoryNoSQL(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://tvidovic1:Thermaltake7@cluster0-shard-00-00-hq1fo.mongodb.net:27017,cluster0-shard-00-01-hq1fo.mongodb.net:27017,cluster0-shard-00-02-hq1fo.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
        db = mongoClient.getDatabase("bpNewsCategories");
        collection = db.getCollection("Categories");
    }

    /**
     * Adds Category into database.
     * @param name instance of string
     */
    public void addCategory(String name){

        Document category = new Document("_id", new ObjectId())
                .append("name", name);
        collection.insertOne(category);
        System.out.println("Komentar uspješno dodan");
    }

    /**
     * Maps Categories object into DB Object.
     * @param category instance of Categories
     * @return Document
     */

    public Document toDBObject(Categories category){
        return new Document("_id", new ObjectId())
                .append("name", category.getName());
    }

    /**
     * Inserts Category into database.
     * @param category insance of Categories
     */
    public void addCategory(Categories category){
        collection.insertOne(toDBObject(category));
    }

    /**
     * Retrives all Categories from database.
     */
    public void getAllCategories() {
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
