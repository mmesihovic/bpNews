package App.Repositories.Implementation;

import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.data.jpa.provider.HibernateUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.Session;
import java.io.File;
import java.io.FileInputStream;

public class UserRepository {

    public boolean AddFile(String firstname, String lastname, String email, MultipartFile file, String fileName){

        File file1 = new File(file.getName());
        byte[] data = new byte[(int) file1.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            fileInputStream.read(data);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*FajloviSanja image = new FajloviSanja();
        image.setImageName("test.jpeg");
        image.setData(imageData);*/

        return true; //ako je spaseno uspjesno
    }

/*    public boolean AddFileDelila(String firstname, String lastname, String email, MultipartFile file, String fileName){

        File file1 = new File(file.getName());
        byte[] imageData = new byte[(int) file1.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FajloviDelila image = new FajloviDelila();
        image.setImageName("test.jpeg");
        image.setData(imageData);

        return true; //ako je spaseno uspjesno
    }
    public boolean AddFileMirza(String firstname, String lastname, String email, MultipartFile file, String fileName){

        File file1 = new File(file.getName());
        byte[] imageData = new byte[(int) file1.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FajloviMirza image = new FajloviMirza();
        image.setImageName("test.jpeg");
        image.setData(imageData);

        return true; //ako je spaseno uspjesno
    }
    public boolean AddFileTin(String firstname, String lastname, String email, MultipartFile file, String fileName){

        File file1 = new File(file.getName());
        byte[] imageData = new byte[(int) file1.length()];

        try {
            FileInputStream fileInputStream = new FileInputStream(file1);
            fileInputStream.read(imageData);
            fileInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        FajloviTin image = new FajloviTin();
        image.setImageName("test.jpeg");
        image.setData(imageData);

        return true; //ako je spaseno uspjesno
    }*/
}
