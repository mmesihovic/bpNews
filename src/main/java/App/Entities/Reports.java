package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Reports {
    private long ID;
    private long CommentID;
    private String Reason;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "COMMENTID", nullable = false, precision = 0)
    public long getCommentID() {
        return CommentID;
    }

    public void setCommentID(long commentID) {
        CommentID = commentID;
    }

    @Basic
    @Column(name = "REASON", nullable = false, length = 50)
    public String getReason() {
        return Reason;
    }

    public void setReason(String reason) {
        Reason = reason;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Reports reports = (Reports) o;

        if (ID != reports.ID) return false;
        if (CommentID != reports.CommentID) return false;
        if (Reason != null ? !Reason.equals(reports.Reason) : reports.Reason != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (CommentID ^ (CommentID >>> 32));
        result = 31 * result + (Reason != null ? Reason.hashCode() : 0);
        return result;
    }
}
