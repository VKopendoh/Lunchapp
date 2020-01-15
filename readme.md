# Lunch application

Application created accrding this task:
Design and implement a REST API using Hibernate/Spring/SpringMVC (or Spring-Boot) **without frontend**.

The task is:

Build a voting system for deciding where to have lunch.

 * 2 types of users: admin and regular users
 * Admin can input a restaurant and it"s lunch menu of the day (2-5 items usually, just a dish name and price)
 * Menu changes each day (admins do the updates)
 * Users can vote on which restaurant they want to have lunch at
 * Only one vote counted per user
 * If user votes again the same day:
    - If it is before 11:00 we asume that he changed his mind.
    - If it is after 11:00 then it is too late, vote can"t be changed

Each restaurant provides new menu each day.

As a result, provide a link to github repository.

It should contain the code and **README.md with API documentation and curl commands to get data for voting and vote.**

##Application REST interfaces

***Important:*** *cUrl commands adopted for Windows CLI*  

   ###1.Restaurant management.   
    
   1.1. Get info about all restaurants (access have admin and regular users): 
   
    curl --location --request GET http://localhost:8080/rest/restaurant/ --header "Content-Type: application/json" --header "Authorization: Basic dXNlcjpwYXNz" --data-raw ""
   
   1.2. Get info about one restaurant(access have admin and regular users): 
   
    curl --location --request GET http://localhost:8080/rest/restaurant/1 --header "Content-Type: application/json" --header "Authorization: Basic dXNlcjpwYXNz" --data-raw ""
   
   1.3. Create new restaurant(access have admin):
   
    curl --location --request POST http://localhost:8080/rest/restaurant --header "Content-Type: application/json" --header "Authorization: Basic YWRtaW46YWRtaW4=" --data-raw "{\"name\": \"Restaurant one\"}"   
  
   1.4. Create new menu for restaurant or update it (access have admin):
   
    curl --location --request PUT http://localhost:8080/rest/restaurant/1/menu --header "Content-Type: application/json" --header "Authorization: Basic YWRtaW46YWRtaW4=" --data-raw "{\"dishes\": [{\"name\":\"lozagnya\", \"price\":900},{\"name\":\"pasta\", \"price\":450},{\"name\":\"latte\", \"price\":200}]}"
   
   1.5. Vote for restaurant (access have admin and regular users):
        
        curl --location --request PATCH http://localhost:8080/rest/restaurant/1/vote ^
        --header "Content-Type: application/json" ^
        --header "Authorization: Basic dXNlcjpwYXNz"
   
   1.6. Update Restaurant (access have admin):
       
       curl --location --request PUT "http://localhost:8080/rest/restaurant/1" ^
       --header "Content-Type: application/json" ^
       --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
       --data-raw "{\"name\": \"NEW RESTAURANT\"}" 
   
   1.7. Delete Restaurant (access have admin):
   
      curl --location --request DELETE "http://localhost:8080/rest/restaurant/2" ^
      --header "Content-Type: application/json" ^
      --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
      --data-raw ""  
   
   ###2.User management.
   
  2.1. Get info about all users (access have admin): 
     
      curl --location --request GET http://localhost:8080/rest/user/ --header /"Content-Type: application/json" --header "Authorization: Basic dXNlcjpwYXNz" --data-raw ""
     
  2.2. Get info about one user(access have admin): 
     
      curl --location --request GET http://localhost:8080/rest/user/1 --header "Content-Type: application/json" --header "Authorization: Basic dXNlcjpwYXNz" --data-raw ""
     
  2.3. Create new user(access have admin):
      
      curl --location --request POST http://localhost:8080/rest/user ^
      --header "Content-Type: application/json" ^
      --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
      --data-raw "{\"name\": \"admin2\",\"password\": \"pass\",\"enabled\": true,\"roles\":[\"ROLE_USER\",\"ROLE_ADMIN\"]}"
     
  2.4. Update new user(access have admin):
        
     curl --location --request PUT "http://localhost:8080/rest/user/1" ^
     --header "Content-Type: application/json" ^
     --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
     --data-raw "{\"password\":\"admin\",\"enabled\":true,\"roles\":[\"ROLE_USER\",\"ROLE_ADMIN\"]}"
  
     
  2.5. Delete user (access have admin):
  
     curl --location --request DELETE "http://localhost:8080/rest/user/1" ^
     --header "Content-Type: application/json" ^
     --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
     --data-raw ""
  
  ###3. User registration
  
  3.1. Get information about current user (Authorized user only with him self):
  
    curl --location --request GET "http://localhost:8080/rest/registration/info" ^
    --header "Content-Type: application/json" ^
    --header "Authorization: Basic dXNlcjpwYXNz" ^
    --data-raw ""  
  
  3.2. Register user (Any)
  
    curl --location --request POST "http://localhost:8080/rest/registration" ^
    --header "Content-Type: application/json" ^
    --data-raw "{\"name\": \"newUser\",\"password\": \"pass\"}"
    
  3.3. Update authorized user (Authorized user only with him self):
  
  *You will not able to get additional roles this way!*
    
    curl --location --request PUT "http://localhost:8080/rest/registration/update" ^
    --header "Content-Type: application/json" ^
    --header "Authorization: Basic bmV3VXNlcjpwYXNz" ^
    --data-raw "{\"name\": \"VeryNew\",\"roles\": [\"ROLE_USER\",\"ROLE_ADMIN\"]}"
  
  3.4. Delete authorized user (Authorized user only with him self):
  
    curl --location --request DELETE "http://localhost:8080/rest/registration/delete" ^
    --header "Content-Type: application/json" ^
    --header "Authorization: Basic dXNlcjpwYXNz" ^
    --data-raw ""
  
  ###4. History information
  
  4.1. Get all history (access have admins):
    
    curl --location --request GET "http://localhost:8080/rest/history" ^
    --header "Content-Type: application/json" ^
    --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
    --data-raw ""
    
  4.2. Get history by Restaurant id (access have admins):
        
    curl --location --request GET "http://localhost:8080/rest/history/3" ^
    --header "Content-Type: application/json" ^
    --header "Authorization: Basic YWRtaW46YWRtaW4=" ^
    --data-raw ""    