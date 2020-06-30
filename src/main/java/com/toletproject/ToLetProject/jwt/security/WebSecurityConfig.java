package com.toletproject.ToLetProject.jwt.security;


import com.toletproject.ToLetProject.jwt.security.jwt.JwtAuthEntryPoint;
import com.toletproject.ToLetProject.jwt.security.jwt.JwtAuthTokenFilter;
import com.toletproject.ToLetProject.jwt.security.services.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.csrf.CsrfTokenRepository;
import org.springframework.security.web.csrf.HttpSessionCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
        prePostEnabled = true,
        securedEnabled = true,
        jsr250Enabled = true
)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtAuthEntryPoint unauthorizedHandler;

    @Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CsrfTokenRepository csrfTokenRepository() {
        HttpSessionCsrfTokenRepository repository = new HttpSessionCsrfTokenRepository();
        repository.setHeaderName("X-XSRF-TOKEN");
        return repository;
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.cors().and().csrf().disable().
                authorizeRequests()
                .antMatchers("/").access("hasAuthority('SUPER_ADMIN')")
                .antMatchers("/api/auth/user/edit").access("hasAnyAuthority('USER','OWNER')")
                .antMatchers(HttpMethod.GET, "/api/auth/areas").permitAll()
                .antMatchers(HttpMethod.POST, "/api/auth/areas").access("hasAuthority('SUPER_ADMIN')")
                .antMatchers("/api/auth/**").permitAll()
                .antMatchers("/public/**").permitAll()
                .antMatchers("/user").permitAll()
                .antMatchers("/v2/**").permitAll()
                .antMatchers("/swagger-resources/**").permitAll()
                .antMatchers("/webjars/springfox-swagger-ui/**").permitAll()
                .antMatchers("/swagger-ui.html").permitAll()
                .antMatchers("/faculty/**").access("hasAuthority('FACULTY')")
                .antMatchers("/owner/**").access("hasAuthority('OWNER')")
                .antMatchers("/user/**").access("hasAuthority('USER')")
                .antMatchers("/user/**").access("hasAuthority('OWNER')")
                .antMatchers("/erp/**").access("hasAuthority('ADMIN')")
                .antMatchers("/erp/**").access("hasAuthority('SUPER_ADMIN')")
                .antMatchers("/student/**").access("hasAuthority('STUDENT')")
                .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().httpBasic();

        http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
    }
}