package mf.uz.repositories;

import mf.uz.domain.Role;
import mf.uz.domain.Token;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by qurbonov on 10/6/2015. 
 */
public interface TokenRepository extends JpaRepository<Token, Long> {
}
