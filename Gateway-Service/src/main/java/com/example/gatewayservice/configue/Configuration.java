package com.example.gatewayservice.configue;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.oauth2.jwt.NimbusReactiveJwtDecoder;
import org.springframework.security.oauth2.jwt.ReactiveJwtDecoder;

@EnableWebFluxSecurity
//@EnableWebSecurity
public class Configuration {

  private RsaConfig rsaConfig ;

  public Configuration(RsaConfig rsaConfig) {
    this.rsaConfig = rsaConfig;
  }


  @Bean
  public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
    httpSecurity
      .csrf(csrf -> csrf.disable())
      .authorizeExchange(auth -> auth.pathMatchers("/Service_Equipe/equipes/email/{id}").permitAll())
      .authorizeExchange(auth -> auth.pathMatchers("/Service_Player/players/email/{id}").permitAll())
      .authorizeExchange(auth -> auth.pathMatchers("/Service_Equipe/equipes/**").hasAuthority("Secondaire"))
      .authorizeExchange(auth -> auth.pathMatchers("/Service_Player/players**").hasAuthority("Principale"))
      .authorizeExchange(auth -> auth.anyExchange().authenticated())
      .oauth2ResourceServer(oauth2 -> oauth2.jwt());
    return httpSecurity.build();
  }
  @Bean
  public ReactiveJwtDecoder jwtDecoder() {
    return NimbusReactiveJwtDecoder.withPublicKey(rsaConfig.publicKey()).build();
  }
}
