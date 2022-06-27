package com.ipaddress.service;

import org.apache.commons.net.util.SubnetUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author abhishek bajpai
 */

public class IPAddressService implements IPAddress {

    private final Logger logger = LoggerFactory.getLogger(IPAddressService.class);
    private final Map<String, Map<String, STATE>> ipStore = new HashMap<>();

    @Override
    public String create(final String cidrBlock) {
        if(null == cidrBlock || 0 == cidrBlock.trim().length() ){
            return CORRESPONDENCES.INVALID_INPUT.getMessage();
        }
        if(ipStore.containsKey(cidrBlock)){
            return CORRESPONDENCES.ALREADY_EXISTING.getMessage() + cidrBlock ;
        }
        try {
            final SubnetUtils utils = new SubnetUtils(cidrBlock);
            final String[] allIps = utils.getInfo().getAllAddresses();
            final Map<String, STATE> ips = new HashMap<>();
            Arrays.stream(allIps).forEach(element -> ips.put(element, STATE.AVAILABLE));
            ipStore.put(cidrBlock, ips);
        } catch ( Exception ex){
            if(ex.getLocalizedMessage().contains("not in range")){
                return CORRESPONDENCES.NOT_IN_RANGE.getMessage() + cidrBlock;
            }
            logger.info(CORRESPONDENCES.UNKNOWN.getMessage() + ex.getLocalizedMessage());

        }

        return CORRESPONDENCES.SUCCESS_CREATION.getMessage();
    }

    @Override
    public Map<String, STATE> getAllIPs() {
        return Collections.unmodifiableMap(ipStore
                .values()
                .stream()
                .flatMap(map -> map.entrySet().stream())
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    @Override
    public String acquire(final String ipAddress) {
        if(null == ipAddress || 0 == ipAddress.trim().length() ){
            return CORRESPONDENCES.INVALID_INPUT.getMessage();
        }
        for (Map.Entry<String, Map<String, STATE>> ele : ipStore.entrySet()) {
            for( Map.Entry<String, STATE> ele1 : ele.getValue().entrySet()){
                if(ipAddress.equalsIgnoreCase(ele1.getKey())){
                    ele1.setValue(STATE.ACQUIRED);
                    return CORRESPONDENCES.SUCCESS_ACQUIRING.getMessage();
                }
            }
        }
        return CORRESPONDENCES.NOT_FOUND.getMessage();
    }


    @Override
    public String release(final String ipAddress){
        if(null == ipAddress || 0 == ipAddress.trim().length() ){
            return CORRESPONDENCES.INVALID_INPUT.getMessage();
        }
        for (Map.Entry<String, Map<String, STATE>> ele : ipStore.entrySet()) {
            for( Map.Entry<String, STATE> ele1 : ele.getValue().entrySet()){
                if(ipAddress.equalsIgnoreCase(ele1.getKey())){
                    ele1.setValue(STATE.AVAILABLE);
                    return CORRESPONDENCES.SUCCESS_RELEASING.getMessage();
                }
            }
        }
        return CORRESPONDENCES.NOT_FOUND.getMessage();
    }

}
