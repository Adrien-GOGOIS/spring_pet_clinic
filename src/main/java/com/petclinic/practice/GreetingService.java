package com.petclinic.practice;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

@Service
@Scope(BeanDefinition.SCOPE_SINGLETON) // Ici seulement singleton mais peut-être changé en prototype par exemple
public class GreetingService {
    protected String sayHi() {
        return "Hello John";
    }
}
