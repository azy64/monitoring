package com.tunaweza.monitoring.constants;

import lombok.Data;

@Data
public class UtilConstant {
    public static String STATIC_BASE_FOLDER="qr-code";
    public static String STATIC_APP_FOLDER ="src/main/resources/static/images/"+STATIC_BASE_FOLDER+"";
    public static String STATIC_QRCODE_FILE_NAME =System.currentTimeMillis()+".png";
    public static int STATIC_QRCODE_WIDTH =400;


}
