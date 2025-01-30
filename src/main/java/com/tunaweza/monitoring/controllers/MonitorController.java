/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tunaweza.monitoring.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.Data;

/**
 *
 * @author azaria
 */
@RestController
@Data
@RequestMapping("/monitoring")
public class MonitorController {
    @Value("${owner.name}")
    private String owner;
    @GetMapping("/homepage")
    public String index(){
        return "Je suis la page homepage:"+this.owner;
    }
}
