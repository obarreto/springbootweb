# Problemas do Spring

* Configuraçẽos de beans em arquivos xml.

* Dispatcher Servlet e view resolver em web.xml

* Setup manual de Banco de Dados

* Muito tempo gasto em configuração

* Perda de foco em valor - entregar o que realmente importante.

  

# O que é o Spring Boot ?

* Criado pela Spring Source em 2012.
* Facilita o setup de projetos Spring.
* Sem necessidade de criar arquivos de configuração.
* Foco em produtividade
* Maior tempo no desenvolvimento de valor.



O Spring Boot é uma extensão do Spring, que utiliza do Spring Framework para iniciar um aplicação de forma simples e rápida, sem se preocupar com configurações complexas como, por exemplo, o Tomcat.



Componentes base do Spring Boot: 

* Spring Boot Starter:
  * Denependências auto configuráveis pelo Spring Boot

* Spting Boot Auto-Configuration;
* Spring Boot Actuator;



Como funciona o Spring Boot funciona ?

Em resumo o Spting Boot é como um template pré-configurado para desenvolvimento e execução de aplicações baseadas no Spring.

* Execução simplificada: sem deploy em servidor extreno.

Onde, temos uma arquitetura baseada no Spring Core, que traz os componentes-base do Spring, como o mecanismo do DI/IoC, a Spring Expression Language(SpEL) e alguns módulos-base do Spring AOP (módulo para implementação de programação orientada a aspectos no Spring).



* O Spring Boot permite adicionar a estrutura-base o que é chamado de módulo.

* Um módulo é na verdade uma biblioteca ou ferramenta do ecossistema Spring que pode ser adicionada a uma aplicação Spring convencional.

  Spring boot **=** Spring Framework **+** Servidor Embutido(Tomcat ou Jetty) **-** XML<bean> Configuration ou @Configuration

# Dependências base do Spring Boot

```xml
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter</artifactId>
	</dependency>
    
    <dependency>
    	<groupId>org.springframework.boot</groupId>
    	<artifactId>spring-boot-starter-test</artifactId>
    	<scope>test</scope>
    </dependency>
</dependencies>
```

____



# Spring Initializr



O Spring Initializr fornece uma API extensível para gerar projetos baseados em JVM (Java Virtual Machine) é onde podemos criar um projeto Spring Boot de forma rápida e fácil, através do site: 

https://start.spring.io/

____

# @SpringBootApplication



O Auto-configuration ou configuração automática do Spring Boot configura automaticamente a aplicação Spring com base nas dependências `jar` que adcionamos ao projeto - desde JPA, JDBC...

Para habilitar o recurso existe a ***anotação***

* @EnableAutoConfiguration, mas essa não é usada porque está contida no @SpringBootApplication. A anatoção @SpringBootApplication é a combinação de três anatoções. 

  @SpringBootApplication **=** @ComponentScan **+** @EnableAutoConfiguration **+** @Configuration

  * A partir dessa classe SpringbootwebApplication.java que irá iniciar o projeto

```java
package com.primeiroprojeto.dio.springbootweb;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootwebApplication{
    public static void main(String[] args){
        SpringApplication.run(SpringbootwebApplication.class, args);
    }
}
```

```
@ org.springframework.boot.autoconfigure.SpringBootApplication

@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan(excludeFilters={@Filter(type=CUSTOM, classes={TypeExcludeFilter.class}),@Filter(type=CUSTOM, classes={AutoConfigurationExcludeFilter.class})})

Indicates a configuration class that declares one or more @Bean methods and also triggers auto-configuration and component scanning. This is a convenience annotation that is equivalent to declaring @Configuration @EnableAutoConfiguration and @ComponentScan.

Since:
	1.2.0
Author:
	Philip Webb
	Stephane Nicoli
	Andy Wilkinson
```

____



# Outras Notações Lombok

`@Data`: Implementa Getters e Setters.

`@Builder`: Desgin Patterns

Modelagem End points -> Aquilo que o aplicativo vai se comunicar com a API.

Não são as classes criadas como modelo que será passada para o aplicativo. Pois as classes de modelo possuem alguns dados sensiveis. Ex.: Id, nem sempre é exportado para o aplicativo - e muitas vezes há necessidade de ocultar alguns dados. Para isso é ncessário criar uma classe DTO -  Data Object. É essa que será responsável de comunicação e captação de dados dos App. Geralmente essas classes tem o mesmos campos.



`@JsonInclude(Include.non_null)`

* Caso algun dado estiver em branco não vai incluir o `Json` - ou seja no objeto que vai ser enviado para o aplicativo isso diminui o tamnho do ` Json`. Passa a ser uma boa prática, pois se o atributo do objeto está nulo não há necessidade de envia-l

___



# Motivação do Spring Boot

Resumidamente o Spring Boot veio para facilitar a vida do desenvolvedor java e melhorar a produtividade do desenvolvimento de softwares. O Spring Boot facilita todo esse processo, fazendo com que o desenvolvedor se concentre mais na parte realmente importante que envolve as regras do negócio e código.

____



# Swagger

