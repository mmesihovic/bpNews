package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Arrays;

@Entity
public class Pictures {
    private long ID;
    private byte[] Value;
    private long PostID;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "VALUE", nullable = false)
    public byte[] getValue() {
        return Value;
    }

    public void setValue(byte[] value) {
        Value = value;
    }

    @Basic
    @Column(name = "POSTID", nullable = false, precision = 0)
    public long getPostID() {
        return PostID;
    }

    public void setPostID(long postID) {
        PostID = postID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pictures pictures = (Pictures) o;

        if (ID != pictures.ID) return false;
        if (PostID != pictures.PostID) return false;
        if (!Arrays.equals(Value, pictures.Value)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + Arrays.hashCode(Value);
        result = 31 * result + (int) (PostID ^ (PostID >>> 32));
        return result;
    }
}
