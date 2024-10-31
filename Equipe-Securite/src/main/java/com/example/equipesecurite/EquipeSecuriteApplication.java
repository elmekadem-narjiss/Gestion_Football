package com.example.equipesecurite;

import com.example.equipesecurite.configuration.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class EquipeSecuriteApplication {

  public static void main(String[] args) {
    SpringApplication.run(EquipeSecuriteApplication.class, args);
  }

}
