package App.Entities;

import javax.persistence.*;
import java.util.Arrays;

@Entity
@Table(name = "FAJLOVITIN")
public class FajloviTin {
    private long id;
    private String name;
    private byte[] content;
    private long korisnikid;

    @Id
    @Column(name = "ID", nullable = false, precision = 0)
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "NAME", nullable = false, length = 100)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "CONTENT", nullable = false)
    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    @Basic
    @Column(name = "KORISNIKID", nullable = false, precision = 0)
    public long getKorisnikid() {
        return korisnikid;
    }

    public void setKorisnikid(long korisnikid) {
        this.korisnikid = korisnikid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FajloviTin that = (FajloviTin) o;

        if (id != that.id) return false;
        if (korisnikid != that.korisnikid) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (!Arrays.equals(content, that.content)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(content);
        result = 31 * result + (int) (korisnikid ^ (korisnikid >>> 32));
        return result;
    }
}
