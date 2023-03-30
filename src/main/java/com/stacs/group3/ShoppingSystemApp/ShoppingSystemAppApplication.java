package com.stacs.group3.ShoppingSystemApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "*")
@SpringBootApplication
public class ShoppingSystemAppApplication {

public static void main(String[] args) {
    SpringApplication.run(ShoppingSystemAppApplication.class, args);
    //  MainApp mainApp = new MainApp();
    // mainApp.main();
}

}
