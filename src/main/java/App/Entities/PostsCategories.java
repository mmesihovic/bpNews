package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class PostsCategories {
    private long ID;
    private long PostID;
    private long CategoryID;

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
    @Column(name = "CATEGORYID", nullable = false, precision = 0)
    public long getCategoryID() {
        return CategoryID;
    }

    public void setCategoryID(long categoryID) {
        CategoryID = categoryID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PostsCategories that = (PostsCategories) o;

        if (ID != that.ID) return false;
        if (PostID != that.PostID) return false;
        if (CategoryID != that.CategoryID) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (PostID ^ (PostID >>> 32));
        result = 31 * result + (int) (CategoryID ^ (CategoryID >>> 32));
        return result;
    }
}
