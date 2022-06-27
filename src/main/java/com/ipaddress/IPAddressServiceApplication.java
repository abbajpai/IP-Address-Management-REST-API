package com.ipaddress;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author abhishek bajpai
 */

@SpringBootApplication
@ComponentScan({"com.ipaddress.config","com.ipaddress.controller","com.ipaddress.model"})
public class IPAddressServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IPAddressServiceApplication.class, args);
    }


}
