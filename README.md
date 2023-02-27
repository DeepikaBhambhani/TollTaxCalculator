# TollTaxCalculator


DESCRIPTION:
This application calculates toll for vehicles travelling in Sweden. There are some criteria on which the toll is calculated. Please find below:
1. List of vehicles which are exempted from toll charges: Motorbike, Tractor, Emergency, Diplomat, Foreign, Military.
2. Vehicles which are not part of exemption will not be charged on
  i. Public Holidays
  ii. A Day before public holidays.
  iii. On weekends
3. Based on time and travel date, toll tax will be calculated.
4. If a vehicle crossing more than one toll in an hour then max of all the tolls will be charged
5. In a day a vehicle must not pay more than 60 Sek.

IMPLEMENTATION:
Created YAML file to store time and amount charged during travel 
Implemented Rest controller which will call service class and calculate toll.
Used data transfer objects to receive request body.
Created interface Vehicle which is implemented by different types of vehicles classes
Implemented service classes to handle validations and business logic.

1.service : This package contains two service classes where whole business logic and validation is done.

2.dto : These are data transfer objects created to fetch data from YAML file and to fetch request body.

3.domain : This package consist of all model objects

4.controller : This package has rest controller which calls services created in service package

5.config : classes created in this package consist of configuration properties which are required and configured at the time of server start up and some constants

6. exception handler: to handle controller thrown exception

TEST Package :

controller : Test cases related to controller classes are written.
service : Unit test cases of service classes.

ENHANCEMENTS:
1.We can implement our own API to handle time and fee data and make a rest call to get all the information.
2.We can use external Holiday API 
3.We can enhance it into a microservice application to make it more scalable.
4.YAML file can be moved as an external file and used whenever required. 
