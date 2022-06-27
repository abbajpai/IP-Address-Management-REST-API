package com.ipaddress.controller;

import com.ipaddress.model.IPAddressCreateRequest;
import com.ipaddress.service.IPAddress;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author abhishek bajpai
 */

@RestController
@RequestMapping(IPAddressServiceConstants.V1_IPAddress)
public class IPAddressServiceController {

    @Autowired
    private IPAddress ipAddress;

    /**
     * Creates and stores ip valid withing the range of given ipAddressCreateRequest
     *
     * @param ipAddressCreateRequest - cidr block
     * @return success or error message
     */
    @PostMapping
    public String create(@RequestBody IPAddressCreateRequest ipAddressCreateRequest){
        return ipAddress.create(ipAddressCreateRequest.getCidr());
    }

    /**
     * @return Map of all ip addresses with there corresponding state ( available or acquired )
     */
    @GetMapping
    public Map<String, IPAddress.STATE> getAllIPAddresses(){
        return ipAddress.getAllIPs();
    }

    /**
     * Marks the ip status as acquired if ip entry exists
     *
     * @param ip - given ip address in path
     * @return success or error message
     */
    @PutMapping("/acquire/{ip}")
    public String acquire( @PathVariable("ip") String ip){
        return ipAddress.acquire(ip);
    }

    /**
     * Marks the ip status as release if ip entry exists
     *
     * @param ip - given ip address in path
     * @return success or error message
     */
    @PutMapping("release/{ip}")
    public String release( @PathVariable("ip") String ip){
        return ipAddress.release(ip);
    }

}
