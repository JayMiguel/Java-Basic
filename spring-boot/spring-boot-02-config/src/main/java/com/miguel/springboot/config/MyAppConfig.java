package com.miguel.springboot.config;

import com.miguel.springboot.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Configuration表明当前类是一个配置类，替代之前的XML配置文件
 *
 * 在XML配置文件中,用<bean></bean>标签添加组件
 * 在配置类中，用@Bean注解添加组件，组件的id默认是方法名
 */
@Configuration
public class MyAppConfig {

    @Bean
    public HelloService helloService02() {
        return new HelloService();
    }
}
