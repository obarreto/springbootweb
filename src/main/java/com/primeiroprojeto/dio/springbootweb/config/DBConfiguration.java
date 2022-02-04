package com.primeiroprojeto.dio.springbootweb.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.Data;

@Configuration
@ConfigurationProperties("spring.datasource")
@Data
public class DBConfiguration {
	
	private String driverClassName;
	private String url;
	private String username;
	private String password;
	
	//O mapeamento das propriedades para dentro do nosso sistema 
	//e onde vai subir o tipo de profile
	@Profile("dev") //resources application-dev.properties
	@Bean //Queremos mostrar que todo esse conteú será mapeado logo na subida do nosso sistema
	public String testDatabaseConnection() {
		System.out.println("DB connection for DEV- H2");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection to H2_TEST - Test instance";
		
	}
	
	@Profile("prod")
	@Bean
	public String productionDatabaseConnection() {
		System.out.println("DB connection for Production- MySql");
		System.out.println(driverClassName);
		System.out.println(url);
		return "DB Connection to MYSQL_PROD - Production instance";
		
	}
}
