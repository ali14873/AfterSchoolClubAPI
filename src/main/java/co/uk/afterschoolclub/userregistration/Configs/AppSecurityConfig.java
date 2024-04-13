package co.uk.afterschoolclub.userregistration.Configs;

import co.uk.afterschoolclub.userregistration.Constants.RoleConstants;
import co.uk.afterschoolclub.userregistration.Providers.ClubAuthenticationProvider;
import co.uk.afterschoolclub.userregistration.Teacher.TeacherApplicationService;
import com.nimbusds.jose.jwk.JWK;
import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.parameters.P;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtEncoder;
import org.springframework.security.oauth2.server.resource.web.access.BearerTokenAccessDeniedHandler;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationEntryPoint;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.security.web.context.SecurityContextRepository;

import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;

@EnableWebSecurity
@Configuration
public class AppSecurityConfig {

    @Value("${jwt.private.key}")
    RSAPrivateKey privateKey;

    @Value("${jwt.public.key}")
    RSAPublicKey publicKey;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((authorize)->authorize
                        //.requestMatchers("/students/create").permitAll() // Explicitly permit the create endpoint
                        .requestMatchers("/students/uploadCSV").permitAll() // Explicitly permit the create endpoint
                        .requestMatchers( "/students/**","/token").hasAuthority("SCOPE_"+RoleConstants.ADMIN)
                        .requestMatchers( "/students/**","/token").permitAll()
                        .requestMatchers( "/allergies/**","/token").permitAll()
                        .requestMatchers( "/teacher/**","/token").permitAll()
                        .requestMatchers( "/studentEmergencyInfo/**","/token").permitAll()
                        .requestMatchers( "/studentClubEnrollments/**","/token").permitAll()
                        .requestMatchers( "/resource-summary/**","/token").permitAll()
                        .requestMatchers( "/clubSessionResourceInfo/**","/token").permitAll()
                        .requestMatchers( "/parent/**","/token").permitAll()
                        .requestMatchers( "/sessions/**","/token").permitAll()
                        .requestMatchers( "/criticalIncidents/**","/token").permitAll()
                        .requestMatchers( "/sessionStaff/**","/token").permitAll()
                        .requestMatchers( "/resources/**","/token").permitAll()
                        .requestMatchers( "/resourceAllocations/**","/token").permitAll()
                        .requestMatchers( "/resourceInventory/**","/token").permitAll()
                        .requestMatchers( "/studentParent/**","/token").permitAll()
                        .requestMatchers( "/studentParent/**","/token").permitAll()
                        .requestMatchers( "/sessionResourceUsage/**","/token").permitAll()
                        .requestMatchers( "/clubInfo/**","/token").permitAll()
                        .requestMatchers( "/sessionComments/**","/token").permitAll()
                        .requestMatchers( "/Attendance/**","/token").permitAll()
                        .requestMatchers( "/sessionBookings/**","/token").permitAll()
                        .requestMatchers( "/club/**","/token").permitAll()
                        .requestMatchers( "/clubMember/**","/token").permitAll()
                        .requestMatchers( "/emergencyContact/**","/token").permitAll()
                        .requestMatchers( "/healthRecord/**","/token").permitAll()
                        .requestMatchers("/auth/login").permitAll()
                        .requestMatchers("/auth/admin/**").hasAuthority("SCOPE_"+ RoleConstants.ADMIN)
                        .anyRequest().authenticated())
                .httpBasic(Customizer.withDefaults())
                .oauth2ResourceServer(jwt->jwt.jwt(Customizer.withDefaults()))
                .sessionManagement((session)->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .exceptionHandling(exceptions->exceptions.
                        authenticationEntryPoint(new CustomAuthenticationEntryPoint(){
                        })
                        .accessDeniedHandler(new BearerTokenAccessDeniedHandler()));
        return http.build();
    }

    @Bean
    JwtDecoder jwtDecoder(){
        return NimbusJwtDecoder.withPublicKey(this.publicKey).build();
    }

    @Bean
    JwtEncoder jwtEncoder(){
        JWK jwk = new RSAKey.Builder(this.publicKey).privateKey(this.privateKey).build();
        JWKSource<SecurityContext> jwks = new ImmutableJWKSet<>(new JWKSet(jwk));
        return new NimbusJwtEncoder(jwks);
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider provider(PasswordEncoder encoder, TeacherApplicationService service){
        ClubAuthenticationProvider provider = new ClubAuthenticationProvider();
        provider.setPasswordEncoder(encoder);
        provider.setTeacherApplicationService(service);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider provider){
        return new ProviderManager(provider);
    }

    @Bean
    public SecurityContextRepository securityContextRepository(){
        return new HttpSessionSecurityContextRepository();
    }


}
