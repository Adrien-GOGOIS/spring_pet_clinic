package com.petclinic.practice;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("testing")
public class GreetingServiceTest {
    GreetingService greetingService1;
    GreetingService greetingService2;

    @Autowired
    public GreetingServiceTest(GreetingService greetingService1, GreetingService greetingService2) {
        this.greetingService1 = greetingService1;
        this.greetingService2 = greetingService2;
    }

    @DisplayName("Should greet with the name of the person")
    @Test
    public void shouldGreetSuccessfully() {
        String result = greetingService1.sayHi();
        assertThat(result).isEqualTo("Hello John");
    }

    @DisplayName("Should use singletons") // Car scope = singleton par défaut (!= prototype)
    @Test
    public void shouldUseSingletons() {
        assertThat(greetingService1).isSameAs(greetingService2);
    }
}
