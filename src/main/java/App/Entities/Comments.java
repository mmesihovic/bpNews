package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Comments {
    private long ID;
    private long AuthorID;
    private long PostID;
    private String Text;
    private Long ReplyTo;
    private Time PublishedAt;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "AUTHORID", nullable = false, precision = 0)
    public long getAuthorID() {
        return AuthorID;
    }

    public void setAuthorID(long authorID) {
        AuthorID = authorID;
    }

    @Basic
    @Column(name = "POSTID", nullable = false, precision = 0)
    public long getPostID() {
        return PostID;
    }

    public void setPostID(long postID) {
        PostID = postID;
    }

    @Basic
    @Column(name = "TEXT", nullable = false, length = 255)
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Basic
    @Column(name = "REPLYTO", nullable = true, precision = 0)
    public Long getReplyTo() {
        return ReplyTo;
    }

    public void setReplyTo(Long replyTo) {
        ReplyTo = replyTo;
    }

    @Basic
    @Column(name = "PUBLISHEDAT", nullable = false)
    public Time getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(Time publishedAt) {
        PublishedAt = publishedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comments comments = (Comments) o;

        if (ID != comments.ID) return false;
        if (AuthorID != comments.AuthorID) return false;
        if (PostID != comments.PostID) return false;
        if (Text != null ? !Text.equals(comments.Text) : comments.Text != null) return false;
        if (ReplyTo != null ? !ReplyTo.equals(comments.ReplyTo) : comments.ReplyTo != null) return false;
        if (PublishedAt != null ? !PublishedAt.equals(comments.PublishedAt) : comments.PublishedAt != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (AuthorID ^ (AuthorID >>> 32));
        result = 31 * result + (int) (PostID ^ (PostID >>> 32));
        result = 31 * result + (Text != null ? Text.hashCode() : 0);
        result = 31 * result + (ReplyTo != null ? ReplyTo.hashCode() : 0);
        result = 31 * result + (PublishedAt != null ? PublishedAt.hashCode() : 0);
        return result;
    }
}
