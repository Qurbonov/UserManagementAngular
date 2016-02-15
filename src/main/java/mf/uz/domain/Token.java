/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mf.uz.domain;

import java.util.Date;
import javax.persistence.*;

/**
 *
 * @author Qurbonov
 */
@Entity
@Table(name = "Token")
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "UUID")
    private String UUID;

    @Column(name = "host_IP")
    private String host_IP;

    @Column(name = "is_Verified")
    private boolean is_Verified;

    @Column(name = "create_Date")
    private Date create_Date;

    public Token() {
    }

    public Token(Long id, String UUID, String host_IP, boolean is_Verified, Date create_Date) {
        this.id = id;
        this.UUID = UUID;
        this.host_IP = host_IP;
        this.is_Verified = is_Verified;
        this.create_Date = create_Date;
    }

    @Override
    public String toString() {
        return "Token{" + "id=" + id + ", UUID=" + UUID + ", host_IP=" + host_IP + ", is_Verified=" + is_Verified + ", create_Date=" + create_Date + '}';
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUUID() {
        return UUID;
    }

    public void setUUID(String UUID) {
        this.UUID = UUID;
    }

    public String getHost_IP() {
        return host_IP;
    }

    public void setHost_IP(String host_IP) {
        this.host_IP = host_IP;
    }

    public boolean getIs_Verified() {
        return is_Verified;
    }

    public void setIs_Verified(boolean is_Verified) {
        this.is_Verified = is_Verified;
    }

    public Date getCreate_Date() {
        return create_Date;
    }

    public void setCreate_Date(Date create_Date) {
        this.create_Date = create_Date;
    }
}
