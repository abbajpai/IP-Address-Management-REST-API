package com.ipaddress.service;

import java.util.Map;

/**
 * @author abhishek bajpai
 */

public interface IPAddress {
    String create(String cidrBlock);

    Map<String, STATE> getAllIPs();

    String acquire(String ipAddress);

    String release(String ipAddress);

     enum STATE {
        ACQUIRED,
        AVAILABLE
    }

     enum CORRESPONDENCES {
        INVALID_INPUT("empty cidr parameter in request body"),
        ALREADY_EXISTING("Already used cidr"),
        NOT_IN_RANGE("cidr parameter in request body is out of valid range"),
        SUCCESS_CREATION("successfully created Ip's in the given cidr range"),
        SUCCESS_ACQUIRING("successfully acquired given ip"),
        SUCCESS_RELEASING("successfully released given ip"),
        UNKNOWN("Server error computing ip's"),
         NOT_FOUND("ip entry not found");

        private final String message;

        String getMessage() {
            return message;
        }

        CORRESPONDENCES(String message) {
            this.message = message;
        }

    }
}
