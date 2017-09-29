package com.example.demo;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	public UserDao userDao;

	private CsrfTokenRepository csrfTokenRepository() {
		HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
		repository.setSessionAttributeName("_csrf");
		return repository;
	}

	// @Override
	// protected void configure(HttpSecurity http) throws Exception {
	// http.csrf()
	// .csrfTokenRepository(csrfTokenRepository())
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/*", "/home", "/getchat").permitAll().anyRequest().authenticated().and()
				.formLogin().defaultSuccessUrl("/chat", true).loginPage("/login").permitAll().and().logout()
				.permitAll();
		http.authorizeRequests().antMatchers("/resources/**").permitAll().anyRequest().permitAll();

		http.csrf().disable();
	}

	// Liste Benutzer laden
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		List<user> dutchlauf = (List<user>) userDao.findAll();
		for (int i = 0; i <= userDao.count(); i++) {
			for (user a : dutchlauf) {
				String username = "" + a.getId();
				auth.inMemoryAuthentication().withUser(username).password(a.getPassword()).roles("USER");
			}

		}

	}
}
