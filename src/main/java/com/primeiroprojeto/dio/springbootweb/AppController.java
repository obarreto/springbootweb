package com.primeiroprojeto.dio.springbootweb;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AppController {
	
	@Value("${app.message}")
	private String appMessage;
	
	@GetMapping("/") // Toda vez que subir no projetoa configuração na porta localhost:8080
	public String getAppMessage() {
		return appMessage;
	}
}
