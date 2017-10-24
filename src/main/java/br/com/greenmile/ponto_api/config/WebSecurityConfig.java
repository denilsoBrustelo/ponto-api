package br.com.greenmile.ponto_api.config;

import br.com.greenmile.ponto_api.common.utils.BCryptUtil;
import br.com.greenmile.ponto_api.security.JWTAuthenticationFilter;
import br.com.greenmile.ponto_api.security.JWTLoginFilter;
import br.com.greenmile.ponto_api.service.UsuarioDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioDetailsService usuarioDetailsService;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(BCryptUtil.LOG_ROUNDS);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeRequests()
                .antMatchers("/").permitAll()
                .antMatchers(HttpMethod.POST, "/login").permitAll()

                .antMatchers(HttpMethod.GET, "/api/v1/usuarios").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v1/usuarios/**").permitAll()

                .antMatchers(HttpMethod.GET, "/api/webjars/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/swagger-ui.html").permitAll()
                .antMatchers(HttpMethod.GET, "/api/swagger-ui.html/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/swagger-resources/**").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/api/v2/api-docs/**").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs").permitAll()
                .antMatchers(HttpMethod.GET, "/v2/api-docs/**").permitAll()

                .anyRequest().authenticated()

                .antMatchers(HttpMethod.POST, "/api/v1/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.PUT, "/api/v1/usuarios").hasRole("ADMIN")
                .antMatchers(HttpMethod.DELETE, "/api/v1/usuarios").hasRole("ADMIN")

                .and()
                // We filter the api/login requests
                .addFilterBefore(new JWTLoginFilter("/login", authenticationManager()),
                        UsernamePasswordAuthenticationFilter.class)
                // And filter other requests to check the presence of JWT in header
                .addFilterBefore(new JWTAuthenticationFilter(),
                        UsernamePasswordAuthenticationFilter.class);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(this.usuarioDetailsService).passwordEncoder(passwordEncoder());
    }
}
