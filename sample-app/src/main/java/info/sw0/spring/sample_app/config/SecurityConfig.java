package info.sw0.spring.sample_app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity(debug = false)
public class SecurityConfig {
  
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http
      .authorizeRequests()
        .mvcMatchers("/public/**").permitAll()
        .anyRequest().authenticated()
      .and().formLogin(Customizer.withDefaults());
      return http.build();

  }

  @Bean
  UserDetailsService users() {
      UserDetails user = User.withDefaultPasswordEncoder()
        .username("admin")
        .password("password")
        .roles("USER")
        .build();
      return new InMemoryUserDetailsManager(user);
  }
}
