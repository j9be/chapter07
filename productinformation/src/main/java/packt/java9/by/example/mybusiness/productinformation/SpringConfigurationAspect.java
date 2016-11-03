package packt.java9.by.example.mybusiness.productinformation;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;
import packt.java9.by.example.mybusiness.productinformation.lookup.ResourceBasedProductLookup;

@Configuration
@Aspect
public class SpringConfigurationAspect {

    @Around("execution(* byId(..))")
    public ProductInformation before(ProceedingJoinPoint jp) throws Throwable {
        System.out.println("before join point ******");
        ProductInformation pi = (ProductInformation) jp.proceed(jp.getArgs());
        System.out.println("after join point ******");
        return pi;
    }

}
