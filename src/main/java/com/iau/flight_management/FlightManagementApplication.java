package com.iau.flight_management;

import com.iau.flight_management.service.MemberDetailsService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import java.io.File;

@SpringBootApplication
@EnableWebMvc
public class FlightManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(FlightManagementApplication.class, args);

//		File dtoFolder = new File("C:\\Users\\Emir\\OneDrive\\Masaüstü\\ileri_seviye_java\\spring-flight-management\\src\\main\\java\\com\\iau\\flight_management\\model\\entity");
//		for(File classFile : dtoFolder.listFiles()) {
//			System.out.println(String.format("%sDTO map (%s value);", classFile.getName().substring(0,classFile.getName().indexOf('.')), classFile.getName().substring(0,classFile.getName().indexOf('.'))));
//			System.out.println(String.format("%s map (%sDTO value);", classFile.getName().substring(0,classFile.getName().indexOf('.')), classFile.getName().substring(0,classFile.getName().indexOf('.'))));
//		}
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public AuthenticationProvider authenticationProvider(@Qualifier("memberDetailsService") MemberDetailsService memberDetailsService) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(memberDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder());
		return authenticationProvider;
	}
}
