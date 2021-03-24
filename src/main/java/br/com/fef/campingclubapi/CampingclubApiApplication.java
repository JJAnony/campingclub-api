package br.com.fef.campingclubapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableCaching
@RestController
@EnableTransactionManagement
@SpringBootApplication
public class CampingclubApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampingclubApiApplication.class, args);
	}

}
