package com.miguel.springboot;

import com.miguel.springboot.bean.Dog;
import com.miguel.springboot.bean.Mail;
import com.miguel.springboot.bean.Person;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class SpringBoot02ConfigApplicationTests {

    @Autowired
    Person person;

    @Autowired
    Dog dog;

    @Autowired
    Mail email;

    @Autowired
    ApplicationContext ioc;

    @Test
    void testHelloService() {
        boolean flag = ioc.containsBean("helloService02");
        System.out.println(flag);
    }

    @Test
    void contextLoads() {
        System.out.println(person);
        System.out.println(dog);
        System.out.println(email);
    }

}
