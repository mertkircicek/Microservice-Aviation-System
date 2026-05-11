package com.aviation.zuul_gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

// Yeni eklenen kütüphaneler (CORS işlemleri için)
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableZuulProxy
@EnableEurekaClient
public class ZuulGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZuulGatewayApplication.class, args);
	}

	// FRONTEND İÇİN GÜVENLİK İZNİ (CORS AYARI)
	@Bean
	public CorsFilter corsFilter() {
		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		final CorsConfiguration config = new CorsConfiguration();

		config.setAllowCredentials(true);
		config.addAllowedOrigin("http://localhost:3000"); // Nuxt 3 arayüzümüze izin veriyoruz
		config.addAllowedHeader("*"); // Tüm başlıklara izin ver
		config.addAllowedMethod("*"); // Tüm işlemlere (GET, POST, PUT, DELETE) izin ver

		source.registerCorsConfiguration("/**", config); // Tüm API yollarında geçerli yap
		return new CorsFilter(source);
	}

}