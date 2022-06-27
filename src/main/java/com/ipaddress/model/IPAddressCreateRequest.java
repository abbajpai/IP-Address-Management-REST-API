package com.ipaddress.model;

/**
 * @author abhishek bajpai
 */

public class IPAddressCreateRequest {

    private String cidr;

    public IPAddressCreateRequest() {
    }

    public String getCidr() {
        return cidr;
    }

    public void setCidr(String cidr) {
        this.cidr = cidr;
    }


}
