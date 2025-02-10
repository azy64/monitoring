/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tunaweza.monitoring.controllers;

import java.net.MalformedURLException;
//import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.contract.QrCodeInterface;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author azaria
 */
@RestController
@AllArgsConstructor
@Getter
@Setter
@RequestMapping("/monitoring")
public class MonitorController {
    
    private QrCodeInterface qrCodeService;
    //@Value("${owner.name}")
    //private final String owner;
    @Autowired
    private ServletContext server;
    
    @GetMapping("/homepage")
    public String index(HttpServletRequest request) throws MalformedURLException{
       
            //@SuppressWarnings("deprecation")
            //URL url = new URL(request.getRequestURL().toString());
            String filename="qr-code"+System.currentTimeMillis()+".png";
            String pathFileName="./src/main/resources/static/qr-code/"+filename;
            qrCodeService.initializeValue("Dieu est bon", 400);
            qrCodeService.writeImage(pathFileName, "png");
            //String urlPathFile=url.getProtocol()+"://"+url.getHost()+":"+url.getPort();
            String img="<img src='"+getServerHost(request)+"/qr-code/"+filename+"' title='qrcode' alt='qrcode-image'/>";
            return img;
        //return "";
        
    }
    public String getServerHost(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    }
}
