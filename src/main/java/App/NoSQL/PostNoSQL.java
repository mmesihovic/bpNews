package App.NoSQL;

import App.Entities.Posts;
import App.Entities.Users;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * NoSQL  database where Post manipulation is implemented
 * Used for Post manipulation
 */

public class PostNoSQL {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public PostNoSQL(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://ddevic1:ddevic1@cluster0-shard-00-00-ohdsv.mongodb.net:27017,cluster0-shard-00-01-ohdsv.mongodb.net:27017,cluster0-shard-00-02-ohdsv.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
        db = mongoClient.getDatabase("prsdb1");
        collection = db.getCollection("Post");
    }

    /**
     * Add Post method used for adding Post into database.
     */
    public void addPost(){

        Document post = new Document("_id", new ObjectId())
        .append("title", "Baudelaire suicide letter sold at auction")
        .append("subtitle", "French poet Baudelaire suicide letter fetches €234,000 at auction")
        .append("text", "A letter by the French 19th-Century poet Charles Baudelaire announcing he would kill himself has sold at auction for €234,000 (£204,000; $267,000). The note, dated 30 June 1845, was addressed to Baudelaire's lover Jeanne Duval. The poet, who was 24 years old at the time, attempted to commit suicide on the same day - but survived. The French auction website Osenat said the letter sold to a private buyer for three times the estimated price. In the letter, Baudelaire informed his mistress of his intention to take his own life. 'By the time you receive this letter, I will be dead,' it said. 'I am killing myself because I can no longer live, or bear the burden of falling asleep and waking up again.'")
        .append("author",new BasicDBObject("username","shrenovica1")
        .append("mail","shrenovica1@etf.unsa.ba"));
        collection.insertOne(post);
        System.out.println("dodao");
    }

    /**
     * Method used for mapping to Database Object.
     * @param post instance of Posts
     * @param author instance of Users
     * @return returns new Document
     */

    public Document toDBObject(Posts post, Users author){
        return new Document("_id", new ObjectId())
                .append("title", post.getTitle())
                .append("subtitle", post.getSubtitle())
                .append("text", post.getText())
                .append("author",new BasicDBObject("username",author.getUsername())
                        .append("mail",author.getMail()));
    }

    /**
     * Used for adding Post into database
     * @param post instance of Posts
     * @param author instance of Users
     */
    public void addPost(Posts post, Users author){
        collection.insertOne(toDBObject(post, author));
    }

    /**
     * Retrieves all Posts from database.
     */
    public void getAllPosts() {
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

    /**
     * Retrieves Post by tag
     * @param tag instance of string
     */
    public void getPostsByTag(String tag) {
        BasicDBObject query = new BasicDBObject("tags", tag);
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
