package ru.itmo.catsProject.cats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories("ru.itmo.catsProject.cats.DataAccess")
@EntityScan("ru.itmo.catsProject.cats.DataAccess")
@EnableFeignClients(basePackages = "ru.itmo.catsProject.common.Clients")
public class CatsApplication {
    public static void main(String[] args){
        SpringApplication.run(CatsApplication.class, args);

    }
}