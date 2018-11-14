package App.NoSQL;

import App.Entities.Posts;
import App.Entities.Users;
import com.mongodb.*;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Contains insert and find methods for Post collection in MongoDB.
 * PostNoSQL object encapsulates the state information needed for connection to MongoDB cluster,
 * and database/collection access.
 * This state information includes:
 *  <ul>
 *  <li><code>MongoClient</code>
 *  <li><code>MongoDatabase</code>
 *  <li><code>MongoCollection</code>
 *  </ul>
 *  @author Delila
 *  @since  1.0
 */
public class PostNoSQL {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;

    /**
     * Constructs new PostNoSQL object and initializes class attributes.
     * <p>
     * Requires no parameters.
     */
    public PostNoSQL(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://ddevic1:ddevic1@cluster0-shard-00-00-ohdsv.mongodb.net:27017,cluster0-shard-00-01-ohdsv.mongodb.net:27017,cluster0-shard-00-02-ohdsv.mongodb.net:27017/test?ssl=true&replicaSet=Cluster0-shard-0&authSource=admin&retryWrites=true"));
        db = mongoClient.getDatabase("prsdb1");
        collection = db.getCollection("Post");
    }

    /**
     * Inserts new document in Post collection.
     *
     * @param title     post title, document attribute
     * @param subtitle  post subtitle, document attribute
     * @param text      post text, document attribute
     * @param username  authors username, document attribute
     * @param mail      authors e-mail, document attribute
     */

    public void addPost(String title, String subtitle, String text, String username, String mail){

        Document post = new Document("_id", new ObjectId())
        .append("title", title)
        .append("subtitle", subtitle)
        .append("text", text)
        .append("author",new BasicDBObject("username",username)
        .append("mail",mail));
        collection.insertOne(post);
        System.out.println("dodao");
    }

    /**
     * Converts oracle entities Post and Author(User) in Document object.
     * This method iz useful while inserting document in Post collection.
     * @see Posts
     * @see Users
     *
     * @param post      oracle entity Post that contains post title, subtitle and text
     * @param author    oracle entity Author that contains authors username and e-mail
     * @return          new Document object for insertion in MongoDB
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
     * Inserts new document in Post collection.
     * Calls toDBObject method for converting parameters to Document object.
     * @see Posts
     * @see Users
     *
     * @param post      oracle entity Post that contains post title, subtitle and text
     * @param author    oracle entity Author that contains authors username and e-mail
     */
    public void addPost(Posts post, Users author){

        collection.insertOne(toDBObject(post, author));

    }

    /**
     * Returns all posts from Post collection.
     *
     * @return      MongoCursor that contains every post from Post collection
     */
    public MongoCursor<Document> getAllPosts() {
        FindIterable<Document> iterables = collection.find();
        MongoCursor<Document> cursor = iterables.iterator();
        try {
            while(cursor.hasNext()) {
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }
        return cursor;
    }

    /**
     * Returns posts from Post collection that contains specific tag.
     * This method will be used for filtering posts on frontend.
     *
     * @param tag       used for finding posts that contains the specific tag
     */
    public MongoCursor<Document> getPostsByTag(String tag) {
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

        return cursor;
    }
    public MongoCursor<Document> getPostsByAuthor(String userneme) {
        BasicDBObject query = new BasicDBObject("author.username", userneme);
        FindIterable<Document> iterables = collection.find(query);
        MongoCursor<Document> cursor = iterables.iterator();
        try {
            while(cursor.hasNext()){
                System.out.println(cursor.next().toJson());
            }
        } finally {
            cursor.close();
        }

        return cursor;
    }


}
