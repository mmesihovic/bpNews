package App.Controller;

import App.Entities.Users;
import App.Services.Implementations.UserService;
import com.fasterxml.jackson.databind.ser.Serializers;
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

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Blob;

@Controller
@RequestMapping("api/users")
public class UserControllerSanja {

    @Autowired
    UserService usersService;

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
                             @RequestParam("fileName") String fileName) {

       // usersService.AddFile(firstname,lastname,email,file, fileName);

        try {
            System.out.println(file.getBytes());
            System.out.println(file.getContentType());
            System.out.println(fileName);
            String encoded = Base64.encodeBase64String(file.getBytes());
            System.out.println(encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
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

        try {
          //  System.out.println(file.getBytes());
           // System.out.println(file.getContentType());
            System.out.println(fileName);
           // String encoded = Base64.encodeBase64String(file.getBytes());
            //System.out.println(encoded);
        } catch (Exception e) {
            e.printStackTrace();
        }
//W29iamVjdCBPYmplY3Rd
        return "W29iamVjdCBPYmplY3Rd";
    }
}
