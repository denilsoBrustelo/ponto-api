package br.com.greenmile.ponto_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@EnableCaching
@RestController
@SpringBootApplication
public class PontoApiApplication {

	@RequestMapping("/")
	String home() {
		return "Lá vamos nós!";
	}

	public static void main(String[] args) {
		SpringApplication.run(PontoApiApplication.class, args);
	}
}
