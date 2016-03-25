package mf.uz.repositories;

import mf.uz.domain.Certificate;
import mf.uz.domain.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * Created by qurbonov on 9/3/2015.
 */
public interface CertificateRepository extends JpaRepository<Certificate, Long>, JpaSpecificationExecutor<Certificate> {

}
