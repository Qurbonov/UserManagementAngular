package mf.uz.services;

import java.util.Collection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import mf.uz.domain.Certificate;
import mf.uz.repositories.CertificateRepository;
import mf.uz.spec.CertificateSpec;

/**
 * Created by qurbonov on 9/2/2015.
 */
@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;

    public Certificate findOne(Long id) {
        return certificateRepository.findOne(id);
    }

    @Transactional
    public Certificate save(Certificate certificate) {
        return certificateRepository.save(certificate);
    }

    public List<Certificate> findAll() {
        return certificateRepository.findAll(new Sort(new Sort.Order(Sort.Direction.ASC, "id")));
    }

    @Transactional
    public void delete(Long certificateID) {
        certificateRepository.delete(certificateID);
    }

    public List<Certificate> findByUserId(Long userId) {
        return certificateRepository.findAll(CertificateSpec.byUserId(userId));
    }
}
