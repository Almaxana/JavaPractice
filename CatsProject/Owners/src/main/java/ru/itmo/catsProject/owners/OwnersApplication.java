package ru.itmo.catsProject.owners;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.itmo.catsProject.owners.DataAccess")
@EntityScan("ru.itmo.catsProject.owners.DataAccess")
@EnableFeignClients(basePackages = "ru.itmo.catsProject.common.Clients")
public class OwnersApplication {
    public static void main(String[] args){
        SpringApplication.run(OwnersApplication.class, args);

    }
}