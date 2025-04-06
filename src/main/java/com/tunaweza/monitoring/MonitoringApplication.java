package com.tunaweza.monitoring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//import java.util.Arrays;
//import java.util.List;

@SpringBootApplication
public class MonitoringApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringApplication.class, args);
	}

	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource urlBasedCorsConfigurationSource = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);
		corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:8081", "http://localhost:3000","http://192.168.9.7:8081","http://192.168.1.93:3000"));
		//corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
		corsConfiguration.setAllowedHeaders(Arrays.asList("Origin", "Access-Control-Allow-Origin", "Content-Type",
				"Accept", "Jwt-Token", "Authorization", "Origin", "Accept", "X-Requested-With",
				"Access-Control-Request-Method", "Access-Control-Request-Headers"));
		corsConfiguration.setExposedHeaders(Arrays.asList("Origin", "Content-Type", "Accept", "Jwt-Token", "Authorization",
				"Access-Control-Allow-Origin", "Access-Control-Allow-Origin", "Access-Control-Allow-Credentials", "File-Name"));
		corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
		corsConfiguration.setMaxAge(3600L);
		urlBasedCorsConfigurationSource.registerCorsConfiguration("http://**", corsConfiguration);
		return new CorsFilter(urlBasedCorsConfigurationSource);
	}
		*/

		@Bean
		public WebMvcConfigurer corsConfigurer(){
			return new WebMvcConfigurer() {
				@Override
				public void addCorsMappings(@SuppressWarnings("null") CorsRegistry registry){
					registry.addMapping("/auth/***").allowedOrigins("http://localhost:3000/").
					allowCredentials(false).allowedHeaders("*").
					allowPrivateNetwork(true).allowedMethods("*");
					
					registry.addMapping("/users/**").allowCredentials(true)
					.allowedMethods("GET","POST","OPTIONS","PUT","DELETE","HEAD")
					.exposedHeaders("*")
					.allowedHeaders("*").maxAge(360L).allowedOriginPatterns("http://**");

					registry.addMapping("/qr-code/**").allowCredentials(false)
					.allowedMethods("GET","HEAD")
					.exposedHeaders("*")
					.allowedHeaders("*");
				}
			};
		}

}