Swagger é um projeto open source com diversas ferramentas utilizadas para desenvolver APIs com a especificação OpenAPI Specification(OAS), uma especificação para descrever, produzir, consumir e visualizar serviços de uma API REST. Com o OAS você poderá descrever recursos, URIs, modelo de dados, métodos HTTP aceitos e códigos de resposta.

Tudo isso facilita a vida dos desenvolvedores de diversas linguagens de programação na criação, testes, consumo e documentação de APIs REST.

Dependência de Swagger

```xml
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger2</artifactId>
	<version>2.9.2</version>
</dependency>
<dependency>
	<groupId>io.springfox</groupId>
	<artifactId>springfox-swagger-ui</artifactId>
	<version>2.9.2</version>
</dependency>	
```

```java
@Configuration
@EnableSwagger2 //inicializar o Swagger
//Classe da dependẽncia do Swagger Utilizada para passar informações para odcumentação da API
public class SwaggerConfig{
    /*Ao utilizar o Swagger em nossa aplicação precisa definir as apis e caminhos disponíveis para documentar uma API, para isso usamos o Docket*/
    @Bean
    public Docket api(){
    	return new Docket(DocumentatioType.SWAGGER_2)
            .select()
            .apis(RequestHandlerSlectors.basePackage("passar.o.caminho.do.controle"))
            .path(PathSelectors.any())
            .build()
            .apiInfo(buildApiInfo)
    }
        
    private ApiInfo buildApiInfo(){ 
        return new ApiInfoBuilder()
            .title("Api e seu nome")
            .decription("REST API e seu nome")
            .verison("1.0.1 - Versão da API")
            .contact(new Contact( // recebe três String
                "Ozeias Barreto",
            	"github/obarreto",
            	null))
            .build();
    }
}
```

____



# Feign

O Feign é uma anotação do Spring Cloud que torna possível realizar o consumo e integrações de APIs. Um cliente de serviço web declarativo (cliente HTTP) desenvolvido pela Netflix e um dos mais populares do Spring Cloud Component. Com ele é possível criar clientes API HTTP no Java de forma mais simples para chamar/consumir os serviços REST, utilizando anotações.

Para usar o Feign, precisamos adicionar a dependência `spring-cloud-starter-openfeign` no arquivo pom.xml

____



# Autenticação Com SpringBoot

Para manter a segurança das suas APIs, uma das formas mais comuns de autenticação com Spring Boot é o JWT - uma notação do javascript JSON Web Tokens. Que permite a geração de uma chave, que chamamos de Token, que ao ser gerado será utilizado na demais APIs por um determinado tempo. É uma forma de atutenticar o seu usuário na aplicação.



### Auth0 - Implementação JWT

Auth0 implementa as regras para geração do token JWT de maneira fácil e simples.

O componete possui a implementação de diversos algoritmos para simplificar e geração e leitura do token.

```xml
<dependency>
	<groupId>com.auth0</groupId>
    <artifactId>java-jwt</artifactId>
    <version>3.13.0</version>
</dependency>
```

____



# Spring Boot Test



O Spring Boot facilita o desenvolvimento de testes de unidade e integração com o Spring Boot Test, um recurso bastante utilizado no desenvolvimento de aplicações Java para testar comportamentos do código e regras do negócio.

Para utilizarmos precisamos usar spring-boot-starter-test, que importa os módulos de teste Spring Boot, bem como JUnit Jupiter, AssertJ, Hamcrest e várias outras bibliotecas úteis.

____



# @SpringBootTest

A anotação **`@SpringBootTest`** é utilizada em uma classe de teste e diz ao Spring Boot para procurar uma classe de configuração principal, por exemplo, o **`@SpringBootApplication`**, é usá-la para iniciar um contexto de aplicativo Spring.



```java
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringboottestApplicationTests {

	@Test
	void contextLoads() {
	}

}
```



# Teste Unitários



Os testes unitários é a base da pirâmide de testes onde são realizados o maior número de testes ou de veria para garantir a fidelidade do código e regras do negócio.

____



# FatJar / UberJar

![](/home/ozeias/Downloads/img.jpeg)

https://developers.redhat.com/blog/2017/08/24/the-skinny-on-fat-thin-hollow-and-uber#

* Artefato do projeto pronto para execução
* Container web embutido na geração e execução (Tomcat)
* Deploy embarcado com outros containers são opcionais
* Dependências principais do projeto embarco

* Execução direta através de um único java -jar
  * `mvn package && java -jar target/spring-boot-example-0.1.0.jar`

* Podemos também gerar o war tradicional ...

  * create a war file ->  Gradle: some options in build. gradle 

    ```groovy
    apply plugins 'war'
    var {
    	baseName = 'myapp'
    	version = '0.5.0'
    }
    ```

  * Maven: `<packagin>war</packaging>`

​	

* no diretorio raiz do projeto`mvn clean package`
* no diretorio /target`java -jar springbootweb-0.0.1-SNAPSHOT.jar `

* Mostra tudo o que contem no jar`jar tf springbootweb-0.0.1-SNAPSHOT.jar | less`

____



# Spring Boot Profiles

* Configurações próprias para cada ambiente
* Ambientes com sua configuração: **dev, production**
