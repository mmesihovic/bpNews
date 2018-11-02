package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostsTags {
    private long ID;
    private long PostID;
    private long TagID;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
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
    @Column(name = "TAGID", nullable = false, precision = 0)
    public long getTagID() {
        return TagID;
    }

    public void setTagID(long tagID) {
        TagID = tagID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostsTags postsTags = (PostsTags) o;

        if (ID != postsTags.ID) return false;
        if (PostID != postsTags.PostID) return false;
        if (TagID != postsTags.TagID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (PostID ^ (PostID >>> 32));
        result = 31 * result + (int) (TagID ^ (TagID >>> 32));
        return result;
    }
}
