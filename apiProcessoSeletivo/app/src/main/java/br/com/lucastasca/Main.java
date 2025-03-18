package br.com.lucastasca;

import br.com.lucastasca.api.config.WebServerConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
        SpringApplication.run(WebServerConfig.class, args);
    }
}