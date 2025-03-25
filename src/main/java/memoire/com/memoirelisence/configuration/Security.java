package memoire.com.memoirelisence.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class Security {
    private JwtFilter jwtFilter;
    private UserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
        return  httpSecurity.cors().and().csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        authorize->
                                authorize
                                        .requestMatchers("/auth/**").permitAll()
                                        //.requestMatchers("/auth/login").authenticated()
                                        .requestMatchers("/api/role/**").hasRole("ADMIN")
                                        .requestMatchers("/api/utilisateur/**").hasRole("ADMIN")
                                        .requestMatchers("/api/cercle/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/commune/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/declaration/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/mere/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/peres/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/quartier/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/regions/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/sage_femme/**").hasAnyRole("ADMIN", "Hopital")
                                        .requestMatchers("/api/naissance/**").hasAnyRole("ADMIN", "Mairie")
                                        .requestMatchers("/api/officier/**").hasAnyRole("ADMIN", "Mairie")
                                        .anyRequest().authenticated()




                )
                .sessionManagement(httpSecuritySessionManagementConfigurer -> httpSecuritySessionManagementConfigurer.sessionCreationPolicy((SessionCreationPolicy.STATELESS) ))
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();

    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return  authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder);
        return daoAuthenticationProvider;
    }
}
