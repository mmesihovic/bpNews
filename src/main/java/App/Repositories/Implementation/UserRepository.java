package App.Repositories.Implementation;

import org.springframework.web.multipart.MultipartFile;

public class UserRepository {

    public boolean AddFile(String firstname, String lastname, String email, MultipartFile file, String fileName){
        //dodati u bazu
        return true; //ako je spaseno uspjesno
    }
}
