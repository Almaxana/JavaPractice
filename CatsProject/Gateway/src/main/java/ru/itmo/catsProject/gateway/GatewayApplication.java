package ru.itmo.catsProject.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = "ru.itmo.catsProject")
@EnableJpaRepositories("ru.itmo.catsProject.gateway.User.DataAccess")
@EntityScan("ru.itmo.catsProject.gateway.User.DataAccess")
@ConfigurationPropertiesScan("ru.itmo.catsProject.gateway.Security")
@EnableFeignClients(basePackages = "ru.itmo.catsProject.common.Clients")
public class GatewayApplication {
    public static void main(String[] args){
        SpringApplication.run(GatewayApplication.class, args);
        //System.out.println("ADMIN password = " + new BCryptPasswordEncoder().encode("1"));
    }
}