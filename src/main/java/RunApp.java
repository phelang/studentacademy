import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan({
        "app.studentacademy.springConfig", "app.studentacademy.model", "app.studentacademy.repository",
        "app.studentacademy.service", "app.studentacademy.controller" })
public class RunApp {

    public static void main(String[] args){
        SpringApplication.run(RunApp.class, args);
    }
}
