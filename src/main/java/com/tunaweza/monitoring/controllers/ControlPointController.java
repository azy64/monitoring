package com.tunaweza.monitoring.controllers;

import java.io.File;
import java.util.*;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tunaweza.monitoring.constants.UtilConstant;
import com.tunaweza.monitoring.contract.AroundServiceInterface;
import com.tunaweza.monitoring.contract.ControlPointServiceInterface;
import com.tunaweza.monitoring.contract.QrCodeInterface;
import com.tunaweza.monitoring.dto.AroundDTO;
import com.tunaweza.monitoring.exception.ResourceAlreadyExistException;
import com.tunaweza.monitoring.mapper.AroundMapper;
import com.tunaweza.monitoring.mapper.ControlPointMapper;
import com.tunaweza.monitoring.model.ControlPoint;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
@Validated
public class ControlPointController {
    private final ControlPointServiceInterface controlPointService;
    private final AroundServiceInterface aroundService;
    private final QrCodeInterface qrCodeService;
    private final ServletContext servletContext;

    @PostMapping("/control-point")
    public ResponseEntity<?>createControlPoint(@RequestBody ControlPoint controlPoint, HttpServletRequest request) throws ResourceAlreadyExistException{
        UUID controlPointId = controlPoint.getAround().getId();
        controlPoint.setCreateAt(new Date());
        String timestamp = controlPoint.getCreateAt().getTime()+"";


        StringBuilder sb = new StringBuilder();
        sb.append(controlPointId.toString()).append("==").append(controlPoint.getLabel()).append("==").append(timestamp);

        String encodedString = Base64.getEncoder().encodeToString(sb.toString().getBytes());

        String fileName = createQrCode(encodedString, request);
        controlPoint.setQrCode(fileName);
        return ResponseEntity.ok(ControlPointMapper.mapToDto(controlPointService.save(controlPoint)));
    }

    @GetMapping("/control-point/{id}")
    public ResponseEntity<?>getControlPoint(@PathVariable UUID id){
        return ResponseEntity.ok(ControlPointMapper.mapToDto(controlPointService.findById(id)));
    }

    @GetMapping("/control-point")
    public ResponseEntity<?>getControlPoint(){
        return ResponseEntity.ok(
            controlPointService.findAll().stream()
            .map(control->ControlPointMapper.mapToDto(control)).toList()
        );
    }

    @DeleteMapping("/control-point/{id}")
    public ResponseEntity<?> deleteCoontrolPoint(@PathVariable UUID id){
        controlPointService.delete(id);
        return ResponseEntity.ok(HttpStatus.GONE);
    }

    @PutMapping("/control-point/{id}")
    public ResponseEntity<?> updateCoontrolPoint(@PathVariable UUID id, ControlPoint controlPoint){
        return ResponseEntity.ok(ControlPointMapper.mapToDto(controlPointService.update(controlPoint, id)));
    }

    @GetMapping("/around/{id}/control-point")
    public ResponseEntity<?>getcontrolPointByAround(@PathVariable UUID id){
        AroundDTO around = aroundService.findAroundById(id);
        if(around!=null)
        return ResponseEntity.ok(
            controlPointService.findControlPointByAround(AroundMapper.mapToEntity(around))
        );
        return ResponseEntity.badRequest().body("Operation not supported");
    }

    public String getServerHost(HttpServletRequest request){
        return request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
    }

    
    public String createQrCode(String text,HttpServletRequest request){

        String filename=UtilConstant.STATIC_QRCODE_FILE_NAME;
        //String pathFileName=UtilConstant.STATIC_APP_FOLDER+filename;
        String qrCodeDirectory = servletContext.getRealPath(UtilConstant.STATIC_APP_FOLDER);
        File directory = new File(qrCodeDirectory);

        if (!directory.exists()) {
            directory.mkdirs();
        }
        String pathFileName = qrCodeDirectory + File.separator + filename;

        qrCodeService.initializeValue(text, UtilConstant.STATIC_QRCODE_WIDTH);
        qrCodeService.writeImage(pathFileName, "png");

        String imageUrl = getServerHost(request) + "/qr-code/" + filename;
        //Map<String, String> response = new HashMap<>();
        //response.put("qrCodeUrl", imageUrl);

        return imageUrl;
    }
}
