package App.NoSQL;

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
 * NoSQL  database where Comment manipulation is implemented
 * Used for Comment manipulation
 */

public class CommentNoSQL {
    MongoClient mongoClient;
    MongoDatabase db;
    MongoCollection<Document> collection;

    public CommentNoSQL(){
        mongoClient = new MongoClient(new MongoClientURI("mongodb://mmesihovic:m3s!h0v!c@mmesihovicbp-shard-00-00-wknse.mongodb.net:27017,mmesihovicbp-shard-00-01-wknse.mongodb.net:27017,mmesihovicbp-shard-00-02-wknse.mongodb.net:27017/test?ssl=true&replicaSet=mmesihovicBP-shard-0&authSource=admin&retryWrites=true"));
        db = mongoClient.getDatabase("bpNewsComments");
        collection = db.getCollection("Comments");
    }

    /**
     * Adds Comment into database.
     */
    public void addComment(){

        Document comment = new Document("_id", new ObjectId())
                .append("text", "This some new comment just for testing")
                .append("replyTo", null)
                .append("publishedAt", new Date())
                .append("author",new BasicDBObject("username","tvidovic1")
                        .append("mail","tvidovic1@etf.unsa.ba"));
        collection.insertOne(comment);
        System.out.println("Uspjesno dodano u NoSQL bazu podataka");
    }

    /**
     * Maps Comment to DB Object
     * @param comment instance of Comment
     * @param author instance of Users
     *
     */
    public Document toDBObject(Comments comment, Users author){
        return new Document("_id", new ObjectId())
                .append("text", comment.getText())
                .append("replyTo", comment.getReplyTo())
                .append("publishedAt", comment.getPublishedAt())
                .append("author",new BasicDBObject("username",author.getUsername())
                        .append("mail",author.getMail()));
    }

    /**
     * Inserts Comment into database
     * @param comment instance of Comments
     * @param author instance of Users
     */
    public void addComment(Comments comment, Users author){
        collection.insertOne(toDBObject(comment, author));
    }

    /**
     * Retrives all Comments from database.
     */
    public void getAllComments() {
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
     * Retrives Comments by username
     * @param username instance of string
     */
    public void getCommentsByUsername(String username) {
        BasicDBObject query = new BasicDBObject("author", new BasicDBObject("username","mmesihovic1")
                .append("mail","mmesihovic1@etf.unsa.ba"));
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
