package mf.uz.services;

import java.util.*;
import mf.uz.domain.Role;
import mf.uz.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import mf.uz.domain.*;
import mf.uz.repositories.TokenRepository;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by qurbonov on 10/6/2015.
 */
@Service
public class TokenService {

    @Autowired
    TokenRepository tokenRepository;

    public List<Token> findAll() {
        return tokenRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    public Token findOne(Long id) {
        return tokenRepository.findOne(id);
    }

    @Transactional
    public Token save(Token token) {
        return tokenRepository.save(token);
    }

    public Token generate(String ipAddress) {
        Token token = new Token();
        token.setCreate_Date(new Date());
        token.setIs_Verified(false);
        token.setHost_IP(ipAddress);
        token.setUUID(UUID.randomUUID().toString());
        return save(token);
    }
}
