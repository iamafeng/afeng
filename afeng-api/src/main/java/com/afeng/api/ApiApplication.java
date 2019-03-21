package com.afeng.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.afeng.*"})
//@EnableMBeanExport(registration = RegistrationPolicy.IGNORE_EXISTING)
@EntityScan(basePackages = {"com.afeng.*.domain"})
@EnableJpaRepositories(basePackages = {"com.afeng.*.dao"})
@EnableSwagger2
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
