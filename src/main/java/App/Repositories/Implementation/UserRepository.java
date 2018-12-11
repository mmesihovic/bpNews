package App.Repositories.Implementation;
import App.Entities.FajloviSanja;
import App.Entities.KorisniciSanja;
import App.Repositories.IFajloviSanja;
import App.Repositories.IKorisniciSanjaRepository;
import org.apache.commons.io.FileUtils;
import org.hibernate.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
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
            System.out.println("ovdje radi 100% " + byteArr);
            f.setContent(byteArr);
            f.setKorisnikid(korisnik.getId());

            f.setName(fileName);
            fajloviSanja.save(f);
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\aa\\Desktop\\bpnews"+fileName),  byteArr);
        }
        catch (IOException e){
            e.printStackTrace();
        }

        //poziv procedure

        Query query = session.createSQLQuery(
                "CALL FAJLOVI_SANJA_INSERT_PROCEDURE(:korisnik)")
                .setParameter("korisnik", korisnik.getMail());

        query.executeUpdate();

        return true; //ako je spaseno uspjesno
    }

    public List<String> GetFiles(String username, String filename){
        KorisniciSanja korisnik = korisniciSanja.findByMail("shrenovica1@etf.unsa.ba");
        FajloviSanja f = new FajloviSanja();
        List<String> files = new ArrayList<>();
        List<FajloviSanja>ks = (List<FajloviSanja>) fajloviSanja.findAll();
        System.out.println(fajloviSanja + "fs");
        System.out.println(ks.size() + "ks");
       for(int i=0; i<ks.size(); i++){
           files.add(ks.get(i).getName());
       }
        System.out.println(filename);

        System.out.println(files.get(0));
       return files;

    }
}
