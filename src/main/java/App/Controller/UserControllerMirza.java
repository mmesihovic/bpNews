package App.Controller;

import App.Entities.Users;
import App.Services.Implementations.UserService;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StandardMultipartHttpServletRequest;

import java.io.IOException;

@Controller
@RequestMapping("api/usersMirza")
public class UserControllerMirza {

    UserService usersService = new UserService();

    @RequestMapping(value="/", method = RequestMethod.GET)
    @ResponseBody
    public String showUserFormMirza() {

        return "userForm";
    }

    @CrossOrigin(origins = "http://localhost:4200")
    @RequestMapping(value="/upload", method = RequestMethod.POST)
    @ResponseBody
    public String uploadFileMirza(@RequestParam("firstName") String firstname,
                             @RequestParam("lastName") String lastname,
                             @RequestParam("email") String email,
                             @RequestParam("file") MultipartFile file,
                             @RequestParam("fileName") String fileName) {

        usersService.AddFile(firstname,lastname,email,file, fileName);

        try {
            System.out.println(file.getBytes() );
            System.out.println(fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "userForm";
    }
}
