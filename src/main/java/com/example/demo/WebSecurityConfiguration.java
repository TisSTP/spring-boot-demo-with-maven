package com.example.demo;

import org.springframework.boot.actuate.autoconfigure.security.servlet.EndpointRequest;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

/**
 * @author sathaphorn.stp (T.STP)
 * @since 11-11-2018, 22:46
 */
@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

  @Bean
  public InMemoryUserDetailsManager inMemoryUserDetailsManager() {
    return new InMemoryUserDetailsManager(
        User.withDefaultPasswordEncoder()
            .username("user")
            .password("passwprd")
            .authorities("ROLE_USER")
            .build(),
        User.withDefaultPasswordEncoder()
            .username("admin")
            .password("admin")
            .authorities("ROLE_ACTUATOR", "ROLE_ADMIN", "ROLE_USER")
            .build()
    );
  }


  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
        .requestMatchers(EndpointRequest.to("info", "health")).permitAll()
        .requestMatchers(EndpointRequest.toAnyEndpoint()).hasRole("ACTUATOR")
        .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
        .mvcMatchers("/admin").hasRole("ADMIN")
        .antMatchers("/events/**").hasRole("USER")
        .antMatchers("/**").permitAll()
        .and().httpBasic();
  }

}
