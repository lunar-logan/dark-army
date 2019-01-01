package com.anurag.darkarmy;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication(scanBasePackages = "com.anurag.darkarmy")
public class DarkArmyApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(DarkArmyApplication.class).build().run(args);
    }
}