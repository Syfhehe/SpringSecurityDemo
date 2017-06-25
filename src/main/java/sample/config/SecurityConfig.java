package sample.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.authentication.dao.ReflectionSaltSource;
import org.springframework.security.authentication.encoding.Md5PasswordEncoder;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import sample.service.TestingUserDetailService;

@Configuration
@EnableWebSecurity
@ComponentScan("sample.service")
public class SecurityConfig extends WebSecurityConfigurerAdapter {

  @Autowired
  private TestingUserDetailService userDetailService;

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http.authorizeRequests()
      .antMatchers("/css/**", "/fonts/**", "/js/**").permitAll()
      .anyRequest().fullyAuthenticated().and()
      .formLogin().loginPage("/login").failureUrl("/login?error").permitAll().and()
      .logout().permitAll();
    http.csrf().disable();
  }

  @Override
  public void configure(AuthenticationManagerBuilder auth) throws Exception {
    DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
    authProvider.setPasswordEncoder(passwordEncoder());
    authProvider.setUserDetailsService(userDetailService);
    ReflectionSaltSource saltSource = new ReflectionSaltSource();
    saltSource.setUserPropertyToUse("salt");
    authProvider.setSaltSource(saltSource);
    auth.authenticationProvider(authProvider);
  }
  
//  @Override
//  public void configure(AuthenticationManagerBuilder auth) throws Exception {
//      auth.inMemoryAuthentication().withUser("admin@ibm.com").password("admin")
//              .roles("ADMIN", "USER").and().withUser("user@ibm.com").password("user")
//              .roles("USER");
//  }
  
  @Bean
  public Md5PasswordEncoder passwordEncoder() {
    return new Md5PasswordEncoder();
  }

}
