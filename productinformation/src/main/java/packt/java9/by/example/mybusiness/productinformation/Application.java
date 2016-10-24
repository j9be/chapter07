package packt.java9.by.example.mybusiness.productinformation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "packt.java9.by.example")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
