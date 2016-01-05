/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mf.uz.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Qurbonov
 */
@RestController
public class MainController {

    @RequestMapping(value = "/api")
    public String index1() {
        return "";
    }
}
