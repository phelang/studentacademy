package app.studentacademy.springConfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@EnableWebMvc   //<mvc:annotation-driven />
@Configuration
@ComponentScan({
        "app.studentacademy.springConfig", "app.studentacademy.model", "app.studentacademy.repository",
        "app.studentacademy.service", "app.studentacademy.controller" })
public class RootConfig extends WebMvcConfigurerAdapter {
}