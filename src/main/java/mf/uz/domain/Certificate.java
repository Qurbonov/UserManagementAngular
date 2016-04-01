/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mf.uz.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author qurbonov
 */
@Entity
@Table(name = "CERTIFICATES", uniqueConstraints = @UniqueConstraint(columnNames = {"CHECKSUM_MD5"}))
@SequenceGenerator(name = "CertificateSeq", sequenceName = "SEQ_CERTIFICATES", allocationSize = 1)
public class Certificate {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CertificateSeq")
    @Column(name = "C_ID", nullable = false)
    private Long id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    @JoinColumn(name = "USER_ID")
    private Users user;
 
    @Column(name = "CONTENT") 
    private byte[] content;

    @Column(name = "CHECKSUM_MD5")
    private String checksumMd5;

    @Column(name = "dateTo")
    private Date dateTo;

    @Column(name = "dateFrom")
    private Date dateFrom;

    @Column(name = "certFileName")
    private String certFileName;
    
    public Certificate() {
    }

    public Certificate(Long id, Users user, byte[] content, String checksumMd5, Date dateTo, Date dateFrom, String certFileName) {
        this.id = id;
        this.user = user;
        this.content = content;
        this.checksumMd5 = checksumMd5;
        this.dateTo = dateTo;
        this.dateFrom = dateFrom;
        this.certFileName = certFileName;
    }

    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public byte[] getContent() {
        return content;
    }

    public void setContent(byte[] content) {
        this.content = content;
    }

    public String getChecksumMd5() {
        return checksumMd5;
    }

    public void setChecksumMd5(String checksumMd5) {
        this.checksumMd5 = checksumMd5;
    }

    public Date getDateTo() {
        return dateTo;
    }

    public void setDateTo(Date dateTo) {
        this.dateTo = dateTo;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public String getCertFileName() {
        return certFileName;
    }

    public void setCertFileName(String certFileName) {
        this.certFileName = certFileName;
    }
    
    public Long getUserId() {
        return user == null ? null : user.getId();
    }
}
