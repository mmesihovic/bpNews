package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Time;

@Entity
public class Posts {
    private long ID;
    private long AuthorID;
    private String Title;
    private String Subtitle;
    private String Text;
    private boolean CommentsAllowed;
    private Long HeadPictureID;
    private long VideoID;
    private Time PublishedAt;
    private Time EditedAt;

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
    @Column(name = "TITLE", nullable = false, length = 100)
    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    @Basic
    @Column(name = "SUBTITLE", nullable = false, length = 75)
    public String getSubtitle() {
        return Subtitle;
    }

    public void setSubtitle(String subtitle) {
        Subtitle = subtitle;
    }

    @Basic
    @Column(name = "TEXT", nullable = false)
    public String getText() {
        return Text;
    }

    public void setText(String text) {
        Text = text;
    }

    @Basic
    @Column(name = "COMMENTSALLOWED", nullable = false, precision = 0)
    public boolean isCommentsAllowed() {
        return CommentsAllowed;
    }

    public void setCommentsAllowed(boolean commentsAllowed) {
        CommentsAllowed = commentsAllowed;
    }

    @Basic
    @Column(name = "HEADPICTUREID", nullable = true, precision = 0)
    public Long getHeadPictureID() {
        return HeadPictureID;
    }

    public void setHeadPictureID(Long headPictureID) {
        HeadPictureID = headPictureID;
    }

    @Basic
    @Column(name = "VIDEOID", nullable = false, precision = 0)
    public long getVideoID() {
        return VideoID;
    }

    public void setVideoID(long videoID) {
        VideoID = videoID;
    }

    @Basic
    @Column(name = "PUBLISHEDAT", nullable = false)
    public Time getPublishedAt() {
        return PublishedAt;
    }

    public void setPublishedAt(Time publishedAt) {
        PublishedAt = publishedAt;
    }

    @Basic
    @Column(name = "EDITEDAT", nullable = true)
    public Time getEditedAt() {
        return EditedAt;
    }

    public void setEditedAt(Time editedAt) {
        EditedAt = editedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Posts posts = (Posts) o;

        if (ID != posts.ID) return false;
        if (AuthorID != posts.AuthorID) return false;
        if (CommentsAllowed != posts.CommentsAllowed) return false;
        if (VideoID != posts.VideoID) return false;
        if (Title != null ? !Title.equals(posts.Title) : posts.Title != null) return false;
        if (Subtitle != null ? !Subtitle.equals(posts.Subtitle) : posts.Subtitle != null) return false;
        if (Text != null ? !Text.equals(posts.Text) : posts.Text != null) return false;
        if (HeadPictureID != null ? !HeadPictureID.equals(posts.HeadPictureID) : posts.HeadPictureID != null)
            return false;
        if (PublishedAt != null ? !PublishedAt.equals(posts.PublishedAt) : posts.PublishedAt != null) return false;
        if (EditedAt != null ? !EditedAt.equals(posts.EditedAt) : posts.EditedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (int) (AuthorID ^ (AuthorID >>> 32));
        result = 31 * result + (Title != null ? Title.hashCode() : 0);
        result = 31 * result + (Subtitle != null ? Subtitle.hashCode() : 0);
        result = 31 * result + (Text != null ? Text.hashCode() : 0);
        result = 31 * result + (CommentsAllowed ? 1 : 0);
        result = 31 * result + (HeadPictureID != null ? HeadPictureID.hashCode() : 0);
        result = 31 * result + (int) (VideoID ^ (VideoID >>> 32));
        result = 31 * result + (PublishedAt != null ? PublishedAt.hashCode() : 0);
        result = 31 * result + (EditedAt != null ? EditedAt.hashCode() : 0);
        return result;
    }
}
