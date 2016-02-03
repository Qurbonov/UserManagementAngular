package mf.uz.services.impl;

import mf.uz.controller.Credentials;
import mf.uz.domain.Users;
import mf.uz.repositories.UserRepository;
import mf.uz.services.UsersService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.security.core.userdetails.UserDetails;

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    public UserRepository userRepository;

    @PersistenceContext
    protected EntityManager em;

    @Override
    public List<Users> findAll() {
        return userRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Override
    public Users findOne(Long id) {
        return userRepository.findOne(id);
    }

    @Override
    public long count() {
        return userRepository.count();
    }

//    @Override
//    public Users findByName(String username) {
//        return userRepository.findOne(UserSpec.byUsername(username));
//    }
    @Override
    public Users save(Users user) {
        return userRepository.save(user);
    }

    //    @Override
    public Users save(String username, Users user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public void delete(Long userID) {
        userRepository.delete(userID);
    }

//    @Override
//    public Users findUsers(Credentials credentials) {
//        return findByName(credentials.getUsername());
//    }
    @Override
    public Users findByName(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Users findUsers(Credentials credentials) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public UserDetails findUserInfoByUsername(String username) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
