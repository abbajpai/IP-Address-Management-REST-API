package com.ipaddress.config;

import com.ipaddress.service.IPAddress;
import com.ipaddress.service.IPAddressService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author abhishek bajpai
 */

@Configuration
public class IPAddressServiceApplicationAutoConfiguration {

    @Bean
    public IPAddress createIPAddressServiceBean(){
        return new IPAddressService();
    }
}
