package br.com.greenmile.ponto_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
public class PontoApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);
	}
}
