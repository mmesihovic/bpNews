package App.Services.Interfaces;

import App.Entities.Users;

import java.util.List;

public interface IUserService {

    List<Users> getAllUsers();
    Users findByUsername(String username);

}
