package com.tunaweza.monitoring.configuration;

import com.tunaweza.monitoring.constants.UtilConstant;
import jakarta.servlet.ServletContext;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class StaticResourceConfiguration implements WebMvcConfigurer {
    private final ServletContext servletContext;

    @Override
    public void addResourceHandlers(@SuppressWarnings("null") ResourceHandlerRegistry registry) {
        // Utiliser un chemin absolu si servletContext ne fonctionne pas
        String qrCodeLocation = "file:" + servletContext.getRealPath(UtilConstant.STATIC_APP_FOLDER) + "/";

        registry.addResourceHandler("/qr-code/**")
                .addResourceLocations(qrCodeLocation);
    }
}