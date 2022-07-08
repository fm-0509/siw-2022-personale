package it.uniroma3.siw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class Siw2022PersonaleApplication {

    private static ApplicationContext appContext;
    public static void main(String[] args) {

        appContext = SpringApplication.run(Siw2022PersonaleApplication.class, args);
    }

    public static ApplicationContext getAppContext() {
        return appContext;
    }
}
