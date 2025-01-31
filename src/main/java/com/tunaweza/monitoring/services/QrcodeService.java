package com.tunaweza.monitoring.services;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
//import com.kuisama.zxing.util.MatrixToImageWriter;
import com.tunaweza.monitoring.contract.QrCodeInterface;

import lombok.Getter;
import lombok.Setter;

@Service
@Getter
@Setter
public class QrcodeService implements QrCodeInterface {

    private String text;
    private int size;

    @Override
    public BitMatrix generateMatrix() throws WriterException  {
        BitMatrix matrix = new QRCodeWriter().encode(this.getText(), BarcodeFormat.QR_CODE, this.getSize(), this.getSize());
        return matrix;
    }

    @Override
    public void writeImage(String outputFileImage, String imageFormat) {
        try {
            BitMatrix matrix= generateMatrix();
            try (FileOutputStream fileOutputStream = new FileOutputStream(new File(outputFileImage))) {
                com.google.zxing.client.j2se.MatrixToImageWriter.writeToStream(matrix, imageFormat, fileOutputStream);
                //MatrixToImageWriter.writeToStream(matrix, imageFormat, fileOutputStream);
            }
        } catch (WriterException | IOException e) {
        }
    }

    @Override
    public void initializeValue(String text, int size){
        this.setSize(size);
        this.setText(text);
    }

}
