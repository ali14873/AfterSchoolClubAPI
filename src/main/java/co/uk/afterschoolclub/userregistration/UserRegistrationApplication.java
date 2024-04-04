package co.uk.afterschoolclub.userregistration;

import co.uk.afterschoolclub.userregistration.Roles.RoleSeeder;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UserRegistrationApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserRegistrationApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner(){
        return new RoleSeeder();
    }

}
