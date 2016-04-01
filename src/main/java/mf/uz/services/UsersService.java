package mf.uz.services;

import mf.uz.controller.Credentials;
import mf.uz.domain.Users;

import java.util.List;
import mf.uz.domain.Certificate;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * Created by qurbonov on 9/22/2015.
 */
public interface UsersService {
    List<Users> findAll();

    Users findOne(Long id);

    long count();

    Users findByName(String username);

    Users save(Users user);

    void delete(Long userID);

    Users findUsers(Credentials credentials);

    public UserDetails findUserInfoByUsername(String username);
    
    public Certificate addCertificate(Long userId, Certificate certificate);
}
