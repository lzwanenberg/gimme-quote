package com.zwanenberg.gimmequote;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Random;

@SpringBootApplication
public class GimmequoteApplication {
    public static void main(String[] args) {
        SpringApplication.run(GimmequoteApplication.class, args);
    }
}
