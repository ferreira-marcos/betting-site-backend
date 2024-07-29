# Betting Site Backend


## Overview
For the project, a relational database was developed using ORM, along with a front end and a back end.

The database consists of a table for punters (Punter), who can make one or more bets, and a table for bets (Bet), with each bet being related to only one punter. Both tables were implemented using classes created with their respective names.

When the application starts, the initial screen will have a "Start Edition" button that calls the endpoint "/deleteDB" to clear the database and start a new edition of bets. On the bet registration screen, when the user registers a bet, a constant is created in the front end containing all the bet information. This information is sent to the back end using the HTTP POST method via the "/newBet" endpoint. The user is then directed to a screen displaying all registered bets, which are retrieved from the back end using the GET method for another endpoint, "/allbets".

When the user reaches the drawing phase, the front end sends a GET request to the "/generateNumbers" endpoint, which is responsible for executing the generateNumbers function in the back end to start the drawing. This function initially generates the first five numbers of the draw randomly through a loop and checks if they match the drawn numbers using the comparingBets method. This method iterates over the bet numbers and compares them with the drawn numbers so far. If there are no winners yet, another loop starts, generating the next twenty-five numbers one at a time until a winner is found.

The drawn numbers displayed on the draw page are retrieved using the GET method at the "/getNumbers" endpoint, which returns an array of the drawn numbers from the back end.

Additionally, on the same page, three other endpoints are called using the GET method: "/drawWinners", "/getRoundsOfDrawing", and "/getBetsNumbers". The first returns the list of winners, the second returns the number of rounds until a winner is found or until thirty numbers have been drawn, and the third endpoint executes the getAllNumbersBet method in the back end, which returns a Map with the bet number as the key and the count of bets containing that number as the value. This latter parameter is used for sorting.

Finally, in the prize phase, the front end makes a GET request to the "/calculateDiscount" endpoint, which calls a method in the back end to calculate the winners' prizes.


![Logical Model](<Logical Model.jpg>)

## Prerequisites
Before starting, you will need to have the following tools installed on your machine:
- git
- code editor like Vscode
- java JDK 17
- maven
- API teste like Insomnia

## Running the Project
To completely run this application, check its front-end: [front-end](https://github.com/ferreira-marcos/betting-site-frontend)
```bash
    # clone the repository
    $ git clone https://github.com/ferreira-marcos/betting-site-backend.git

    # access the directory
    $ cd betting-site-backend

    # run the Project
    $ mvn spring-boot:run
```

## Technologies
The following tools were used in the construction of the project:
- java
- Spring Boot
- H2
