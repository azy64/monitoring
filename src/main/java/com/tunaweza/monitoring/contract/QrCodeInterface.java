package com.tunaweza.monitoring.contract;

import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

public interface QrCodeInterface {

    public BitMatrix generateMatrix() throws WriterException;
    public void writeImage(String outputFileImage, String imageFormat);
    public void initializeValue(String text, int size);

}
