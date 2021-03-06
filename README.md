# User Manipulation

An application to save list of users to database and fetch the name of users, sorted, in the specifeid range.

## Description

In the applicattion, there are two api's exposed. 
* Save user api - This takes a list of UserData class as argument and saves this details to the database.
* Fetch user data api- This api takes two parameters in the request which acts as the minimum and maximum value of a range of postcode . The api returns sorted list of names of  users that belong to that specified postcode range alongwith the total character count of all the names in the list.

## Getting Started

### Prerequisites

* JDK 1.8
* Maven 3
* Postman
* IDE

### Technologies used
The tech stack includes:
* Spring Boot
* REST API
* Mockito framework for testing
* In memory database - H2

### Dependencies
All the dependencies are updated in the pom.xml file

### Installing

* Clone the project from the github repository to a local folder 
* Import the project to the IDE

### Executing program

* Build the project using ```mvn clean install ```
* Building can be done either from terminal in the IDE or using command prompt
* Run the application using ```mvn spring-boot:run``` or directly from the IDE "Run" after adding the required maven configurations

### Verifying the results

* The application will be available at ```localhost:{port}```
* From postman, this can be verified by hitting this corresponding endpoints. 
* CURL for save user api - 

```curl --location --request POST 'localhost:8080/api/users' \
--header 'Content-Type: application/json' \
--data-raw '[{
    "name":"A",
    "postcode":"5490"
},
{
    "name":"B",
    "postcode":"8"
},
{
    "name":"C",
    "postcode":"13000"
}]'
```
The usernames and postcodes and the number of userdata object can be changed as per user convinience.
* Fetch userData api :
    ```curl --location --request GET 'localhost:8080/api/userdata?minValue={}&maxValue={}'```

### Test Cases

* Test cases are included for all the layers of the application
* Run those from the IDE to check the functionality of the methods

## Authors

*Ann George - annrosevarghese@gmail.com

## Version History

* 0.1
    * Initial Release
