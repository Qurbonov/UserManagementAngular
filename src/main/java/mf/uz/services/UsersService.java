package mf.uz.services;

import mf.uz.controller.Credentials;
import mf.uz.domain.Users;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
}
