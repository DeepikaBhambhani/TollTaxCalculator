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
Handled exceptions
Used data transfer objects to receive request body.
Created interface Vehicle which is implemented by different types of vehicles classed
Implemented service classes to handle validations and business logic.

ENHANCEMENTS:
We can implement our own API to handle time and fee data and make a rest call to get all the information.
We can use external Holiday API 
We can enhance it into a microservice application to make it more scalable.
YAML file can be moved as an external file and used whenever required.
