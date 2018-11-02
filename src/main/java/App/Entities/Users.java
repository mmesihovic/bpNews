package App.Entities;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Users {
    private long ID;
    private String Username;
    private String Password;
    private String Mail;
    private long RoleID;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    @Basic
    @Column(name = "USERNAME", nullable = false, length = 20)
    public String getUsername() {
        return Username;
    }

    public void setUsername(String username) {
        Username = username;
    }

    @Basic
    @Column(name = "PASSWORD", nullable = false, length = 100)
    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    @Basic
    @Column(name = "MAIL", nullable = false, length = 50)
    public String getMail() {
        return Mail;
    }

    public void setMail(String mail) {
        Mail = mail;
    }

    @Basic
    @Column(name = "ROLEID", nullable = false, precision = 0)
    public long getRoleID() {
        return RoleID;
    }

    public void setRoleID(long roleID) {
        RoleID = roleID;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Users users = (Users) o;

        if (ID != users.ID) return false;
        if (RoleID != users.RoleID) return false;
        if (Username != null ? !Username.equals(users.Username) : users.Username != null) return false;
        if (Password != null ? !Password.equals(users.Password) : users.Password != null) return false;
        if (Mail != null ? !Mail.equals(users.Mail) : users.Mail != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (ID ^ (ID >>> 32));
        result = 31 * result + (Username != null ? Username.hashCode() : 0);
        result = 31 * result + (Password != null ? Password.hashCode() : 0);
        result = 31 * result + (Mail != null ? Mail.hashCode() : 0);
        result = 31 * result + (int) (RoleID ^ (RoleID >>> 32));
        return result;
    }
}
