package io.javabrains.SpingSecurityH2DB.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

	@Autowired
	DataSource dataSource;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication()
		    .dataSource(dataSource)
		    .withDefaultSchema()
		    .withUser(User.withUsername("user").password("user").roles("USER"))
		    .withUser(User.withUsername("admin").password("admin").roles("ADMIN"));		    
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		    .authorizeRequests()
		    .antMatchers("/admin").hasRole("ADMIN")
		    .antMatchers("/user").hasAnyRole("USER","ADMIN")
		    .antMatchers("/").fullyAuthenticated()
		    .and().logout().clearAuthentication(true).logoutSuccessUrl("/logoutSuccess")
		    .and().exceptionHandling().accessDeniedPage("/unauthorized")
		    .and().formLogin();
		    
	}

	@Bean
	public static NoOpPasswordEncoder passwordEncoder() {
		return (NoOpPasswordEncoder) NoOpPasswordEncoder.getInstance();
	}
}
