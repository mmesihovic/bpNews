package App.Controller;

import App.Entities.*;
import App.Repositories.IFajloviTin;
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
@RequestMapping("tin/api/users")
public class UserControllerTin {

    @Autowired
    UserService usersService;
    @Autowired
    IFajloviTin fajloviTin;

    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String showUserFormTin() {

        return "userForm";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/tin/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileTin(@RequestParam("firstName") String firstname,
                                  @RequestParam("lastName") String lastname,
                                  @RequestParam("email") String email,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam("fileName") String fileName)
    {

        try {

            System.out.println(fileName);
            System.out.println(file.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
        usersService.AddFile(firstname,lastname,email,file, fileName);

        return "userForm";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/tin/download", method = RequestMethod.GET)
    @ResponseBody
    public String downloadFile(@RequestParam("fileName") String fileName,
                               @RequestParam("firstName") String firstname,
                               @RequestParam("lastName") String lastname ) {

        FajloviTin f = new FajloviTin();
        KorisniciTin kd = new KorisniciTin();


        List<FajloviTin> listaFajlova = (List<FajloviTin>) fajloviTin.findAll();


        for(int i=0; i<listaFajlova.size();i++){
            if(listaFajlova.get(i).getName().contains(fileName)){
                System.out.println(listaFajlova.get(i).getName() + " nasao");
                f = listaFajlova.get(i);
            }
        }

        byte[] byteArr = null;
        try {
            byteArr = f.getContent();
            FileUtils.writeByteArrayToFile(new File("C:\\Users\\aa\\Desktop\\bpnews\\downloaded"+fileName), byteArr);

        } catch (Exception e) {
            e.printStackTrace();
        }

        return "Success";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/tin/getFiles", method = RequestMethod.GET)
    @ResponseBody
    public List<String> getFileList(@RequestParam("firstName") String firstname,
                                    @RequestParam("lastName") String lastname ) {

        List<String> result1 = new ArrayList<>() ;
        result1.add("file1");
        result1 = usersService.GetFiles("kk", firstname);
        List<String> response = new ArrayList<>();
        for(String s : result1){
            response.add("name:" + s);
        }
        return  result1;
    }


}
