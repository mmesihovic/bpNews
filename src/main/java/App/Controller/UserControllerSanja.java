package App.Controller;

import App.Entities.FajloviSanja;
import App.Entities.KorisniciSanja;
import App.Entities.Users;
import App.Repositories.IFajloviSanja;
import App.Repositories.Implementation.MetaDataRepository;
import App.Services.Implementations.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
import org.apache.commons.io.FileUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static ch.qos.logback.core.encoder.ByteArrayUtil.hexStringToByteArray;

@Controller
@RequestMapping("api/users")
public class UserControllerSanja {

    @Autowired
    UserService usersService;
    @Autowired
    IFajloviSanja fajloviSanja;

    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String showUserFormSanja() {

        return "userForm";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileSanja(@RequestParam("firstName") String firstname,
                             @RequestParam("lastName") String lastname,
                             @RequestParam("email") String email,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("fileName") String fileName)
    {

        try {
            //  System.out.println(file.getBytes());
            // System.out.println(file.getContentType());
            System.out.println(fileName);
            System.out.println(file.getBytes());
            // String encoded = Base64.encodeBase64String(file.getBytes());
            //System.out.println(encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
         usersService.AddFile(firstname,lastname,email,file, fileName);


//W29iamVjdCBPYmplY3Rd
        return "userForm";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/download", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(@RequestParam("fileName") String fileName,
                               @RequestParam("firstName") String firstname,
                               @RequestParam("lastName") String lastname ) {

        //remove comment
        // usersService.AddFile(firstname,lastname,email,file, fileName);

        FajloviSanja f = new FajloviSanja();
        KorisniciSanja ks = new KorisniciSanja();


        List<FajloviSanja> listaFajlova = (List<FajloviSanja>) fajloviSanja.findAll();


        for(int i=0; i<listaFajlova.size();i++){
            //System.out.println(listaFajlova.get(i).getName());
            if(listaFajlova.get(i).getName().contains(fileName)){
                System.out.println(listaFajlova.get(i).getName() + " nasao");
                f = listaFajlova.get(i);
            }
        }
        System.out.println(f.getName() + " dobar name");
        System.out.println(fileName);

        byte[] byteArr = null;
        try {
            //GET FILE CONTENT!
           byteArr = f.getContent();
            System.out.println(byteArr + " bitearr");
           FileUtils.writeByteArrayToFile(new File("C:\\Users\\aa\\Desktop\\bpnews\\downloaded"+fileName), byteArr);


        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Success";
    }
    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getFiles", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFileList(@RequestParam("firstName") String firstname,
                                    @RequestParam("lastName") String lastname ) {

        List<String> result = new ArrayList<>() ;
        result.add("file1");
        result = usersService.GetFiles("kk", firstname);
         List<String> response = new ArrayList<>();
         for(String s : result){
             response.add("name:" + s);
         }
        return  result;
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/getMetaData", method = RequestMethod.GET)
    @ResponseBody
    public void getMetaData(){
        String connectionString = "jdbc:oracle:thin:@ora.db.lab.ri.etf.unsa.ba:1521:ETFLAB";
        String username = "BP20";
        String password = "qEHBT2wy";

       MetaDataRepository mdr = new MetaDataRepository();
        try {
           Connection con = DriverManager.getConnection( connectionString, username, password );;
            DatabaseMetaData dbm = mdr.getMetadata();
            mdr.getSchema();


            mdr.getTables(dbm, "Categories");
            mdr.getTables(dbm, "Comments");
            mdr.getTables(dbm, "Pictures");
            mdr.getTables(dbm, "Posts");
            mdr.getTables(dbm, "PostCategories");
            mdr.getTables(dbm, "PostTags");
            mdr.getTables(dbm, "Reports");
            mdr.getTables(dbm, "Tags");
            mdr.getTables(dbm, "Users");
            mdr.getTables(dbm, "Videos");

            //Columns
            mdr.getColumns(dbm, "Categories");
            mdr.getColumns(dbm, "Comments");
            mdr.getColumns(dbm, "Pictures");
            mdr.getColumns(dbm, "Posts");
            mdr.getColumns(dbm, "PostCategories");
            mdr.getColumns(dbm, "PostTags");
            mdr.getColumns(dbm, "Reports");
            mdr.getColumns(dbm, "Tags");
            mdr.getColumns(dbm, "Users");
            mdr.getColumns(dbm, "Videos");

            //Indexes
            mdr.getIndexes(dbm, "Categories");
            mdr.getIndexes(dbm, "Comments");
            mdr.getIndexes(dbm, "Pictures");
            mdr.getIndexes(dbm, "Posts");
            mdr.getIndexes(dbm, "PostCategories");
            mdr.getIndexes(dbm, "PostTags");
            mdr.getIndexes(dbm, "Reports");
            mdr.getIndexes(dbm, "Tags");
            mdr.getIndexes(dbm, "Users");
            mdr.getIndexes(dbm, "Videos");

            //Views
            mdr.getViews(dbm, "Categories");
            mdr.getViews(dbm, "Comments");
            mdr.getViews(dbm, "Pictures");
            mdr.getViews(dbm, "Posts");
            mdr.getViews(dbm, "PostCategories");
            mdr.getViews(dbm, "PostTags");
            mdr.getViews(dbm, "Reports");
            mdr.getViews(dbm, "Tags");
            mdr.getViews(dbm, "Users");
            mdr.getViews(dbm, "Videos");

            //PrimaryKeys
            mdr.getPrimaryKeys(dbm, "Categories");
            mdr.getPrimaryKeys(dbm, "Comments");
            mdr.getPrimaryKeys(dbm, "Pictures");
            mdr.getPrimaryKeys(dbm, "Posts");
            mdr.getPrimaryKeys(dbm, "PostCategories");
            mdr.getPrimaryKeys(dbm, "PostTags");
            mdr.getPrimaryKeys(dbm, "Reports");
            mdr.getPrimaryKeys(dbm, "Tags");
            mdr.getPrimaryKeys(dbm, "Users");
            mdr.getPrimaryKeys(dbm, "Videos");

            //Procedures
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);
            mdr.getProcedures(dbm, connectionString);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
