
# IP Address Management Application

This application provides APIs to manage ( create, acquire , release , list) all possible ip addresses within
the range of CIDR (Classless Inter-Domain Routing) -- also known as supernetting --



## Run Locally

Clone the project

```bash
  git clone https://github.com/abbajpai/IP-Address-Management-REST-API.git
```

Go to the project directory

```bash
  cd IP-Address-Management-REST-API
```

Install dependencies

```bash
  1.) Install Java 8 or higher
  2.) Install and configure maven following this link https://maven.apache.org/install.html
```

Start the server

```bash
  mvn spring-boot:run
```


## Usage/Examples

Create IP addresses from a given CIDR block string  

```javascript

Request 
curl --location --request POST 'http://localhost:8080/v1/ipaddress' \
--header 'Content-Type: application/json' \
--header 'Cookie: JSESSIONID=node01smt9jiwn23pc1coqzamth7skr0.node0' \
--data-raw '{
  
   "cidr" : "168.25.0.0/22"
}

Response  
"successfully created Ip's in the given cidr range"

```

Get  all available ip addresses with there current state   
```javascript
Request
curl --location --request GET 'http://localhost:8080/v1/ipaddress'

Response
{
    "168.25.1.255": "AVAILABLE",
    "168.25.1.254": "AVAILABLE",
    ...........................
    ...........................
}   
```

Update the state of a IP addresse to acquired
```javascript
Request
curl --location --request PUT 'http://localhost:8080/v1/ipaddress/acquire/168.25.1.255' \
--header 'Cookie: JSESSIONID=node01smt9jiwn23pc1coqzamth7skr0.node0'

Response
"successfully acquired given ip"
```

Update the state of a IP addresse to release or available 
```javascript
Request
curl --location --request PUT 'http://localhost:8080/v1/ipaddress/release/168.25.1.255' \
--header 'Cookie: JSESSIONID=node01smt9jiwn23pc1coqzamth7skr0.node0'

Response
"successfully released given ip"
```
## Running Tests

To run tests, run the following command

```bash
  mvn clean install
```

There are five integration test 

Test Class : com.ipaddress.service.IPAddressServiceTests

Tests:
```bash
  create_And_then_Fetch_Success_Test() 
  create_And_Fetch_Failure_Test()
  acquire_Non_Existing_IP_Test()
  release_Non_Existing_IP_Test()
  fetch_ALL_From_Empty_Store_Test()

```
