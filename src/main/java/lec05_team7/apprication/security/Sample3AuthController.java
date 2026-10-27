package lec05_team7.apprication.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Sample3AuthController {

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http.formLogin(login -> login
        .permitAll())
        .logout(logout -> logout
            .logoutUrl("/logout")
            .logoutSuccessUrl("/"))
        .authorizeHttpRequests(authz -> authz
            .requestMatchers("/sample5/**").authenticated()
            .anyRequest().permitAll());
    return http.build();
  }

  @Bean
  public InMemoryUserDetailsManager userDetailsService() {

    // pass:c1
    UserDetails customer1 = User.withUsername("customer1")
        .password("{bcrypt}$2y$05$FZslfonwEPCZV4pfcGiHvu5Pj9ZOjzWJCO5FvB65.6vBFRx8LC22m")
        .roles("CUSTOMER")
        .build();
    // pass:c2
    UserDetails customer2 = User.withUsername("customer2")
        .password("{bcrypt}$2y$05$1yDw6h3TJrxNJVg7nV2MKeDyPtu9WhEU.iw63OGTIwIU52o1fEBlq")
        .roles("CUSTOMER")
        .build();
    // pass:s
    UserDetails seller = User.withUsername("seller")
        .password("{bcrypt}$2y$05$Xau9.yIRAA3bvCrxI0JVWODKde2jbAFt5Jxyuv9GZRIidOUN5G75e")
        .roles("SELLER")
        .build();

    return new InMemoryUserDetailsManager(customer1, customer2, seller);
  }
}
