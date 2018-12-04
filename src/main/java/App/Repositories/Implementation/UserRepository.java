package App.Repositories.Implementation;
import App.Entities.FajloviSanja;
import App.Entities.KorisniciSanja;
import App.Repositories.IFajloviSanja;
import App.Repositories.IKorisniciSanjaRepository;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.servlet.ServletOutputStream;

@Repository
public class UserRepository  {
    private static Session session;
    @Autowired
    IFajloviSanja fajloviSanja;
    @Autowired
    IKorisniciSanjaRepository korisniciSanja;

    private static void saveBytesToFile(String filePath, byte[] fileBytes) throws IOException {
        FileOutputStream outputStream = new FileOutputStream(filePath);
        outputStream.write(fileBytes);
        outputStream.close();
    }

    public boolean AddFile(String firstname, String lastname, String email, MultipartFile file, String fileName){

        KorisniciSanja korisnik = korisniciSanja.findByMail(email);
        if(korisnik != null) {
            System.out.println(korisnik.getFirstname());
            System.out.println(korisnik.getLastname());
            System.out.println(korisnik.getMail());
        } else {
            KorisniciSanja novi = new KorisniciSanja();
            novi.setFirstname(firstname);
            novi.setLastname(lastname);
            novi.setMail(email);
            korisniciSanja.save(novi);
        }
        korisnik = korisniciSanja.findByMail(email);

        FajloviSanja f = new FajloviSanja();
        byte[] byteArr = null;
        try {
            byteArr = file.getBytes();
            f.setContent(byteArr);
            f.setKorisnikid(korisnik.getId());
            f.setName(fileName);
            fajloviSanja.save(f);
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\maste\\Desktop\\bpnews\\"+fileName),  byteArr);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        return true; //ako je spaseno uspjesno
    }
}
