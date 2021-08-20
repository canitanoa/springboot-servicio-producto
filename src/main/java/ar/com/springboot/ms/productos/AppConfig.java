package ar.com.springboot.ms.productos;

import java.util.Collections;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration // Para crear las configuraciones
@EnableSwagger2 // Para la Auto-Documentacion
@EnableEurekaClient // Para que sea Cliente de Eureka y pueda registrarse en el server
@EntityScan({"ar.com.springboot.ms.*"}) //Para escanear los pakage
public class AppConfig {

	// Definicion de Swagger
	@Bean
	public Docket apiDocket() {
		return new Docket(DocumentationType.SWAGGER_2).select()
				.apis(RequestHandlerSelectors.basePackage("ar.com.springboot.ms.productos.controllers"))
				.paths(PathSelectors.any()).build().apiInfo(getApiInfo());
	}

	private ApiInfo getApiInfo() {
		return new ApiInfo("Producto Service API", "Producto Service API Description", "1.0", "http://ejempo.com/terms",
				new Contact("Canitano", "https://adriancanitano.com", "acanitano@gmail.com"), "LICENSE", "LICENSE URL",
				Collections.emptyList());
	}
	//........
	
	
}