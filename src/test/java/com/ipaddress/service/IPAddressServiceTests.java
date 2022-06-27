package com.ipaddress.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * @author abhishek bajpai
 */

@SpringBootTest
class IPAddressServiceTests {

	@Autowired
	private  IPAddressService ipAddressService;

	@Test
	void create_And_then_Fetch_Success_Test() {
		String response = ipAddressService.create("168.25.0.0/22");
		Assert.isTrue(response.equalsIgnoreCase("Successfully created Ip's in the given cidr range") , "Ip creation failed");
		Assert.notEmpty(ipAddressService.getAllIPs(), "Created "+ ipAddressService.getAllIPs().size() + "IPs");
	}

	@Test
	void create_And_Fetch_Failure_Test() {
		String response = ipAddressService.create("192.168.0.1/33");
		Assert.isTrue(response.equalsIgnoreCase("cidr parameter in request body is out of valid range"+"192.168.0.1/33") , "Ip creation failed");
		Assert.isTrue(0==ipAddressService.getAllIPs().size(), "Ip creation should have failed");
	}

	@Test
	void acquire_Non_Existing_IP_Test() {
		String response = ipAddressService.acquire("192.168.0.1");
		Assert.isTrue(response.equalsIgnoreCase("ip entry not found") , "ip entry found");
	}

	@Test
	void release_Non_Existing_IP_Test() {
		String response = ipAddressService.release("192.168.0.1");
		Assert.isTrue(response.equalsIgnoreCase("ip entry not found") , "ip entry found");
	}

	@Test
	void fetch_ALL_From_Empty_Store_Test() {
		Map<String, IPAddress.STATE> response = ipAddressService.getAllIPs();
		Assert.isTrue( response.size() > 0 , "actual size" + response.size());
	}

}
