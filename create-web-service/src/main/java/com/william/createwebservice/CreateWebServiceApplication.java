package com.william.createwebservice;

import com.corundumstudio.socketio.Configuration;
import com.corundumstudio.socketio.SocketIOServer;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@OpenAPIDefinition
public class CreateWebServiceApplication {

	@Value("${server.host}")
	private String host;

	@Value("${socketIO.server.port}")
	private Integer port;

	@Bean
	public SocketIOServer socketIOServer() {
		Configuration config = new Configuration();
		config.setHostname(host);
		config.setPort(port);
		config.setOrigin("http://" + host + ":" + port);
		return new SocketIOServer(config);
	}

	public static void main(String[] args) {
		SpringApplication.run(CreateWebServiceApplication.class, args);
	}

	@Bean
	public SpringApplicationContext springApplicationContext() {
		return new SpringApplicationContext();
	}

}
