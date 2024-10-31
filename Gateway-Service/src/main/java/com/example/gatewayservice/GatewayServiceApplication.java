package com.example.gatewayservice;

import com.example.gatewayservice.configue.RsaConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.client.discovery.ReactiveDiscoveryClient;
import org.springframework.cloud.gateway.discovery.DiscoveryClientRouteDefinitionLocator;
import org.springframework.cloud.gateway.discovery.DiscoveryLocatorProperties;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableConfigurationProperties(RsaConfig.class)
public class GatewayServiceApplication {

  public static void main(String[] args) {
    SpringApplication.run(GatewayServiceApplication.class, args);
  }
 @Bean
 DiscoveryClientRouteDefinitionLocator locator
 (ReactiveDiscoveryClient rdc, DiscoveryLocatorProperties dpp){
    return new DiscoveryClientRouteDefinitionLocator(rdc,dpp);
 }

}
