package com.zwanenberg.gimmequote;

import org.junit.jupiter.api.Test;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class GimmequoteApplicationTests {
    @Test
    public void testMain() {
        String[] args = {}; // No command-line arguments needed

        ConfigurableApplicationContext context = SpringApplication.run(GimmequoteApplication.class, args);

        assertNotNull(context);
        context.close(); // Close the application context after the test
    }
}
