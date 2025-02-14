/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.tunaweza.monitoring.controllers;

import java.io.File;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.Map;
//import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
@RequestMapping("/auth")
public class MonitorController {
    
    private QrCodeInterface qrCodeService;
    //@Value("${owner.name}")
    //private final String owner;
    @Autowired
    private ServletContext server;

    @GetMapping("/homepage")
    public ResponseEntity<Map<String, String>> index(HttpServletRequest request) throws MalformedURLException {

        String filename = "qr-code-" + System.currentTimeMillis() + ".png";

        String qrCodeDirectory = server.getRealPath("/qr-code/");
        File directory = new File(qrCodeDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }

        String pathFileName = qrCodeDirectory + File.separator + filename;

        qrCodeService.initializeValue("Dieu est bon", 400);
        qrCodeService.writeImage(pathFileName, "png");

        String imageUrl = getServerHost(request) + "/qr-code/" + filename;

        Map<String, String> response = new HashMap<>();
        response.put("qrCodeUrl", imageUrl);
        return ResponseEntity.ok(response);
    }

    private String getServerHost(HttpServletRequest request) {
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
    }
}
