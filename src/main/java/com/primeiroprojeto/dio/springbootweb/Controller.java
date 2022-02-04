package com.primeiroprojeto.dio.springbootweb;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController// é uma notação que fala parao spring boot que se trata de um ponto de Bean,
public class Controller {
	
	@GetMapping("/") //vai falar para o servido apartir do caminho inicila vai dar o retorno da mensagem via get quando abirir quando abriri o localhost na porta padrão que o spring dar o start do projeto 
	public String mensagem() {
		return "Nosso primeiro projeto Spring boot Web";
	}
}
